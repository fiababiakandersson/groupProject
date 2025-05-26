package se.yrgo.client;

import java.util.*;

import org.springframework.context.support.*;

import se.yrgo.domain.Game;
import se.yrgo.domain.Review;
import se.yrgo.domain.User;
import se.yrgo.exception.GameNotFoundException;
import se.yrgo.exception.ReviewNotFoundException;
import se.yrgo.exception.UserNotFoundException;
import se.yrgo.services.GameService;
import se.yrgo.services.ReviewService;
import se.yrgo.services.UserService;

public class Client {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        GameService gameService = context.getBean(GameService.class);
        UserService userService = context.getBean(UserService.class);
        ReviewService reviewService = context.getBean(ReviewService.class);

        Scanner input = new Scanner(System.in);

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

            try {
                int pick = input.nextInt();
                input.nextLine();

                if (pick == 0)
                    break;

                switch (pick) {
                    case 1:
                        
                        List<Game> games = gameService.getAllGames();
                        for (Game g : games) {
                            System.out.println("Game: " + g.getTitle() + " | Genre: " + g.getGenre());
                        }
                        break;

                    case 2:
                        
                        System.out.print("Enter game ID: ");
                        int gameId = input.nextInt();
                        input.nextLine();
                        try {
                            Game game = gameService.getGameById(gameId);
                            System.out.println("Title: " + game.getTitle());
                            System.out.println("Developer: " + game.getDeveloper());
                            System.out.println("Reviews:");
                            for (Review r : game.getReviews()) {
                                System.out.println("- " + r.getRating() + "/5: " + r.getComment());
                            }
                        } catch (GameNotFoundException e) {
                            System.out.println("Game not found.");
                        }
                        break;

                    case 3:
                        
                        System.out.print("Enter your user ID: ");
                        int userId = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter game ID: ");
                        int gameIdForReview = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter rating (1-5): ");
                        int rating = input.nextInt();
                        input.nextLine();
                        System.out.print("Enter comment: ");
                        String comment = input.nextLine();

                        try {
                            User user = userService.getUserById(userId);
                            Game gameToReview = gameService.getGameById(gameIdForReview);
                            Review review = new Review(rating, comment, user, gameToReview);
                            reviewService.addReview(review);
                            System.out.println("Review added.");
                        } catch (UserNotFoundException | GameNotFoundException e) {
                            System.out.println("Invalid user or game ID.");
                        }
                        break;

                    case 4:
                        
                        System.out.print("Enter review ID to delete: ");
                        int reviewIdToDelete = input.nextInt();
                        input.nextLine();
                        Review reviewToDelete = new Review();
                        reviewToDelete.setId(reviewIdToDelete);
                        reviewService.deleteReview(reviewToDelete);
                        System.out.println("Review deleted.");
                        break;

                    case 5:
                        
                        System.out.print("Enter review ID to update: ");
                        int reviewId = input.nextInt();
                        input.nextLine();
                        System.out.print("New rating (1-5): ");
                        int newRating = input.nextInt();
                        input.nextLine();
                        System.out.print("New comment: ");
                        String newComment = input.nextLine();

                        Review updatedReview = new Review();
                        updatedReview.setRating(newRating);
                        updatedReview.setComment(newComment);

                        try {
                            reviewService.updateReview(reviewId, updatedReview);
                            System.out.println("Review updated.");
                        } catch (ReviewNotFoundException e) {
                            System.out.println("Review not found.");
                        }
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                input.nextLine(); 
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        }

        input.close();
        context.close();

    }
}
