/*
 * Filename: QuoteServlet.java
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

import org.json.JSONException;
import org.json.JSONObject;

import com.jimmietolliver.utilities.StockQuote;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Servlet implementation class QuoteServlet
 */
@WebServlet("/QuoteServlet")
public class QuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QuoteServlet() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ticker = req.getParameter("ticker");
		String[] tkr = ticker.split(" ");

		req.setAttribute("ticker", tkr[0]);

		StockQuote quote = new StockQuote(tkr[0]);
		try {
			req.setAttribute("value", quote.getQuote());
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		HttpResponse<JsonNode> stockData = null;
		try {
			stockData = quote.getFinancialData();
		} catch (UnirestException e) {
			e.printStackTrace();
		}

		String name = "N/A";
		try {
			name = stockData.getBody().getObject().getJSONObject("price").getString("shortName");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String marketChange = "N/A";
		try {
			marketChange = stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketChange")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String marketChangePercent = "N/A";
		try {
			marketChangePercent = stockData.getBody().getObject().getJSONObject("price")
					.getJSONObject("regularMarketChangePercent").getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String yearChange = "N/A";
		try {
			yearChange = stockData.getBody().getObject().getJSONObject("defaultKeyStatistics")
					.getJSONObject("52WeekChange").getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String eps = "N/A";
		try {
			eps = stockData.getBody().getObject().getJSONObject("defaultKeyStatistics").getJSONObject("trailingEps")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String marketOpen = "N/A";
		try {
			marketOpen = stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketOpen")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String avgDailyVol = "N/A";
		try {
			avgDailyVol = stockData.getBody().getObject().getJSONObject("price")
					.getJSONObject("averageDailyVolume3Month").getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String prevClose = "N/A";
		try {
			prevClose = stockData.getBody().getObject().getJSONObject("price")
					.getJSONObject("regularMarketPreviousClose").getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String dayHigh = "N/A";
		try {
			dayHigh = stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketDayHigh")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String dayLow = "N/A";
		try {
			dayLow = stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketDayLow")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String yearHigh = "N/A";
		try {
			yearHigh = stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("fiftyTwoWeekHigh")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String yearLow = "N/A";
		try {
			yearLow = stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("fiftyTwoWeekLow")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String pe = "N/A";
		try {
			pe = stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("trailingPE")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String beta = "N/A";
		try {
			beta = stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("beta")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String marketCap = "N/A";
		try {
			marketCap = stockData.getBody().getObject().getJSONObject("price").getJSONObject("marketCap")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String volume = "N/A";
		try {
			volume = stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("volume")
					.getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String targetPrice = "N/A";
		try {
			targetPrice = stockData.getBody().getObject().getJSONObject("financialData")
					.getJSONObject("targetMeanPrice").getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String earningsDate = "N/A";
		try {
			earningsDate = ((JSONObject) stockData.getBody().getObject().getJSONObject("earnings")
					.getJSONObject("earningsChart").getJSONArray("earningsDate").get(0)).getString("fmt");
		} catch (JSONException e) {
			e.printStackTrace();
		}

		req.setAttribute("name", name);
		req.setAttribute("marketChange", marketChange);
		req.setAttribute("marketChangePercent", marketChangePercent);
		req.setAttribute("yearChange", yearChange);
		req.setAttribute("eps", eps);
		req.setAttribute("marketOpen", marketOpen);
		req.setAttribute("avgDailyVol", avgDailyVol);
		req.setAttribute("prevClose", prevClose);
		req.setAttribute("dayHigh", dayHigh);
		req.setAttribute("dayLow", dayLow);
		req.setAttribute("yearHigh", yearHigh);
		req.setAttribute("yearLow", yearLow);
		req.setAttribute("pe", pe);
		req.setAttribute("beta", beta);
		req.setAttribute("marketCap", marketCap);
		req.setAttribute("volume", volume);
		req.setAttribute("targetPrice", targetPrice);
		req.setAttribute("earningsDate", earningsDate);

		// Flush for next quote
		quote = null;

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/quotes.jsp");
		rd.forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
