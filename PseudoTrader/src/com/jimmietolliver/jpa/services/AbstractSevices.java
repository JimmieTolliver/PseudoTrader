/*
 * Filename: AbstractServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Jimmie Tolliver
 *
 */
public abstract class AbstractSevices {
	protected EntityManagerFactory emf;
	protected EntityManager em;
	
	public AbstractSevices() {
		this("PseudoTrader");
	}
	
	public AbstractSevices(String PU) {
		emf= Persistence.createEntityManagerFactory(PU);
		em = emf.createEntityManager();
	}
	
	public void cleanup() {
		em.close();
		emf.close();
	}
}
