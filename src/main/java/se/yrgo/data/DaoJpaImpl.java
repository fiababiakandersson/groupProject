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
        Review toDelete = em.find(Review.class, review.getId());
        if (toDelete != null)
            em.remove(toDelete);
    }

    @Override
    public void updateReviewById(int id, Review review) throws ReviewNotFoundException {
        Review existing = em.find(Review.class, id);
        if (existing == null)
            throw new ReviewNotFoundException();
        existing.setRating(review.getRating());
        existing.setComment(review.getComment());
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
        } catch (Exception e) {
            throw new GameNotFoundException();
        }
    }

    // user dao
    @Override
    public User findUserById(int id) throws UserNotFoundException {
        try {
            return (User) em.createQuery("select user from User as user where user.id=:id").setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new UserNotFoundException();
        }
    }

}
