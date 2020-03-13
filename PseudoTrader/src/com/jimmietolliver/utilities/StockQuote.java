/*
* Filename: StockQuote.java
* Author: Jimmie Tolliver
* 03/12/2020 
*/
package com.jimmietolliver.utilities;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Jimmie Tolliver
 *
 */
public class StockQuote {
	private String ticker;

	/**
	 * @param ticker
	 */
	public StockQuote(String ticker) {
		this.setTicker(ticker);
	}
	
	public double getQuote() throws UnirestException {
		//Gets API Key
		ApiKey apiKey = new ApiKey();
		String key = apiKey.getKey();
		
		HttpResponse<JsonNode> response = Unirest
				.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary?region=US&symbol="+ ticker)
				.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
				.header("x-rapidapi-key", key).asJson();
		
		return response.getBody().getObject().getJSONObject("financialData").getJSONObject("currentPrice").getDouble("raw");
	}
	
	public String getName() throws UnirestException {
		//Gets API Key
		ApiKey apiKey = new ApiKey();
		String key = apiKey.getKey();
		
		HttpResponse<JsonNode> response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-profile?symbol="+ ticker)
				.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
				.header("x-rapidapi-key", key).asJson();
		
		return response.getBody().getObject().getJSONObject("quoteType").getString("shortName");
	}
	
	public HttpResponse<JsonNode> getFinancialData() throws UnirestException{
		//Gets API Key
		ApiKey apiKey = new ApiKey();
		String key = apiKey.getKey();

		return  Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-summary?region=US&symbol="+ ticker)
				.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
				.header("x-rapidapi-key", key).asJson();
	}

	/**
	 * @return the ticker
	 */
	public String getTicker() {
		return ticker;
	}

	/**
	 * @param ticker the ticker to set
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockQuote [ticker=");
		builder.append(ticker);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ticker == null) ? 0 : ticker.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockQuote other = (StockQuote) obj;
		if (ticker == null) {
			if (other.ticker != null)
				return false;
		} else if (!ticker.equals(other.ticker))
			return false;
		return true;
	}

}