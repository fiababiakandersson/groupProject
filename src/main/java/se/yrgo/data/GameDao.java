package se.yrgo.data;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface GameDao {
  public List<Game> allGames();

  public Game findGameById(int id) throws GameNotFoundException;

  public void createGame(Game review);

  // public void createReview(Review review, int id);
}
