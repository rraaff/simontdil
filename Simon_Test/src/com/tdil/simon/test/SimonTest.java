package com.tdil.simon.test;

import org.watij.webspec.dsl.WebSpec;

import junit.framework.TestCase;

public class SimonTest extends TestCase {

	public static String SERVER_URL = "http://localhost:8180/Simon/";
	public static String ADMIN_PASS = "Admin";
	
	private static WebSpec spec;

	public synchronized static WebSpec getSpec() {
		if (spec == null) {
			spec = createWebSpec();
		} else {
			spec.open(SimonTest.SERVER_URL + "logout.st");
		}
		return spec;
	}

	private static WebSpec createWebSpec() {
		WebSpec.window_width = 800;
		WebSpec.window_height = 800;
		return new WebSpec().mozilla();
	}


	
}
