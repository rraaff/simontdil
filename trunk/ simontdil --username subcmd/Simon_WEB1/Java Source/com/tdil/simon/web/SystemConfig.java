package com.tdil.simon.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tdil.simon.utils.LoggerProvider;

public class SystemConfig implements ServletContextListener {

	private static final Logger Log = LoggerProvider.getLogger(SystemConfig.class);
	public static final String propertyLocation = "simon.properties";
	
	public static Properties properties;
	
	public static String mailServer;
	private static String publicPath;
	private static String referencedDocPath;
	private static String privateTemporaryPath;
	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			SystemConfig.init();
		} catch (IOException e) {
			Log.error(e.getMessage(), e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
	
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
		return "Hola {FULLNAME}, tu usuario de acceso a simon es {USERNAME} y tu clave es {PASSWORD}.";
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
