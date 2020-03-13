/*
 * Filename: PortfolioServlet.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Holding;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.utilities.StockQuote;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class PortfolioServlet
 */
@WebServlet("/PortfolioServlet")
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PortfolioServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long accountId = (Long) session.getAttribute("accountId");

		AccountServices accountService = new AccountServices();
		Account account = accountService.getAccountByNumber(accountId);
		Set<Holding> holdings = new HashSet<Holding>();
		List<Holding> holdingsList = account.getHoldings();
		for (Holding holding : holdingsList) {
			System.out.println(holding.getStock().getTicker());
			if (holding.getNumShares() > 0) {
				holdings.add(holding);
			}
		}

		request.setAttribute("holdings", holdings);

		Map<String, Double> price = new HashMap<String, Double>();
		Map<String, Double> value = new HashMap<String, Double>();
		Double totalStockValue = 0.0;

		String ticker = null;
		for (Holding holding : holdings) {
			ticker = holding.getStock().getTicker();
			request.setAttribute("ticker", ticker);
			request.setAttribute("numShares", holding.getNumShares());
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
		request.setAttribute("price", price);
		request.setAttribute("value", value);
		session.setAttribute("totalStockValue", totalStockValue);

		// Get Account Cash Info
		Double currCash = accountService.getAccountByNumber(accountId).getCash().getCurrCashVal();
		Double initialCash = accountService.getAccountByNumber(accountId).getCash().getInitialCashVal();
		session.setAttribute("currCash", currCash);
		session.setAttribute("intitialCash", initialCash);

		accountService.cleanup();

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/landing.jsp");
		rd.forward(request, response);
	}

}
