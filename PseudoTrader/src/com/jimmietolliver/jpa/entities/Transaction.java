/*
 * Filename: Transaction.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the transactions database table.
 * 
 */
@Entity
@Table(name="transactions")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private boolean buy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name="trade_price")
	private Double tradePrice;
	
	@Column(name="trade_shares")
	private Long tradeShares;

	//bi-directional many-to-one association to Account
	@ManyToOne
	private Account account;

	//bi-directional many-to-one association to Stock
	@ManyToOne
	private Stock stock;

	public Transaction() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getBuy() {
		return this.buy;
	}

	public void setBuy(boolean buy) {
		this.buy = buy;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getTradePrice() {
		return this.tradePrice;
	}

	public void setTradePrice(Double stockPrice) {
		this.tradePrice = stockPrice;
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

	/**
	 * @return the tradeShares
	 */
	public Long getTradeShares() {
		return tradeShares;
	}

	/**
	 * @param tradeShares the tradeShares to set
	 */
	public void setTradeShares(Long tradeShares) {
		this.tradeShares = tradeShares;
	}

}