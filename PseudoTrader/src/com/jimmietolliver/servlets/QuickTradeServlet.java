/*
 * Filename: QuickTradeServlet.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.utilities.Trade;

/**
 * Servlet implementation class TradeServlet
 */
@WebServlet("/QuickTradeServlet")
public class QuickTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuickTradeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Long accountId = (Long) session.getAttribute("accountId");

		AccountServices accountNum = new AccountServices();
		Account accNum = accountNum.getAccountByNumber(accountId);

		Long numShares = Long.parseLong(request.getParameter("qty"));
		System.out.println(numShares);

		String trade = request.getParameter("trade");
		String trades[] = trade.split("_");
		for (String s : trades) {
			System.out.println(s);
		}
		String ticker = trades[0];
		Double stockPrice = Double.parseDouble(trades[1]);
		String tradeType = trades[2];

		int validTrade;
		Trade t = new Trade(accNum.getId(), ticker, numShares, stockPrice);
		if (tradeType.equals("buy")) {
			validTrade = t.buyStock();
		} else {
			validTrade = t.sellStock();
		}

		System.out.println("Done");

		request.setAttribute("ticker", ticker);
		request.setAttribute("tradeType", tradeType);
		request.setAttribute("stockPrice", stockPrice);
		request.setAttribute("numShares", numShares);

		if (validTrade == 0) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/quicktrade.jsp");
			rd.forward(request, response);
		} else {
			String message;
			if (tradeType.contentEquals("buy")) {
				message = "Sorry you don't have enough cash to buy " + numShares + " shares of " + ticker;
			} else {
				message = "Sorry you don't own enough shares of " + ticker;
			}

			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/invalidtransaction.jsp");
			rd.forward(request, response);
		}
	}

}
