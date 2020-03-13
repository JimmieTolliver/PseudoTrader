/*
 * Filename: Holding.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the holdings database table.
 * 
 */
@Entity
@Table(name="holdings")
@NamedQueries({
@NamedQuery(name="allHoldings", query="SELECT h FROM Holding h"),
@NamedQuery(name="getHoldingsByAccount", query="SELECT h FROM Holding h, Account a WHERE :id = a.id")
})
public class Holding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="num_shares")
	private Long numShares;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Stock
	@ManyToOne
	private Stock stock;

	public Holding() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumShares() {
		return this.numShares;
	}

	public void setNumShares(Long numShares) {
		this.numShares = numShares;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}