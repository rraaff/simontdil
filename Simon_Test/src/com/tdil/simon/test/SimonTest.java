package com.tdil.simon.test;

import org.watij.webspec.dsl.WebSpec;

import junit.framework.TestCase;

public class SimonTest extends TestCase {

	private static WebSpec spec;

	public synchronized static WebSpec getSpec() {
		if (spec == null) {
			spec = createWebSpec();
		} else {
			spec.open("http://localhost:8180/Simon/logout.st");
		}
		return spec;
	}

	private static WebSpec createWebSpec() {
		WebSpec.window_width = 800;
		WebSpec.window_height = 800;
		return new WebSpec().mozilla();
	}

	
}
