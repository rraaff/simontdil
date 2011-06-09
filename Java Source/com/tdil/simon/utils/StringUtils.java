package com.tdil.simon.utils;

import org.apache.commons.lang.StringEscapeUtils;

public class StringUtils {

	public static String notNullValueOf(String st) {
		if (st == null) {
			return "";
		} else {
			return st;
		}
	}
	
	public static boolean equalsUnescaped(String first, String second) {
		if (first == null && second != null) {
			return false;
		}
		if (first != null && second == null) {
			return false;
		}
		if (first == null && second == null) {
			return true;
		}
		String firstToCompare = StringEscapeUtils.unescapeHtml(first);
		String secondToCompare = StringEscapeUtils.unescapeHtml(second);
		return firstToCompare.equals(secondToCompare);
	}
}
