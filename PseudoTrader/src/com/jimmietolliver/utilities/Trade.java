/*
 * Filename: Trade.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.utilities;

import java.util.ArrayList;
import java.util.List;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Holding;
import com.jimmietolliver.jpa.entities.Stock;
import com.jimmietolliver.jpa.entities.Transaction;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.jpa.services.StockServices;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Jimmie Tolliver
 *
 */
public class Trade {
	private String ticker;
	private Long numShares;
	private Double stockPrice;
	private Long accountId;

	/**
	 * 
	 */
	public Trade() {

	}

	/**
	 * @param ticker
	 * @param numShares
	 * @param account
	 * @param stockPrice
	 */
	public Trade(Long accountId, String ticker, Long numShares, Double stockPrice) {
		this.ticker = ticker;
		this.numShares = numShares;
		this.accountId = accountId;
		this.stockPrice = stockPrice;
	}

	public int buyStock() {
		// BUY STOCK ALGORITHM

		// Return 0 if valid -1 if invalid
		int validTrade = 0;

		// GET ACCOUNT NUMBER
		AccountServices accountNum = new AccountServices();
		Account accNum = accountNum.getAccountByNumber(accountId);

		// GET CURRENT CASH VALUE
		AccountServices account = new AccountServices();
		Long accountId = accNum.getId();
		Double currCash = account.getAccountByNumber(accountId).getCash().getCurrCashVal();

		// ENOUGH CASH FOR TRANSACTION?
		Stock newStock = new Stock(ticker);

		if (currCash.doubleValue() >= stockPrice * numShares) {

			// CHECK ID STOCK IN STOCKS TABLE
			StockServices stocks = new StockServices();
			if (!stocks.contains(ticker)) {
				StockQuote quote = new StockQuote(ticker);
				HttpResponse<JsonNode> stockData;
				String name = null;
				try {
					stockData = quote.getFinancialData();
					name = stockData.getBody().getObject().getJSONObject("price").getString("shortName");
				} catch (UnirestException e) {
					e.printStackTrace();
				}
				if (name.length() > 30) {
					newStock.setName(name.substring(0, 29));
				} else {
					newStock.setName(name);
				}
				newStock.setClosePrice(42.56); // TODO remove from DB - not needed
				stocks.create(newStock);
			} else {
				newStock.setName(stocks.getStockByTicker(ticker).getName());
				newStock.setClosePrice(stocks.getStockByTicker(ticker).getClosePrice());
			}

			// HOLDINGS TABLE

			Holding newHolding = new Holding();
			newHolding.setId(newStock.getId());
			newHolding.setAccount(accNum);
			newHolding.setStock(stocks.getStockByTicker(ticker));

			List<Holding> h = accNum.getHoldings();
			List<String> tkrs = new ArrayList<String>();
			for (Holding i : h) {
				tkrs.add(i.getStock().getTicker());
			}

			if (tkrs.contains(ticker)) {
				for (Holding i : h) {
					if (i.getStock().getTicker().equals(ticker)) {
						i.setNumShares(numShares + stocks.getNumSharesByStockId((i.getId())));
					}
				}

			} else {
				newHolding.setNumShares(numShares);
				accNum.addHolding(newHolding);
			}
			accountNum.updateAccount(accNum);

			stocks.cleanup();

			// TRANSACTION TABLE CODE
			Transaction newTransaction = new Transaction();
			newTransaction.setAccount(accNum);
			newTransaction.setStock(newHolding.getStock());
			newTransaction.setTradePrice(stockPrice);
			newTransaction.setTradeShares(numShares);
			newTransaction.setBuy(true);

			accNum.addTransaction(newTransaction);
//				END TRANSACTION TABLE CODE

			// UPDATE CASH TABLE
			account.getAccountByNumber(accountId).getCash().setCurrCashVal(currCash - stockPrice * numShares);
			account.updateAccount(accNum);

		} else {

			validTrade = -1;
		}
		
		accountNum.cleanup();

		return validTrade;
	}

	public int sellStock() {
		// GET ACCOUNT NUMBER
		AccountServices accountService = new AccountServices();
		Account account = accountService.getAccountByNumber(accountId);

		// GET ACCOUNT CASH
		Long accountId = account.getId();
		Double currCash = accountService.getAccountByNumber(accountId).getCash().getCurrCashVal();

		// GET CURRENT NUMBER OF SHARES
		List<Holding> holdings = account.getHoldings();
		Long holdShares = 0L;
		Holding newHolding = null;
		int validTrade = 0;
		for (Holding holding : holdings) {
			if (holding.getStock().getTicker().equals(ticker)) {
				holdShares = holding.getNumShares();
				// ENOUGH SHARES TO TRADE?
				if (holdShares >= numShares) {
					holding.setNumShares(holdShares - numShares); // SET SHARES TO NEW VALUE
					newHolding = holding;

					// TRANSACTION TABLE CODE
					Transaction newTransaction = new Transaction();
					newTransaction.setAccount(account);
					newTransaction.setStock(newHolding.getStock());
					newTransaction.setTradePrice(stockPrice);
					newTransaction.setTradeShares(numShares);
					newTransaction.setBuy(false);
					account.addTransaction(newTransaction);
					// END TRANSACTION TABLE CODE

					// UPDATE CASH
					account.getCash().setCurrCashVal(currCash + numShares * stockPrice);
				} else {
					validTrade = -1;
				}
			}
		}
		accountService.updateAccount(account);
		accountService.cleanup();
		return validTrade;
	}

}
