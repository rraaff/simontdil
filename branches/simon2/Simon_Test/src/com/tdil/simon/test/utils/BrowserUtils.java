package com.tdil.simon.test.utils;

import com.tdil.simon.test.SimonTest;

public class BrowserUtils {

	private static Browser browser = new BrowserUtilsHtmlUnit();
	
	public static void useHtmlUnit() {
		browser = new BrowserUtilsHtmlUnit();
	}
	
	public static void waitUntilPage(String string) {
		browser.waitUntilPage(string);
	}
	
	public static String getPageName() {
		return browser.getPageName();
	}
	
	public static void open(String url) throws Exception {
		browser.open(SimonTest.SERVER_URL + url);
	}
	
	public static void waitFor(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void delay() {
		if (SimonTest.getNavigationDelay() != 0) {
			try {
				Thread.sleep(SimonTest.getNavigationDelay());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void setInput(String inputName, String value) {
		browser.setInput(inputName, value);
	}
	
	public static void setTextArea(String inputName, String value) {
		browser.setTextArea(inputName, value);
	}

	public static void setSelect(String select, String value) {
		browser.setSelect(select, value);
	}

	public static void setSetChecked(String checkboxName, boolean checked) {
		browser.setSetChecked(checkboxName, checked);
	}

	public static void clickButton(String inputName, String value) {
		browser.clickButton(inputName, value);
	}
	public static void clickButtonById(String string) {
		browser.clickButtonById(string);
	}
	
	public static void setFile(String fieldName, String fileName) {
		browser.setFile(fieldName, fileName);
	}

	public static void execute(String script) {
		browser.execute(script);
	}

	public static void close() {
		browser.close();
	}

	public static void executeNoMove(String script) {
		browser.executeNoMove(script);
	}

	public static String getDivHtmlContentById(String id) {
		// TODO Auto-generated method stub
		return browser.getDivHtmlContentById(id);
	}
}
