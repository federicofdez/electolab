package es.upm.dit.isst.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFService {

	
	private static final EntityManagerFactory emflnstance = Persistence
			.createEntityManagerFactory("transactions-optional");
	private EMFService(){}

	public static EntityManagerFactory get() {
		return emflnstance;
	}

}