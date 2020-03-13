/**
 * 
 */
package com.jimmietolliver.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.jimmietolliver.jpa.entities.Stock;
import com.jimmietolliver.jpa.services.StockServices;

/**
 * @author Jimmie Tolliver
 *
 */
public class StockTest {

	// Test stock services contains method
	@Test
	public void containsTest() {
		StockServices stockService = new StockServices();
		boolean result = stockService.contains("F");
		stockService.cleanup();
		assertEquals(true, result);
	}
	
	// Test getStockByTicker method
	@Test
	public void getStockByTickerTest() {
		StockServices stockService = new StockServices();
		Stock stock = stockService.getStockByTicker("F");
		stockService.cleanup();
		assertEquals("Ford", stock.getName());
	}
	
	// Test getNumSharesByStockId method
	@Test
	public void getNumSharesByStockIdTest() {
		StockServices stockService = new StockServices();
		Long numShares = stockService.getNumSharesByStockId(1L);
		System.out.println(numShares);
		stockService.cleanup();
		assertEquals((Long)25L, numShares);
	}
	
}
