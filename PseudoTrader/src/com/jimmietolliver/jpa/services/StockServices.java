/*
 * Filename: StockServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Stock;

/**
 * @author Jimmie Tolliver
 *
 */
public class StockServices extends AbstractSevices implements Serializable {
	private static final long serialVersionUID = 1L;

	Long id;
	String ticker;
	String name;
	Double closePrice;

	/**
	 * 
	 */
	public StockServices() {

	}

	/**
	 * @param id
	 * @param ticker
	 * @param name
	 * @param closePrice
	 */
	public StockServices(Long id, String ticker, String name, Double closePrice) {
		this.id = id;
		this.ticker = ticker;
		this.name = name;
		this.closePrice = closePrice;
	}

	public boolean contains(String ticker) {
		Query query = em.createNamedQuery("getAllTickers");
		@SuppressWarnings("unchecked")
		List<String> stocks = query.getResultList();
		if (stocks.contains(ticker)) {
			return true;
		}
		return false;
	}

	/**
	 * @return void add new stock to stocks table
	 */
	public void create(Stock newStock) {
		em.getTransaction().begin();
		em.persist(newStock);
		em.getTransaction().commit();
	}

	/**
	 * @return Stock by ticker
	 */
	public Stock getStockByTicker(String ticker) {
		Query query = em.createNamedQuery("getStockByTicker");
		query.setParameter("id", ticker);
		return (Stock) query.getSingleResult();
	}

	/**
	 * @return Shares by id
	 */
	public Long getNumSharesByStockId(Long stockId) {
		Query query = em.createNamedQuery("getNumSharesByStockId");
		query.setParameter("id", stockId);
		return (Long) query.getSingleResult();
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
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the closePrice
	 */
	public Double getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice the closePrice to set
	 */
	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockServices [id=");
		builder.append(id);
		builder.append(", ticker=");
		builder.append(ticker);
		builder.append(", name=");
		builder.append(name);
		builder.append(", closePrice=");
		builder.append(closePrice);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((closePrice == null) ? 0 : closePrice.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
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
		StockServices other = (StockServices) obj;
		if (closePrice == null) {
			if (other.closePrice != null)
				return false;
		} else if (!closePrice.equals(other.closePrice))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}

}
