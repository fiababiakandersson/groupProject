package se.yrgo.client;

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

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		tx.commit();
		em.close();
	}

}
