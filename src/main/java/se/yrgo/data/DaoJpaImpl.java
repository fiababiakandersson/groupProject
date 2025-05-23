package se.yrgo.data;

import java.util.*;

import javax.persistence.*;

import org.springframework.stereotype.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Repository
public class DaoJpaImpl implements GameDao, UserDao, ReviewDao {
    @PersistenceContext
    private EntityManager em;

    // review dao
    @Override
    public List<Review> allReviews() {
        return em.createQuery("select review from Review as review", Review.class).getResultList();
    }

    @Override
    public void createReview(Review newReview) {
        em.persist(newReview);
    }

    @Override
    public void deleteReview(Review review) {
        Review book = em.find(Review.class, review.getId());
        em.remove(book);
    }

    @Override
    public void updateReviewById(int id, Review review) throws ReviewNotFoundException {
        Review existingReview = em.find(Review.class, id);
        if (existingReview == null) {
            throw new ReviewNotFoundException();
        }

        existingReview.setRating(review.getRating());
        existingReview.setComment(review.getComment());
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
    public AppUser findUserById(int id) throws UserNotFoundException {
        try {
            return (AppUser) em.createQuery("select user from User as user where user.id=:id").setParameter("id", id)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new UserNotFoundException();
        }
    }

}
