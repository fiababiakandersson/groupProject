package se.yrgo.data;

import java.util.*;

import javax.persistence.*;

import org.springframework.stereotype.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Repository
public class DaoJpaImpl implements GameDao, UserDao, ReviewDao {
    private EntityManager em;

    public DaoJpaImpl(EntityManager em) {
        this.em = em;
    }

    // review dao
    @Override
    public List<Review> allReviews() {
        return em.createQuery("select review from Review as review", Review.class).getResultList();
    }

    @Override
    public void createReview(Review newReview) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(newReview);
        tx.commit();
    }

    @Override
    public void deleteReview(Review review) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Review toDelete = em.find(Review.class, review.getId());
        if (toDelete != null)
            em.remove(toDelete);
        tx.commit();
    }

    @Override
    public void updateReviewById(int id, Review review) throws ReviewNotFoundException {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Review existing = em.find(Review.class, id);
        if (existing == null) {
            tx.rollback();
            throw new ReviewNotFoundException();
        }
        existing.setRating(review.getRating());
        existing.setComment(review.getComment());
        tx.commit();
    }

    // game dao
    @Override
    public List<Game> allGames() {
        return em.createQuery("select game from Game as game", Game.class).getResultList();
    }

    @Override
    public Game findGameById(int id) throws GameNotFoundException {
        try {
            return (Game) em.createQuery("select game from Game as game where game.id=:id").setParameter("id", id)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new GameNotFoundException();
        }
    }

    // user dao
    @Override
    public User findUserById(int id) throws UserNotFoundException {
        try {
            return (User) em.createQuery("select user from User as user where user.id=:id").setParameter("id", id)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new UserNotFoundException();
        }
    }

}
