package com.tdil.simon.test;

import java.io.IOException;
import java.util.Properties;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.tdil.simon.data.ibatis.IBatisManager;

public class AllTest {
	
	public static Test suite() throws IOException{
		initDatabaseConnection();
		TestSuite suite = new TestSuite("Simon test suite");
		suite.addTestSuite(TestModeratorABM.class);
		return suite;
	}

	private static void initDatabaseConnection() throws IOException {
		Properties p = new Properties(); 
		p.load(AllTest.class.getResourceAsStream("connection.properties"));
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
	}
	
}
