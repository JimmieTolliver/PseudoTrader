/*
 * Filename: Account.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */

package com.jimmietolliver.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.jimmietolliver.jpa.services.HoldingServices;

import java.util.List;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name = "account")
@NamedQueries({ @NamedQuery(name = "findAll", query = "select a from Account a"),
		@NamedQuery(name = "getAllUserNames", query = "select a.username from Account a"),
		@NamedQuery(name = "GetFirstName", query = "SELECT f FROM Account f WHERE :username = f.username"),
		@NamedQuery(name= "GetAccountNumber", query = "SELECT f.id FROM Account f WHERE :username = f.username"),
		@NamedQuery(name = "getAccountHoldings", query = "SELECT h FROM Holding h, Account a WHERE :id = a.id"),
		@NamedQuery(name="getTickersById", query="SELECT t FROM Holding t WHERE :id = t.id"),
		@NamedQuery(name="getAccountPassword", query = "SELECT p.password FROM Account p WHERE :username = p.username")
})
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	private String password;

	@Column(name = "user_name")
	private String username;

	// bi-directional one-to-one association to Cash
	@OneToOne(mappedBy = "account")
	private Cash cash;

	// bi-directional many-to-one association to Holding
	@OneToMany(mappedBy = "account")
	private List<Holding> holdings;

	// bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions;

	public Account() {
	}
	
	/**
	 * @param id
	 */
	public Account(Long id) {
		this.setId(id);
		HoldingServices holdings = new HoldingServices();
		this.setHoldings(holdings.getHoldingsByAccountId(id));
	}

	/**
	 * @param id
	 * @param holdings
	 * @param transactions
	 */
	public Account(Long id, List<Holding> holdings) {
		this.setId(id);
		this.setHoldings(holdings);
	}

	/**
	 * @param id
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param username
	 * @param cash
	 * @param holdings
	 * @param transactions
	 */
	public Account(Long id, String email, String firstName, String lastName, String password, String username, Cash cash,
			List<Holding> holdings, List<Transaction> transactions) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.username = username;
		this.cash = cash;
		this.holdings = holdings;
		this.transactions = transactions;
	}

	/**
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param username
	 */
	public Account(String email, String firstName, String lastName, String password, String username) {
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setUsername(username);
	}

	/**
	 * @param id
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param username
	 */
	public Account(Long id, String email, String firstName, String lastName, String password, String username) {

		this.setId(id);;
		this.setEmail(email);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setPassword(password);
		this.setUsername(username);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Cash getCash() {
		return this.cash;
	}

	public void setCash(Cash cash) {
		this.cash = cash;
	}

	public List<Holding> getHoldings() {
		return this.holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

	public Holding addHolding(Holding holding) {
		getHoldings().add(holding);
		holding.setAccount(this);

		return holding;
	}

	public Holding removeHolding(Holding holding) {
		getHoldings().remove(holding);
		holding.setAccount(null);

		return holding;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setAccount(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setAccount(null);

		return transaction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", username=");
		builder.append(username);
		builder.append(", cash=");
		builder.append(cash);
		builder.append(", holdings=");
		builder.append(holdings);
		builder.append(", transactions=");
		builder.append(transactions);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cash == null) ? 0 : cash.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((holdings == null) ? 0 : holdings.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((transactions == null) ? 0 : transactions.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (cash == null) {
			if (other.cash != null)
				return false;
		} else if (!cash.equals(other.cash))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (holdings == null) {
			if (other.holdings != null)
				return false;
		} else if (!holdings.equals(other.holdings))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (transactions == null) {
			if (other.transactions != null)
				return false;
		} else if (!transactions.equals(other.transactions))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}