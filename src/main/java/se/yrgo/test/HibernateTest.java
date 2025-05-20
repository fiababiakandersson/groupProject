package se.yrgo.test;

import jakarta.persistence.*;

public class HibernateTest {
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("databaseConfig");

	public static void main(String[] args) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		tx.commit();
		em.close();
	}

}
