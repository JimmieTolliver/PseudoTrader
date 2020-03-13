/*
 * Filename: TransactionServlet.java
* Author: Jimmie Tolliver
* 03/12/2020 
 */
package com.jimmietolliver.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Transaction;
import com.jimmietolliver.jpa.services.AccountServices;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransactionServlet() {

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
		List<Transaction> transactions = account.getTransactions();
		for (Transaction transaction : transactions) {
			System.out.println("Ticker: " + transaction.getStock().getTicker());
			System.out.println("Name: " + transaction.getStock().getName());
			System.out.println("Date: " + transaction.getDate());
			System.out.println("Shares: " + transaction.getTradeShares());
			System.out.println("Trade Type: " + transaction.getBuy());
			System.out.println("Trade Price: " + transaction.getTradePrice());
			System.out.println("Amount: " + transaction.getTradeShares() * transaction.getTradePrice());
		}

		request.setAttribute("transactions", transactions);

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/transactions.jsp");
		rd.forward(request, response);
	}

}
