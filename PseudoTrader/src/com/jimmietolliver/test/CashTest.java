/**
 * 
 */
package com.jimmietolliver.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.entities.Cash;
import com.jimmietolliver.jpa.services.AccountServices;
import com.jimmietolliver.jpa.services.CashServices;

/**
 * @author Jimmie Tolliver
 *
 */
public class CashTest {

	//Update cash test
	@Test
	public void test() {
		AccountServices accountService = new AccountServices();
		Account account = accountService.getAccountByNumber(1L);
		Cash cash = account.getCash();
		cash.setCurrCashVal(20000.00);
		
		CashServices cashService = new CashServices();
		cashService.updateCash(cash);

		assertEquals(cash, account.getCash());
		
		accountService.cleanup();
		cashService.cleanup();
	}

}
