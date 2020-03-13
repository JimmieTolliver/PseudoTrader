/*
 * Filename: HoldingServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Holding;

/**
 * @author Jimmie Tolliver
 *
 */
public class HoldingServices extends AbstractSevices implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long holdingId;
	private Long accountId;

	/**
	 * 
	 */
	public HoldingServices() {
	}

	public Holding getHoldingsById(Long id) {
		this.holdingId = id;
		return em.find(Holding.class, holdingId);

	}

	public List<Holding> getHoldingsByAccountId(Long id) {
		this.accountId = id;

		Query query = em.createNamedQuery("getHoldingsByAccount");
		query.setParameter("id", accountId);
		@SuppressWarnings("unchecked")
		List<Holding> holdings = query.getResultList();
		return holdings;
	}

	public List<Holding> findAllHoldings() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PseudoTrader");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("allHoldings");

		@SuppressWarnings("unchecked")
		List<Holding> holdings = query.getResultList();
		em.close();
		emf.close();
		return holdings;
	}
	
	/**
	 * @return void add new stock to stocks table
	 */
	public void create(Holding newHolding) {
		em.getTransaction().begin();
		em.persist(newHolding);
		em.getTransaction().commit();
		cleanup();
	}
}
