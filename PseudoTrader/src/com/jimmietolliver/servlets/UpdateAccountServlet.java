/*
 * Filename: UpdateAccountServlet.java
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

/**
 * Servlet implementation class UpdateAccountServlet
 */
@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountServlet() {

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String password = null;
		
		HttpSession session = request.getSession();
		Long accountId = (Long) session.getAttribute("accountId");
		
		AccountServices accountService = new AccountServices();
		Account account = accountService.getAccountByNumber(accountId);

		if(!password1.equals(password2)) {
			String message = "Passwords don't match, try again";
			System.out.println(message);
			request.setAttribute("message", message);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/updateaccount.jsp");
			rd.include(request, response);
		}
		
		else { 
			password = password1;
			account.setPassword(password);
			accountService.updateAccount(account);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/accountupdated.jsp");
			rd.forward(request, response);
		}
		
		accountService.cleanup();
	
	}

}
