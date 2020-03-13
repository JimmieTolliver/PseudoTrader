/**
 * 
 */
package com.jimmietolliver.drivers;

import com.jimmietolliver.utilities.Trade;

/**
 * @author Jimmie Tolliver
 *
 */
public class AnotherTestDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Long accountId = 1L;
		String ticker = "F";
		Long numShares = 25L;
//		AccountServices accountService = new AccountServices();
//		Account account = accountService.getAccountByNumber(1L);  //TODO don't forget to change this 
		Double stockPrice = 43.25;
		
//		Trade trade = new Trade(ticker, numShares, account, stockPrice);
		Trade trade = new Trade(accountId, ticker, numShares, stockPrice);
//		Trade trade = new Trade();
		trade.buyStock();
		System.out.println("Done");
	}

}
