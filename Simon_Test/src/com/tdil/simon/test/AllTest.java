package com.tdil.simon.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTest {
	
	public static Test suite() {
		
		TestSuite suite = new TestSuite("Simon test suite");
		suite.addTestSuite(TestSystemUserABM.class);
		suite.addTestSuite(TestDelegateABM.class);
		suite.addTestSuite(TestCategoryABM.class);
		suite.addTestSuite(TestCreateDocument.class);
		suite.addTestSuite(TestReferenceDocumentABM.class);
		suite.addTestSuite(TestNegotiationDelegate.class);
		//suite.addTestSuite(TestCountryABM.class);
		return suite;
	}
	
}
