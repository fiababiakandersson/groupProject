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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allReviews'");
    }

    @Override
    public void createReview(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReview'");
    }

    @Override
    public void deleteReview(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReview'");
    }

    @Override
    public void updateReviewById(int id, Review review) throws ReviewNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReviewById'");
    }

    // game dao
    @Override
    public List<Game> allGames() {
        return em.createQuery("select game from Game as game", Game.class).getResultList();
    }

    @Override
    public Game findGameById(int id) throws GameNotFoundException {
        try {
            return (Game) em.createQuery("select game from Game as game where game.id=:id").setParameter("id", id);
        } catch (javax.persistence.NoResultException e) {
            throw new GameNotFoundException();
        }
    }

    // user dao
    @Override
    public User findUserById(int id) throws UserNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findUserById'");
    }

}
