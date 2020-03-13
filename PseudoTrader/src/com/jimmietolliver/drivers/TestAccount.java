/**
 * 
 */
package com.jimmietolliver.drivers;

import java.util.List;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Cash;
import com.jimmietolliver.jpa.entities.Transaction;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.jpa.services.CashServices;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * @author Jimmie Tolliver
 *
 */
public class TestAccount {

	/**
	 * @param args
	 * @throws UnirestException
	 */
//	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws UnirestException {
		// Drives QuickTradeServlet
//		AccountServices accountNum = new AccountServices();
//		Account accNum = accountNum.getAccountByNumber(1L); // CHANGE TO ACCOUNT NUMBER
//		List<Holding> holdings = accNum.getHoldings();
//		for (Holding holding : holdings) {
//			System.out.println(holding.getStock().getTicker());
//		}

//		//GET NAME TEST
//		HttpResponse<JsonNode> response = null;
//		try {
//			response = Unirest.get("https://apidojo-yahoo-finance-v1.p.rapidapi.com/stock/v2/get-profile?symbol=AMRN")
//					.header("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
//					.header("x-rapidapi-key", "c7471e9e5bmsha35a35b8fc75f76p17e7dfjsnd47c1802be04").asJson();
//		} catch (UnirestException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////				response.getBody().getObject().getJSONObject("qouteType").getJSONObject("shortName")
//				System.out.println(response.getBody().getObject().getJSONObject("quoteType").getString("shortName"));

