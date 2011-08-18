package com.tdil.simon.test;

import junit.framework.TestCase;

import com.tdil.simon.test.utils.SimonTestInit;

public class SimonTest extends TestCase {

	public static String NAVIGATION_DELAY = "0";
	
	public static String SERVER_URL = "http://localhost:8180/Simon/";
	public static String ADMIN_PASS = "Admin";
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SimonTestInit.init();
	}

	public static int getNavigationDelay() {
		return Integer.valueOf(NAVIGATION_DELAY);
	}

}
