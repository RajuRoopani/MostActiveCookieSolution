package com.active.cookie;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.active.cookie.ds.CookieCounter;

/**
 * 
 * @author rroopani
 *
 */
public class TestCookieCounter {
	private CookieCounter cookieCounter;
	
	@BeforeEach
	void setUp() throws Exception {
		cookieCounter = new CookieCounter();
	}

	@AfterEach
	void tearDown() throws Exception {
		cookieCounter.reset();
	}
	
	@Test
	void testEmptyCookieCounter() {
		Assert.assertTrue(cookieCounter.getMaxCookieCount() == 0);
		Assert.assertTrue(cookieCounter.getTopCookies().isEmpty());
	}
	
	@Test
	void testCookieCounterWithSingleEntry() {
		cookieCounter.addCookie("ABC");
		Assert.assertTrue(cookieCounter.getMaxCookieCount() == 1);
		Assert.assertTrue(cookieCounter.getTopCookies().size() == 1);
		Assert.assertTrue(cookieCounter.getTopCookies().contains("ABC"));
	}
	
	@Test
	void testCookieCounterWithMultipleEntriesNoRecurrence() {
		cookieCounter.addCookie("ABC");
		cookieCounter.addCookie("XYZ");
		Assert.assertTrue(cookieCounter.getMaxCookieCount() == 1);
		Assert.assertTrue(cookieCounter.getTopCookies().size() == 2);
		Assert.assertTrue(cookieCounter.getTopCookies().contains("XYZ"));
		Assert.assertTrue(cookieCounter.getTopCookies().contains("ABC"));
	}
	
	@Test
	void testCookieCounterWithMultipleEntrieRecurrence() {
		cookieCounter.addCookie("ABC");
		cookieCounter.addCookie("XYZ");
		cookieCounter.addCookie("ABC");
		Assert.assertTrue(cookieCounter.getMaxCookieCount() == 2);
		Assert.assertTrue(cookieCounter.getTopCookies().size() == 1);
		Assert.assertTrue(cookieCounter.getTopCookies().contains("ABC"));
	}
	
	@Test
	void testCookieCounterFull() {
		cookieCounter.addCookie("ABC");
		cookieCounter.addCookie("XYZ");
		cookieCounter.addCookie("ABC");
		cookieCounter.addCookie("XYZ");
		cookieCounter.addCookie("XYZ");
		cookieCounter.addCookie("XYZ");
		cookieCounter.addCookie("PQR");
		Assert.assertTrue(cookieCounter.getMaxCookieCount() == 4);
		Assert.assertTrue(cookieCounter.getTopCookies().size() == 1);
		Assert.assertTrue(cookieCounter.getTopCookies().contains("XYZ"));
	}


}
