/*
 * Filename: TransactionServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.sql.Timestamp;

import com.jimmietolliver.jpa.entities.Transaction;

/**
 * @author Jimmie Tolliver
 *
 */
public class TransactionServices extends AbstractSevices implements Serializable {
	private static final long serialVersionUID = 1L;

	Long id;
	Long accountId;
	Long stockId;
	Double tradePrice;
	boolean buy;
	Timestamp date;

	/**
	 * 
	 */
	public TransactionServices() {

	}

	/**
	 * @param id
	 * @param accountId
	 * @param stockId
	 * @param tradePrice
	 * @param buy
	 * @param date
	 */
	public TransactionServices(Long id, Long accountId, Long stockId, Double tradePrice, boolean buy, Timestamp date) {
		this.setId(id);
		this.setAccountId(accountId);
		this.setStockId(stockId);
		this.setTradePrice(tradePrice);
		this.setBuy(buy);
		this.setDate(date);
	}
	
	/**
	 * @param accountId
	 * @param stockId
	 * @param tradePrice
	 * @param buy
	 * @param date
	 */
	public TransactionServices(Long accountId, Long stockId, Double tradePrice, boolean buy, Timestamp date) {
		this.setAccountId(accountId);
		this.setStockId(stockId);
		this.setTradePrice(tradePrice);
		this.setBuy(buy);
		this.setDate(date);
	}
	
	/**
	 * @param newTransaction
	 * @return void
	 * Creates entry in transactions DB table
	 */
	public void create(Transaction newTransaction) {
		em.getTransaction().begin();
		em.persist(newTransaction);
		em.getTransaction().commit();
		cleanup();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the stockId
	 */
	public Long getStockId() {
		return stockId;
	}

	/**
	 * @param stockId the stockId to set
	 */
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	/**
	 * @return the tradePrice
	 */
	public Double getTradePrice() {
		return tradePrice;
	}

	/**
	 * @param tradePrice the tradePrice to set
	 */
	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	/**
	 * @return the buy
	 */
	public boolean isBuy() {
		return buy;
	}

	/**
	 * @param buy the buy to set
	 */
	public void setBuy(boolean buy) {
		this.buy = buy;
	}

	/**
	 * @return the date
	 */
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TransactionServices [id=");
		builder.append(id);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append(", stockId=");
		builder.append(stockId);
		builder.append(", tradePrice=");
		builder.append(tradePrice);
		builder.append(", buy=");
		builder.append(buy);
		builder.append(", date=");
		builder.append(date);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		result = prime * result + (buy ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((stockId == null) ? 0 : stockId.hashCode());
		result = prime * result + ((tradePrice == null) ? 0 : tradePrice.hashCode());
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
		TransactionServices other = (TransactionServices) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (buy != other.buy)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		if (tradePrice == null) {
			if (other.tradePrice != null)
				return false;
		} else if (!tradePrice.equals(other.tradePrice))
			return false;
		return true;
	}

}
