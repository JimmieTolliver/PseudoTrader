/*
 * Filename: AccountServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Holding;

/**
 * @author Jimmie Tolliver
 *
 */
@SuppressWarnings("unchecked")
public class AccountServices extends AbstractSevices implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long accountId;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;

	public AccountServices() {
		super();
	}

	public String getFirstName(String userName) {
		Query query = em.createNamedQuery("GetFirstName");
		query.setParameter("username", userName);
		List<Account> account = query.getResultList();
		String firstName = null;
		try {
			firstName = account.get(0).getFirstName();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return firstName;
	}
	
	public String getAccountPassword(String username) {
		Query query = em.createNamedQuery("getAccountPassword");
		query.setParameter("username", username);
		List<String> account = query.getResultList();
		System.out.println(account);
				
		return account.get(0);
	}

	public List<Account> getAccountHoldings(Long accountId) {
		Query query = em.createNamedQuery("getAccountHoldings");
		query.setParameter("id", accountId);
		List<Account> account = query.getResultList();
		return account;
	}

	public List<String> getTickersByAccountId(Long accountId) {
		Query query = em.createNamedQuery("getTickersById");
		query.setParameter("id", accountId);
		List<Holding> holdings = query.getResultList();
		List<String> tickers = new ArrayList<String>();
		for (Holding h : holdings) {
			tickers.add(h.getStock().getTicker());
		}
		return tickers;
	}

	public Account getAccountByNumber(Long id) {
		return em.find(Account.class, id);
	}

	public Long getAccountNumberByUserName(String userName) {
		Query query = em.createNamedQuery("GetAccountNumber");
		query.setParameter("username", userName);
		return (Long) query.getSingleResult();
	}

	public void updateAccount(Account account) {
		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();
	}

	public List<Account> findAll() {
//		Query query = em.createNamedQuery("findAll");
//		List<Account> account = query.getResultList();
//		return account;
		return em.createNamedQuery("findAll").getResultList(); // TODO replace other namedquery methods like this
	}
	
	public List<String> getAllUserNames(){
		return em.createNamedQuery("getAllUserNames").getResultList();
	}

	@Override
	public String toString() {
		return "AccountServices [accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

}
