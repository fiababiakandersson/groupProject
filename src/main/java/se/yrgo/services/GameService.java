package se.yrgo.services;

import java.util.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;

public interface GameService {
    List<Game> getAllGames();

    Game getGameById(int id) throws GameNotFoundException;
}
