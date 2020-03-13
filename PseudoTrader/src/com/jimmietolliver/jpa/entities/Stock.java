/*
 * Filename: Stock.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the stocks database table.
 * 
 */
@Entity
@Table(name="stocks")
@NamedQueries({
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s"),
@NamedQuery(name="getAllTickers", query="SELECT s.ticker FROM Stock s"),
@NamedQuery(name="getStockByTicker", query="SELECT s FROM Stock s WHERE :id = s.ticker"),
@NamedQuery(name="getNumSharesByStockId", query= "SELECT n.numShares FROM Holding n WHERE :id = n.id")
})
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="close_price")
	private double closePrice;

	private String name;

	private String ticker;

	//bi-directional many-to-one association to Holding
	@OneToMany(mappedBy="stock")
	private List<Holding> holdings;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="stock")
	private List<Transaction> transactions;

	public Stock() {
	}
	
	public Stock(String ticker) {
		this.setTicker(ticker.toUpperCase());
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getClosePrice() {
		return this.closePrice;
	}

	public void setClosePrice(double d) {
		this.closePrice = d;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public List<Holding> getHoldings() {
		return this.holdings;
	}

	public void setHoldings(List<Holding> holdings) {
		this.holdings = holdings;
	}

	public Holding addHolding(Holding holding) {
		getHoldings().add(holding);
		holding.setStock(this);

		return holding;
	}

	public Holding removeHolding(Holding holding) {
		getHoldings().remove(holding);
		holding.setStock(null);

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
		transaction.setStock(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setStock(null);

		return transaction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Stock [id=");
		builder.append(id);
		builder.append(", closePrice=");
		builder.append(closePrice);
		builder.append(", name=");
		builder.append(name);
		builder.append(", ticker=");
		builder.append(ticker);
		builder.append(", holdings=");
		builder.append(holdings);
		builder.append(", transactions=");
		builder.append(transactions);
		builder.append("]");
		return builder.toString();
	}

}