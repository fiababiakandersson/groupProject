package se.yrgo.client;

import java.util.*;

import org.springframework.context.support.*;

import se.yrgo.domain.*;
import se.yrgo.exception.*;
import se.yrgo.initialize.*;
import se.yrgo.services.*;

public class Client {

    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml")) {

            DataInitializer initializer = context.getBean(DataInitializer.class);
            initializer.initialize();

            GameService gameService = context.getBean(GameService.class);
            UserService userService = context.getBean(UserService.class);
            ReviewService reviewService = context.getBean(ReviewService.class);

            // test adding game
            gameService.addGame(new Game("Skate 3", "Sport", "Blackbox"));

            try (Scanner input = new Scanner(System.in)) {

                while (true) {

                    System.out.println("");
                    for (User u : userService.getAllUsers()) {
                        System.out.println("Hello " + u.getUsername() + "! " + " | UserId: " + u.getId());
                    }
                    System.out.println("Welcome to Gamereview.com:");
                    System.out.println("[1] Show all games");
                    System.out.println("[2] Show game details");
                    System.out.println("[3] Write a review");
                    System.out.println("[4] Remove a review");
                    System.out.println("[5] Update a review");
                    System.out.println("[6] Show all review");

                    try {
                        int pick = input.nextInt();
                        input.nextLine();

                        if (pick == 0)
                            break;

                        switch (pick) {
                            case 1:

                                List<Game> games = gameService.getAllGames();
                                for (Game g : games) {
                                    System.out.println("Game: " + g.getTitle() + " | Genre: " + g.getGenre() + " | Id: "
                                            + g.getId());
                                }
                                break;

                            case 2:

                                System.out.print("Enter game ID: ");
                                int gameId = input.nextInt();
                                input.nextLine().trim();
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
                                    gameToReview.getReviews().add(review);
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

                                try {
                                    Review reviewToDelete = reviewService.getReviewById(reviewIdToDelete);
                                    reviewService.deleteReview(reviewToDelete);
                                    System.out.println("Review deleted.");
                                } catch (ReviewNotFoundException e) {
                                    System.out.println("Review not found.");
                                }
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

                                try {
                                    Review existingReview = reviewService.getReviewById(reviewId);
                                    existingReview.setRating(newRating);
                                    existingReview.setComment(newComment);
                                    reviewService.updateReview(reviewId, existingReview);
                                    System.out.println("Review updated.");
                                } catch (ReviewNotFoundException e) {
                                    System.out.println("Review not found.");
                                }
                                break;

                            case 6:
                                List<Review> allReviews = reviewService.getAllReviews();
                                for (Review r : allReviews) {
                                    String gameTitle = (r.getGame() != null) ? r.getGame().getTitle() : "okänt spel";
                                    String userName = (r.getUser() != null) ? r.getUser().getUsername()
                                            : "okänd användare";
                                    System.out.println("[ID: " + r.getId() + "] " + r.getRating() + "/5 - \""
                                            + r.getComment() + "\" (" + gameTitle + ", " + userName + ")");
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
            } catch (Exception e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
