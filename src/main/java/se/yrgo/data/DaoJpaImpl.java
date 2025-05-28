package se.yrgo.data;

import java.util.*;

import javax.persistence.*;
import javax.transaction.*;

import org.springframework.stereotype.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Repository
@Transactional
public class DaoJpaImpl implements GameDao, UserDao, ReviewDao {

    @PersistenceContext
    private EntityManager em;

    // reviews
    @Override
    public List<Review> allReviews() {
        return em.createQuery("select review from Review as review", Review.class).getResultList();
    }

    @Override
    public void createReview(Review newReview) {
        em.persist(newReview);
    }

    @Override
    public Review findReviewById(int id) {
        return em.find(Review.class, id);
    }

    @Override
    public void deleteReview(Review review) {
        Review toDelete = em.find(Review.class, review.getId());
        if (toDelete != null)
            em.remove(toDelete);
    }

    @Override
    public void updateReviewById(int id, Review updatedReview) throws ReviewNotFoundException {
        em.merge(updatedReview);
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

    @Override
    public void createGame(Game game) {
        em.persist(game);
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

    @Override
    public void createUser(User user) {
        em.persist(user);
    }

    @Override
    public List<User> allUsers() {
        return em.createQuery("select user from User as user", User.class).getResultList();
    }

}