		// GET FINANCIAL DATA TEST
//		String ticker = "GM";
//		StockQuote financialData = new StockQuote(ticker);
//		HttpResponse<JsonNode> stockData = financialData.getFinancialData();
//		System.out.println(stockData.getBody().getObject().getJSONObject("defaultKeyStatistics").getJSONObject("52WeekChange").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketOpen").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("averageDailyVolume3Month").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketPreviousClose").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketDayHigh").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("regularMarketDayLow").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("fiftyTwoWeekHigh").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("fiftyTwoWeekLow").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("trailingPE").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("beta").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("price").getJSONObject("marketCap").getString("fmt"));
//		System.out.println(stockData.getBody().getObject().getJSONObject("summaryDetail").getJSONObject("volume").getString("fmt"));
//		System.out.println(((JSONObject) stockData.getBody().getObject().getJSONObject("earnings").getJSONObject("earningsChart").getJSONArray("earningsDate").get(0)).getString("fmt"));

//		// SELL STOCK ALGORITHM
//
//		// GET STOCK PRICE
//		Double stockPrice = 40.0;
//
//		// GET NUM SHARES FROM USER GUI INPUT
//		Long numShares = 25L;
//		String ticker = "F";
//
//		// GET ACCOUNT NUMBER
//		AccountServices accountService = new AccountServices();
//		Account account = accountService.getAccountByNumber(1L);
//
//		// GET ACCOUNT CASH
//		Long accountId = account.getId(); // Should know this via login
//		Double currCash = accountService.getAccountByNumber(accountId).getCash().getCurrCashVal();
//
//		// GET CURRENT NUMBER OF SHARES
//		List<Holding> holdings = account.getHoldings();
//		System.out.println(accountId);
//		Long holdShares = 0L;
//		Holding newHolding = null;
//		for (Holding holding : holdings) {
//			if (holding.getStock().getTicker().equals(ticker)) {
//				holdShares = holding.getNumShares();
//				// ENOUGH SHARES TO TRADE?
//				if (holdShares >= numShares) {
//					holding.setNumShares(holdShares - numShares); // SET SHARES TO NEW VALUE
//					newHolding = holding;
//					System.out.println("New num shares " + holding.getNumShares());
//
//					// TRANSACTION TABLE CODE
//					Transaction newTransaction = new Transaction();
//					newTransaction.setAccount(account);
//					newTransaction.setStock(newHolding.getStock());
//					newTransaction.setTradePrice(stockPrice);
//					newTransaction.setTradeShares(numShares);
//					newTransaction.setBuy(false);
//										account.addTransaction(newTransaction);
//					// END TRANSACTION TABLE CODE
//
//					// UPDATE CASH
//					account.getCash().setCurrCashVal(currCash + numShares * stockPrice);
//				} else {
//					System.out.println("Sorry you don't own enough shares of " + holding.getStock().getName() + "("
//							+ holding.getStock().getTicker() + ")");  //TODO SEND TO GUI
//				}
//			}
//		}
//		accountService.updateAccount(account);
//		accountService.cleanup();
		
//		String firstName = "test";
//		String lastName = "name";
//		String username = "tname";
//		String password = "pw)rd1";
//		String email ="tname@email.com";
//		
//		//TODO CHECK TO MAKE SURE USERNAME PASSWORD UNIQUE
//		
//		Account newAccount = new Account(email, firstName, lastName, password, username);		
//		AccountServices accountService = new AccountServices();
//		accountService.updateAccount(newAccount);
//		
//		// GET AUTO GENERATED ACCOUNT_ID FROM DATABASE
//		Long accountId = accountService.getAccountNumberByUserName(username);
//		newAccount = new Account(accountId);
//		Cash newCash = new Cash();
//		newCash.setCurrCashVal(10000.00);
//		newCash.setInitialCashVal(10000.00);
//		newCash.setAccount(newAccount);
//		
//		CashServices cashService = new CashServices();
//		cashService.updateCash(newCash);
//		
//		//Clean up services
//		cashService.cleanup();
//		accountService.cleanup();

//		AccountServices accountService = new AccountServices();
//		Account account = accountService.getAccountByNumber(1L);
//		List<Holding> holdings = account.getHoldings();
//		for(Holding holding:holdings) {
//			System.out.println(holding.getStock().getTicker());
//		}
		
//		AccountServices accountService = new AccountServices();
//		Account account = accountService.getAccountByNumber(1L);
//		List<Transaction> transactions = account.getTransactions();
//		for(Transaction transaction: transactions) {
//			System.out.println("Ticker: " + transaction.getStock().getTicker());
//			System.out.println("Name: " + transaction.getStock().getName());
//			System.out.println("Date: " + transaction.getDate());
//			System.out.println("Shares: " + transaction.getTradeShares());
//			System.out.println("Trade Type: " + transaction.getBuy());
//			System.out.println("Trade Price: " + transaction.getTradePrice());
//			System.out.println("Amount: " + transaction.getTradeShares() * transaction.getTradePrice());
//		}
		
//		String username = "nkahn";
//		String password = "passw0rd";
//		
//		AccountServices account = new AccountServices();
//		String firstName = null;
//		String pword = "";
//		
//		try {
//			firstName = account.getFirstName(username).toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		if(firstName == null) {
//			System.out.println("invalid username");
//		} else {
//			pword = account.getAccountPassword(username);
//			System.out.println(pword);
//		}
////		Long accountId = account.getAccountNumberByUserName(username);	
//		
//		System.out.println(firstName);
//		
//		if(!pword.equals(password) || firstName == null) {
//			System.out.println("Invalid username or password");
//		} else {
//			System.out.println("Welcome "+ firstName);
//		}
		
		
//		String password1 = "passwd";
//		String password2 = "passwd";
//		String password = null;
//		
//		AccountServices accountService = new AccountServices();
//		Account account = accountService.getAccountByNumber(3L);
//
//		if(!password1.equals(password2)) {
//			System.out.println("Passwords don't match, try again");  //TODO send to GUI
//		}
//		
//		else { 
//			password = password1;
//			account.setPassword(password);
//			accountService.updateAccount(account);
//		}
//		
//		accountService.cleanup();
		
		String newUsername = "jdoe";
		
		AccountServices accountService = new AccountServices();
		List<String> userNames = accountService.getAllUserNames();
		if(userNames.contains(newUsername)) {
			System.out.println("Sorry username already in use. Try again.");
		}
	}
}