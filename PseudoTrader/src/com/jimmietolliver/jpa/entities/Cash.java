/*
 * Filename: Cash.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


/**
 * The persistent class for the cash database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Cash.findAll", query="SELECT c FROM Cash c"),
	@NamedQuery(name = "getCash", query = "SELECT h FROM Cash h, Account a WHERE :id = a.id")
})
public class Cash implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="curr_cash_val")
	private Double currCashVal;

	@Column(name="initial_cash_val")
	private Double initialCashVal;

	//bi-directional one-to-one association to Account
	@OneToOne
	private Account account;

	public Cash() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCurrCashVal() {
		return this.currCashVal;
	}

	public void setCurrCashVal(Double d) {
		this.currCashVal = d;
	}

	public Double getInitialCashVal() {
		return this.initialCashVal;
	}

	public void setInitialCashVal(Double initialCashVal) {
		this.initialCashVal = initialCashVal;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}