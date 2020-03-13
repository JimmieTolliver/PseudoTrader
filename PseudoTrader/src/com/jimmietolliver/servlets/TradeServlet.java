/*
 * Filename: TradeServlet.java
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

import com.jimmietolliver.utilities.StockQuote;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class Trade
 */
@WebServlet("/TradeServlet")
public class TradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TradeServlet() {

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
		String ticker = request.getParameter("trade");
		request.setAttribute("ticker", ticker.toUpperCase());

		StockQuote quote = new StockQuote(ticker);
		Double currPrice = null;
		String stockName = null;
		try {
			stockName = quote.getName();
		} catch (UnirestException e1) {
			e1.printStackTrace();
		}
		try {
			currPrice = quote.getQuote();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		request.setAttribute("stockName", stockName);
		request.setAttribute("currPrice", currPrice);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/trade.jsp");
		rd.forward(request, response);
	}

}
