package com.tdil.simon.test.utils;

import java.io.IOException;
import java.util.Properties;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.test.AllTest;
import com.tdil.simon.test.SimonTest;
import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.web.SystemConfig;

public class SimonTestInit {

	private static boolean initialized = false;
	
	public static void init() throws IOException {
		if (!initialized) {
			initDatabaseConnection();
			initTestProperties();
			SystemConfig.init();
			SMTPServer.getSigleIntance();
			initialized = true;
		}
	}
	
	private static void initTestProperties() throws IOException {
		Properties p = new Properties(); 
		p.load(AllTest.class.getResourceAsStream("test.properties"));
		SimonTest.SERVER_URL = p.getProperty("serverURL");
		SimonTest.ADMIN_PASS = p.getProperty("adminPassword");
		SimonTest.NAVIGATION_DELAY = p.getProperty("navigation.delay");
	}

	private static void initDatabaseConnection() throws IOException {
		Properties p = new Properties(); 
		p.load(AllTest.class.getResourceAsStream("connection.properties"));
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
	}
}
