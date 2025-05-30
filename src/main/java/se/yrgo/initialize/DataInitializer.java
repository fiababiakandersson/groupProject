package se.yrgo.initialize;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Component
public class DataInitializer {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GameDao gameDao;

    @Autowired
    private ReviewDao reviewDao;

    public void initialize() {
        System.out.println("Initierar exempeldatat...");

        Game game1 = new Game("Elden Ring", "Action RPG", "FromSoftware");
        Game game2 = new Game("Stardew Valley", "Farming Sim", "ConcernedApe");

        gameDao.createGame(game1);
        gameDao.createGame(game2);

        User user1 = new User("User-1", "user@yrgo.se", "sigma123", new HashSet<>());

        user1.getLibrary().add(game1);

        userDao.createUser(user1);

        Review review1 = new Review(9, "Spel med lore", user1, game1);
        Review review2 = new Review(8, "Rogivande och beroendeframkallande.", user1, game2);

        reviewDao.createReview(review1);
        reviewDao.createReview(review2);

        System.out.println("Exempeldatat är inlagt");
    }
}
