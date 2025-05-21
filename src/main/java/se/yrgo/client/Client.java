package se.yrgo.client;

import java.util.*;

import jakarta.persistence.*;

public class Client {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

	public static void main(String[] args) {
		// menu options:
		// 1. show all games
		// 2. select a game (and show details and their reviews)
		// 3. write and add a review on a game
		// 4. remove your own review
		// 5. update your own review

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

			EntityManager em = emf.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			tx.commit();
			em.close();
		}

	}
}
