package com.tdil.simon.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;

import com.tdil.simon.utils.LoggerProvider;

public class SystemConfig {

	public static final String propertyLocation = "simon.properties";
	
	public static Properties properties;
	
	public static String mailServer;
	private static String publicPath;
	private static String referencedDocPath;
	private static String privateTemporaryPath;
	
	
	public static void init() throws IOException {
		loadProperties();
		initLogger();
	}
	
	private static void initLogger() {
		String logFilePath = properties.getProperty("log4.config");
		LoggerProvider.initialize(logFilePath, LogManager.getCurrentLoggers());
	}

	private static void loadProperties() throws IOException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(System.getProperty(propertyLocation));
			properties = new Properties();
			properties.load(fileInputStream);
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}
	
	public static String getMailServer() {
		return properties.getProperty("mail.server");
	}
	
	public static String getMailFromForNewPassword() {
		return properties.getProperty("newpassword.from");
	}
	
	public static String getMailSubjectForNewPassword() {
		return properties.getProperty("newpassword.subject");
	}
	
	public static String getMailBodyForNewPassword() {
		return properties.getProperty("newpassword.body");
	}
	
	public static String getMailFromForPasswordReset() {
		return properties.getProperty("passworreset.from");
	}
	
	public static String getMailToForPasswordReset() {
		return properties.getProperty("passworreset.to");
	}
	
	public static String getMailSubjectForPasswordReset() {
		return properties.getProperty("passworreset.subject");
	}
	
	public static String getMailBodyForPasswordReset() {
		return properties.getProperty("passworreset.body");
	}
	
	public static String getFlagStore() {
		return properties.getProperty("store.flags");
	}
	
	public static String getReferenceDocumentStore() {
		return properties.getProperty("store.reference");
	}
}
