/*
 * Filename: GetServlet.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jimmietolliver.jpa.entities.Holding;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.utilities.StockQuote;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Jimmie Tolliver
 *
 */

//@WebServlet("/GetSevlet")
public class GetServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		AccountServices account = new AccountServices();
		HttpSession session = req.getSession();
		Long accountId = (Long) session.getAttribute("accountId");

		List<Holding> holdingsAll = account.getAccountByNumber(accountId).getHoldings();
		List<Holding> holdings = new ArrayList<Holding>();
		for (Holding holding : holdingsAll) {
			if (holding.getNumShares() > 0) {
				holdings.add(holding);
			}
		}

		req.setAttribute("holdings", holdings);

		Map<String, Double> price = new HashMap<String, Double>();
		Map<String, Double> value = new HashMap<String, Double>();
		Double totalStockValue = 0.0;

		String ticker = null;
		for (Holding holding : holdings) {
			ticker = holding.getStock().getTicker();
			req.setAttribute("ticker", ticker);
			req.setAttribute("numShares", holding.getNumShares());
			StockQuote quote = new StockQuote(holding.getStock().getTicker());
			try {
				Double currPrice = quote.getQuote();
				price.put(ticker, currPrice);
				Double currValue = currPrice * holding.getNumShares();
				value.put(ticker, currValue);
				totalStockValue += currValue;
			} catch (UnirestException e) {
				e.printStackTrace();
			}
		}
		req.setAttribute("price", price);
		req.setAttribute("value", value);
		req.setAttribute("totalStockValue", totalStockValue);

		// Get Account Cash Info
		Double currCash = account.getAccountByNumber(accountId).getCash().getCurrCashVal();
		Double initialCash = account.getAccountByNumber(accountId).getCash().getInitialCashVal();
		req.setAttribute("currCash", currCash);
		req.setAttribute("intitialCash", initialCash);

		account.cleanup();

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/landing.jsp");
		rd.include(req, resp);
	}

}
