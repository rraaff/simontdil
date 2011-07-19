package com.tdil.simon.test.utils;

import java.util.Calendar;

public class RandomUtils {

	private static long numerator = 0;
	
	static {
		numerator = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		cal.set(2011, 0, 1, 0, 0 ,0);
		numerator = numerator - cal.getTimeInMillis();
	}
	
	public static String randomString(String prefix) {
		return prefix + nextNumerator();
	}

	private static long nextNumerator() {
		return numerator++;
	}
	
	public static String randomEmail(String username, String domain) {
		return username + nextNumerator() + "@" + domain;
	}
}
