package com.tdil.simon.test;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.test.smtp.SMTPServer;

public class AllTest {
	
	public static Test suite() throws IOException{
		initDatabaseConnection();
		initTestProperties();
		SMTPServer.getSigleIntance();
		
		TestSuite suite = new TestSuite("Simon test suite");
		suite.addTestSuite(TestSystemUserABM.class);
		suite.addTestSuite(TestDelegateABM.class);
		//suite.addTestSuite(TestCountryABM.class);
		return suite;
	}

	private static void initTestProperties() throws IOException {
		Properties p = new Properties(); 
		p.load(AllTest.class.getResourceAsStream("test.properties"));
		SimonTest.SERVER_URL = p.getProperty("serverURL");
		SimonTest.ADMIN_PASS = p.getProperty("adminPassword");
	}

	private static void initDatabaseConnection() throws IOException {
		Properties p = new Properties(); 
		p.load(AllTest.class.getResourceAsStream("connection.properties"));
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
	}
	
}
