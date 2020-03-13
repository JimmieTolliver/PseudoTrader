/*
 * Filename: HoldingServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Holding;

/**
 * @author Jimmie Tolliver
 *
 */
public class HoldingServices extends AbstractSevices implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long accountId;

	/**
	 * 
	 */
	public HoldingServices() {
	}


	/**
	 * @param Long id
	 * @return List<Holding>
	 * Gets holdings by account number
	 */
	public List<Holding> getHoldingsByAccountId(Long id) {
		this.accountId = id;

		Query query = em.createNamedQuery("getHoldingsByAccount");
		query.setParameter("id", accountId);
		@SuppressWarnings("unchecked")
		List<Holding> holdings = query.getResultList();
		return holdings;
	}

	
	/**
	 * @return void add new stock to stocks table
	 * @param newHolding
	 */
	public void create(Holding newHolding) {
		em.getTransaction().begin();
		em.persist(newHolding);
		em.getTransaction().commit();
		cleanup();
	}
}
