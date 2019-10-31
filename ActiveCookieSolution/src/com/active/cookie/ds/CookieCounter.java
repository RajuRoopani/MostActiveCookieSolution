package com.active.cookie.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author rroopani
 *
 */
public class CookieCounter {

	private int maxCookieCount;
	private List<String> topCookies = new ArrayList<>();
	private Map<String, Integer> cookieMap;

	/**
	 * 
	 */
	public CookieCounter() {
		this.maxCookieCount = 0;
		cookieMap = new HashMap<>(1);
	}

	/**
	 * 
	 * @param cookie
	 */
	public void addCookie(String cookie) {
		int count = 1;
		if (cookieMap.containsKey(cookie)) {
			count = cookieMap.get(cookie) + 1;
			cookieMap.put(cookie, count);
		} else {
			cookieMap.put(cookie, count);
		}
		updateTopCookies(cookie, count);
	}

	/**
	 * Returns the top cookies
	 * @return
	 */
	public List<String> getTopCookies() {
		return topCookies;
	}
	
	/**
	 * 
	 * @return maximum cookies count
	 */
	public int getMaxCookieCount() {
		return this.maxCookieCount;
	}
	
	public void reset() {
		this.maxCookieCount = 0;
		this.topCookies.clear();
		this.cookieMap.clear();
	}

	/**
	 *  Update Cookies
	 * @param cookie
	 * @param count
	 */
	private void updateTopCookies(String cookie, int count) {
		if (count == maxCookieCount) {
			topCookies.add(cookie);
		} else if (count > maxCookieCount) {
			maxCookieCount = count;
			//reset top cookies
			topCookies.clear();
			topCookies.add(cookie);
		}
	}

}
