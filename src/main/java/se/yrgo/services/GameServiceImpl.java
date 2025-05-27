package se.yrgo.services;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;
import se.yrgo.exception.*;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Override
    public List<Game> getAllGames() {
        return gameDao.allGames();
    }

    @Override
    public Game getGameById(int id) throws GameNotFoundException {
        return gameDao.findGameById(id);
    }

    @Override
    public void addGame(Game game) {
        gameDao.createGame(game);
    }
}
