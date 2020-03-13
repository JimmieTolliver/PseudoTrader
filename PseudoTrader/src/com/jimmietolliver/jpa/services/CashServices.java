/*
 * Filename: CashServices.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.services;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import com.jimmietolliver.jpa.entities.Cash;

/**
 * @author Jimmie Tolliver
 *
 */
public class CashServices extends AbstractSevices implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long accountId;
	private double intitialCashVal;
	private double currCashVal;
	/**
	 * 
	 */
	public CashServices() {
	}
	
	
	/**
	 * @param cash
	 * @return void
	 * Updates cash balance in DB
	 */
	public void updateCash(Cash cash) {
		em.getTransaction().begin();
		em.merge(cash);
		em.getTransaction().commit();
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
	 * @return the intitialCashVal
	 */
	public double getIntitialCashVal() {
		return intitialCashVal;
	}
	/**
	 * @param intitialCashVal the intitialCashVal to set
	 */
	public void setIntitialCashVal(double intitialCashVal) {
		this.intitialCashVal = intitialCashVal;
	}
	/**
	 * @return the currCashVal
	 */
	public double getCurrCashVal() {
		return currCashVal;
	}
	/**
	 * @param currCashVal the currCashVal to set
	 */
	public void setCurrCashVal(double currCashVal) {
		this.currCashVal = currCashVal;
	}
	@Override
	public String toString() {
		return "CashServices [id=" + id + ", accountId=" + accountId + ", intitialCashVal=" + intitialCashVal
				+ ", currCashVal=" + currCashVal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountId == null) ? 0 : accountId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(currCashVal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(intitialCashVal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		CashServices other = (CashServices) obj;
		if (accountId == null) {
			if (other.accountId != null)
				return false;
		} else if (!accountId.equals(other.accountId))
			return false;
		if (Double.doubleToLongBits(currCashVal) != Double.doubleToLongBits(other.currCashVal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(intitialCashVal) != Double.doubleToLongBits(other.intitialCashVal))
			return false;
		return true;
	}
	

}
