/*
 * Filename: AccountServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Account;

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

	/**
	 * @param userName
	 * @return String
	 * Gets firstname by userName
	 */
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
	
	
	/**
	 * @param username
	 * @return String
	 * Gets password by username
	 */
	public String getAccountPassword(String username) {
		Query query = em.createNamedQuery("getAccountPassword");
		query.setParameter("username", username);
		List<String> account = query.getResultList();
		System.out.println(account);
				
		return account.get(0);
	}

	/**
	 * @param id
	 * @return Account
	 * Gets account by id
	 */
	public Account getAccountByNumber(Long id) {
		return em.find(Account.class, id);
	}

	/**
	 * @param userName
	 * @return Long
	 * Gets account number by user name
	 */
	public Long getAccountNumberByUserName(String userName) {
		Query query = em.createNamedQuery("GetAccountNumber");
		query.setParameter("username", userName);
		return (Long) query.getSingleResult();
	}

	/**
	 * @param account
	 * @return void
	 * Updates account info in database
	 */
	public void updateAccount(Account account) {
		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();
	}
	
	/**
	 * @return List<String>
	 * Returns all usernames
	 */
	public List<String> getAllUserNames(){
		return em.createNamedQuery("getAllUserNames").getResultList();
	}

	
	@Override
	public String toString() {
		return "AccountServices [accountId=" + accountId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

}
