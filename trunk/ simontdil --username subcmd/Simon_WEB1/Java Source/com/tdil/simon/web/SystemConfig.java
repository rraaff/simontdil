package com.tdil.simon.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tdil.simon.utils.LoggerProvider;

public class SystemConfig implements ServletContextListener {

	public static final String propertyLocation = "simon.properties";
	
	public static Properties properties;
	
	public static String mailServer;
	private static String publicPath;
	private static String referencedDocPath;
	private static String privateTemporaryPath;
	
	private static String newPasswordBody;
	private static String passwordResetBody;
	
	private static String newVersionBody;
	private static String newObservationBody;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemConfig.class);
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		try {
			SystemConfig.init();
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		LoggerProvider.destroy();
	}
	
	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("dd MMM yyyy");
	}
	
	public static DateFormat getDateFormatWithMinutes() {
		return new SimpleDateFormat("dd MMM yyyy HH:mm");
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
			newPasswordBody = loadEmailBody(properties, "newpassword.body");
			passwordResetBody = loadEmailBody(properties, "passworreset.body");
			newVersionBody = loadEmailBody(properties, "newversion.body");
			newObservationBody = loadEmailBody(properties, "newobservation.body");
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
		}
	}
	
	private static String loadEmailBody(Properties properties2, String key) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(properties2.getProperty(key));
			return IOUtils.toString(fileInputStream);
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return "";
	}

	public static String getClientStatusRefreshTime() {
		return properties.getProperty("client.refreshTime.status");
	}
	
	public static String getClientParagrahRefreshTime() {
		return properties.getProperty("client.refreshTime.paragrah");
	}
	
	public static String getClientSignaturesRefreshTime() {
		return properties.getProperty("client.refreshTime.signatures");
	}
	
	public static String getMailServer() {
		return properties.getProperty("mail.server");
	}
	
	public static String getServerUrl() {
		return properties.getProperty("server.url");
	}
	
	public static String getMailFromForNewPassword() {
		return properties.getProperty("newpassword.from");
	}
	
	public static String getMailSubjectForNewPassword() {
		return properties.getProperty("newpassword.subject");
	}
	
	public static String getMailBodyForNewPassword() {
		return newPasswordBody;
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
		return passwordResetBody;
	}
	
	public static String getFlagStore() {
		return properties.getProperty("store.flags");
	}
	
	public static String getReferenceDocumentStore() {
		return properties.getProperty("store.reference");
	}
	
	public static String getSignatureStore() {
		return properties.getProperty("store.signatures");
	}
	
	public static String getMailFromForNewVersion() {
		return properties.getProperty("newversion.from");
	}
	
	public static String getMailSubjectForNewVersion() {
		return properties.getProperty("newversion.subject");
	}
	
	public static String getMailBodyForNewVersion() {
		return newVersionBody;
	}
	
	public static String getMailFromForNewObservation() {
		return properties.getProperty("newobservation.from");
	}
	
	public static String getMailSubjectForNewObservation() {
		return properties.getProperty("newobservation.subject");
	}
	
	public static String getMailBodyForNewObservation() {
		return newObservationBody;
	}

	public static String getXHTML2FO() {
		return properties.getProperty("xthml2fo");
	}
}
