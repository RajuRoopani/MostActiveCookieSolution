package com.active.cookie;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.active.cookie.service.CookieProcessorService;

class TestCookieProcessorService {
	private CookieProcessorService cookieProcessorService;
	@BeforeEach
	void setUp() throws Exception {
		cookieProcessorService = new CookieProcessorService();
	}

	@AfterEach
	void tearDown() throws Exception {
		cookieProcessorService.reset();
	}

	@Test
	void testEmpty() {
		assertTrue(cookieProcessorService.getTopCookies("").isEmpty());
		assertTrue(cookieProcessorService.getTopCookies("2018-12-09").isEmpty());
	}
	
	@Test
	void testProcessInvalidDate() {
		cookieProcessorService.processLine("ABC,2018-12T14:19:00+00:00");
		assertTrue(cookieProcessorService.getTopCookies("").isEmpty());
		assertTrue(cookieProcessorService.getTopCookies("2018-12-09").isEmpty());
	}
	
	@Test
	void testProcessValidDate() {
		cookieProcessorService.processLine("ABC,2018-12-09T14:19:00+00:00");
		assertTrue(cookieProcessorService.getTopCookies("").isEmpty());
		assertTrue(!cookieProcessorService.getTopCookies("2018-12-09").isEmpty());
		assertTrue(cookieProcessorService.getTopCookies("2018-12-09").contains("ABC"));

	}
	
	@Test
	void testProcessValidDateMoreLines() {
		cookieProcessorService.processLine("ABC,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("XYZ,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("XYZ,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("ABC,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("XYZ,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("PQR,2018-12-09T14:19:00+00:00");
		cookieProcessorService.processLine("ABC,2018-12-08T14:19:00+00:00");
		cookieProcessorService.processLine("MNO,2018-12-08T14:19:00+00:00");
		cookieProcessorService.processLine("RST,2018-12-07T14:19:00+00:00");
		assertTrue(cookieProcessorService.getTopCookies("").isEmpty());
		assertTrue(!cookieProcessorService.getTopCookies("2018-12-09").isEmpty());
		assertTrue(cookieProcessorService.getTopCookies("2018-12-09").contains("XYZ"));
	}

}
