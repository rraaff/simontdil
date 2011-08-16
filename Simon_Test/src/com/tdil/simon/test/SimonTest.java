package com.tdil.simon.test;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.test.utils.SimonTestInit;

import junit.framework.TestCase;

public class SimonTest extends TestCase {

	public static String NAVIGATION_DELAY = "0";
	
	public static String SERVER_URL = "http://localhost:8180/Simon/";
	public static String ADMIN_PASS = "Admin";
	
	private static WebSpec spec;

	public synchronized static WebSpec getSpec() {
		if (spec == null) {
			WebSpec.webspec_home = "/home/mgodoy/simon/webspec/web_spec/webspec_1.3.1";
			spec = createWebSpec();
		}
		return spec;
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		SimonTestInit.init();
	}

	private static WebSpec createWebSpec() {
		WebSpec.window_width = 800;
		WebSpec.window_height = 800;
		return new WebSpec().mozilla();
	}

	public static int getNavigationDelay() {
		return Integer.valueOf(NAVIGATION_DELAY);
	}

}
