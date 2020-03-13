/*
 * Filename: AuthServlet.java
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

import com.jimmietolliver.jpa.services.AccountServices;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AuthServlet() {

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

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		AccountServices account = new AccountServices();
		String firstName = null;
		String pword = "";

		try {
			firstName = account.getFirstName(username).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (firstName == null) {
			System.out.println("invalid username");
		} else {
			pword = account.getAccountPassword(username);
			System.out.println(pword);
		}

		System.out.println(firstName);

		if (!pword.equals(password) || firstName == null) {
			String message = "Invalid username or password. Try again.";
			System.out.println(message);
			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/index.jsp");
			rd.include(request, response);
		} else {
			System.out.println("Welcome " + firstName);

			// Set session info
			Long accountId = account.getAccountNumberByUserName(username);
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("firstName", firstName);
			session.setAttribute("accountId", accountId);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/getServlet");
			rd.include(request, response);
		}

	}

}
