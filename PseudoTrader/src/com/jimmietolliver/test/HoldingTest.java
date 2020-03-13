/**
 * 
 */
package com.jimmietolliver.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.jimmietolliver.jpa.entities.Holding;
import com.jimmietolliver.jpa.services.HoldingServices;

/**
 * @author Jimmie Tolliver
 *
 */
public class HoldingTest {

	// Test getHoldingsByAccountId method
	@Test
	public void testGetHoldingsByAccountId() {
		HoldingServices holdingService = new HoldingServices();
		List<Holding> holdings = holdingService.getHoldingsByAccountId(1L);
		holdingService.cleanup();
		assertEquals("AMZN", holdings.get(0).getStock().getTicker());
	}

}
