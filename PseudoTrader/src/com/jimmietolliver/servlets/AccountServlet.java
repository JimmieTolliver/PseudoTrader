/*
 * Filename: AccountServlet.java
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

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Cash;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.jpa.services.CashServices;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {

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

		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password = null;
		String email = request.getParameter("email");

		request.setAttribute("username", username);
		request.setAttribute("password1", password1);
		request.setAttribute("password2", password2);
		request.setAttribute("email", email);

		// CHECK TO MAKE SURE USERNAME PASSWORD UNIQUE
		AccountServices accountService = new AccountServices();
		List<String> userNames = accountService.getAllUserNames();
		if (userNames.contains(username)) {
			String message = "Sorry username already in use. Try again.";
			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/newuser.jsp");
			rd.include(request, response);
		}

		// Make sure passwords match
		else if (!password1.equals(password2)) {
			String message = "Passwords don't match, try again";
			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/newuser.jsp");
			rd.include(request, response);
		}

		else {
			password = password1;

			Account newAccount = new Account(email, firstName, lastName, password, username);
			accountService.updateAccount(newAccount);

			// GET AUTO GENERATED ACCOUNT_ID FROM DATABASE
			Long accountId = accountService.getAccountNumberByUserName(username);
			newAccount = new Account(accountId);
			Cash newCash = new Cash();
			newCash.setCurrCashVal(10000.00);
			newCash.setInitialCashVal(10000.00);
			newCash.setAccount(newAccount);

			CashServices cashService = new CashServices();
			cashService.updateCash(newCash);

			// Clean up services
			cashService.cleanup();
			accountService.cleanup();

			request.setAttribute("firstName", firstName);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/newuserlogin.jsp");
			rd.forward(request, response);
		}
	}

}
