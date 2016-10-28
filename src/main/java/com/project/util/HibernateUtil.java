package com.project.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	private static EntityManagerFactory emf ;


	public static EntityManager getEntityManager() {

		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("default");
		}
		return emf.createEntityManager();
	}
}