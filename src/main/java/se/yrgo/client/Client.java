package se.yrgo.client;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

import se.yrgo.data.*;
import se.yrgo.domain.*;

@Component
public class Client {
    // public static EntityManagerFactory emf =
    // Persistence.createEntityManagerFactory("databaseConfig");

    private final DaoJpaImpl dao;

    @Autowired
    public Client(DaoJpaImpl dao) {
        this.dao = dao;
    }

    public void run() {
        try {
            // create users
            AppUser user1 = new AppUser();
            user1.setUsername("gamer123");
            user1.setEmail("gamer123@example.com");
            user1.setPassword("securepass");

            AppUser user2 = new AppUser();
            user2.setUsername("proplayer");
            user2.setEmail("proplayer@example.com");
            user2.setPassword("propass");

            // create games
            Game game1 = new Game();
            game1.setTitle("Space Battle");
            game1.setGenre("Action");
            game1.setDeveloper("Galactic Games");

            Game game2 = new Game();
            game2.setTitle("Mystic Quest");
            game2.setGenre("RPG");
            game2.setDeveloper("Fantasy Forge");

            // add games to users' libraries
            user1.getLibrary().add(game1);
            user1.getLibrary().add(game2);
            user2.getLibrary().add(game2);

            game1.getUsers().add(user1);
            game2.getUsers().add(user1);
            game2.getUsers().add(user2);

            // create reviews
            Review review1 = new Review(5, "Awesome game!", user1, game1);
            Review review2 = new Review(4, "Really fun but too short.", user1, game2);
            Review review3 = new Review(3, "Decent RPG experience.", user2, game2);

            user1.getReviews().add(review1);
            user1.getReviews().add(review2);
            user2.getReviews().add(review3);

            game1.getReviews().add(review1);
            game2.getReviews().add(review2);
            game2.getReviews().add(review3);

            dao.createUser(user1);
            dao.createUser(user2);
        } catch (Exception e) {
            System.err.println(e);
        }

        // menu options:
        // 1. show all games
        // 2. select a game (and show details and their reviews)
        // 3. write and add a review on a game
        // 4. remove your own review
        // 5. update your own review

        // files.
        // Customer-klass: Game.java
        // CustomerService-interface:
        // CustomerServiceProductionImpl-klass :
        // CustomerDaoImpl: DaoJpaImpl.java
        // CustomerDao-interface: GameDao.java

        while (true) {

            System.out.println("Welcome to Gamereview.com:");
            System.out.println("[1] Show all games");
            System.out.println("[2] Show game details");
            System.out.println("[3] Write a review");
            System.out.println("[4] Remove a review");
            System.out.println("[5] Update a review");

            Scanner input = new Scanner(System.in);
            try {
                int pick = input.nextInt();

                switch (pick) {
                    case 1:
                        System.out.println("[1]");
                        break;
                    case 2:
                        System.out.println("[2]");
                        break;
                    case 3:
                        System.out.println("[3]");
                        break;
                    case 4:
                        System.out.println("[4]");
                        break;
                    case 0: {
                        break;
                    }
                    default:
                        System.out.println("Ogiltigt val");

                }

            } catch (InputMismatchException ex) {
                System.err.println("Pick one of the options presented");
            }
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Client client = context.getBean(Client.class);
        client.run();
    }
}
