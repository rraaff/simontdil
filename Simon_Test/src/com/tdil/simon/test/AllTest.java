package com.tdil.simon.test;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTest {
	
	public static Test suite() throws IOException{
		
		TestSuite suite = new TestSuite("Simon test suite");
		suite.addTestSuite(TestSystemUserABM.class);
		suite.addTestSuite(TestDelegateABM.class);
		suite.addTestSuite(TestCategoryABM.class);
		suite.addTestSuite(TestCreateDocument.class);
		//suite.addTestSuite(TestCountryABM.class);
		return suite;
	}
	
}
