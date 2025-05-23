package se.yrgo.client;

import java.util.*;

import jakarta.persistence.*;
import se.yrgo.domain.*;

public class Client {
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        createTestData(tx, em);

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

    private static void createTestData(EntityTransaction tx, EntityManager em) {
        try {
            tx.begin();

            // Create Users
            AppUser user1 = new AppUser();
            user1.setUsername("gamer123");
            user1.setEmail("gamer123@example.com");
            user1.setPassword("securepass");

            AppUser user2 = new AppUser();
            user2.setUsername("proplayer");
            user2.setEmail("proplayer@example.com");
            user2.setPassword("propass");

            // Create Games
            Game game1 = new Game();
            game1.setTitle("Space Battle");
            game1.setGenre("Action");
            game1.setDeveloper("Galactic Games");

            Game game2 = new Game();
            game2.setTitle("Mystic Quest");
            game2.setGenre("RPG");
            game2.setDeveloper("Fantasy Forge");

            // Add games to users' libraries
            user1.getLibrary().add(game1);
            user1.getLibrary().add(game2);
            user2.getLibrary().add(game2);

            game1.getUsers().add(user1);
            game2.getUsers().add(user1);
            game2.getUsers().add(user2);

            // Create Reviews
            Review review1 = new Review(5, "Awesome game!", user1, game1);
            Review review2 = new Review(4, "Really fun but too short.", user1, game2);
            Review review3 = new Review(3, "Decent RPG experience.", user2, game2);

            user1.getReviews().add(review1);
            user1.getReviews().add(review2);
            user2.getReviews().add(review3);

            game1.getReviews().add(review1);
            game2.getReviews().add(review2);
            game2.getReviews().add(review3);

            // Persist data
            em.persist(user1);
            em.persist(user2);

            // Since reviews and games are cascaded, they will be persisted too

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
