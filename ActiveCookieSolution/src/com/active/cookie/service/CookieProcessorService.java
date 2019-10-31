package com.active.cookie.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.active.cookie.ds.CookieCounter;

/**
 * 
 * @author rroopani
 *
 */
public class CookieProcessorService {
	private Map<String, CookieCounter> dateWiseCookieCounter;
	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	/**
	 * Constructor
	 */
	public CookieProcessorService() {
		this.dateWiseCookieCounter = new HashMap<>(1);
	}
	
	/**
	 * Returns the top cookies for given local date
	 * @param localDate
	 * @return
	 */
	public List<String> getTopCookies(String localDate) {
		if( dateWiseCookieCounter.containsKey(localDate) ) {
			return dateWiseCookieCounter.get(localDate).getTopCookies();
		}
		System.out.println( "There are no Cookies active on date:" + localDate );
		return Collections.emptyList();
	}
	
	/**
	 * Process each line and populate map
	 * @param line
	 */
	public void processLine(String line) {
		if( line == null || line.isEmpty() ) {
			return;
		}
		
		String[] words = line.trim().split(",");
		if(words.length != 2) {
			System.out.println("Invalid Line: " + line + " has been ignored!");
			return;
		}
		
		String cookie = words[0];
		String localDate = this.getLocalDate(words[1]);
		if( localDate != null) {
			if(dateWiseCookieCounter.containsKey(localDate)) {
				dateWiseCookieCounter.get(localDate).addCookie(cookie);
			}else {
				CookieCounter cookieCounter = new CookieCounter();
				cookieCounter.addCookie(cookie);
				dateWiseCookieCounter.put(localDate, cookieCounter);
			}
		}
	}
	
	/**
	 * Reset the date wise cookie counter
	 */
	public void reset() {
		this.dateWiseCookieCounter.clear();
	}
	
	/**
	 * Convert date Time to local date
	 * @param dateTime
	 * @return
	 */
	private String getLocalDate(String dateTime) {
		Date utcDate = null;
		try {
			utcDate = this.sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Invalid Date: " + dateTime + " has been ignored!");
			return null;
		}
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd");
		String date = dateFormatLocal.format(utcDate).toString();
		return date;
	}
}
