/**
 * 
 */
package com.jimmietolliver.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jimmietolliver.jpa.entities.Account;
import com.jimmietolliver.jpa.services.AccountServices;

/**
 * @author Jimmie Tolliver
 *
 */
public class AccountTest {

	//Test get first name method
	@Test
	public void firstName() {
		AccountServices test = new AccountServices();
		String name = test.getFirstName("jtolliver");
		test.cleanup();
		assertEquals("Jimmie", name);
	}
	
	//Test get password method
	@Test
	public void password() {
		AccountServices test = new AccountServices();
		String password = test.getAccountPassword("jtolliver");
		test.cleanup();
		assertEquals("password", password);
	}

	//Test get password method
	@Test
	public void account() {
		AccountServices test = new AccountServices();
		Account accountNum = test.getAccountByNumber(1L);
		test.cleanup();
		assertEquals("Jimmie", accountNum.getFirstName());
	}
	
	//Test get account by username method
	@Test
	public void accountNumByUserName() {
		AccountServices test = new AccountServices();
		Long accountNum = test.getAccountNumberByUserName("jtolliver");
		test.cleanup();
		assertEquals((Long)1L, accountNum);
	}
	
	
}
