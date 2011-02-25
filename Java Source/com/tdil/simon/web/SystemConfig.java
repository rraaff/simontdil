package com.tdil.simon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SysPropertiesDAO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;

public class SystemConfig {

	public static Properties properties;
	
	public static String mailServer;
	private static String publicPath;
	private static String referencedDocPath;
	private static String privateTemporaryPath;
	
	private static String newPasswordBody;
	private static String passwordResetBody;
	
	private static String newVersionBody;
	private static String newObservationBody;
	
	private static String server;
	private static String tempPath;
	private static String simonLocation;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemConfig.class);
	}
	
	public static DateFormat getDateFormat() {
		return new SimpleDateFormat("dd MMM yyyy");
	}
	
	public static DateFormat getDateFormatWithMinutes() {
		return new SimpleDateFormat("dd MMM yyyy HH:mm");
	}
	
	public static void init() throws IOException {
		loadPropertiesFromDB();
		loadProperties();
		initLogger();
	}
	
	private static void loadPropertiesFromDB() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					server = SysPropertiesDAO.getPropertyByKey("simon.server.name");
					tempPath = System.getProperty("java.io.tmpdir") + "/" + SysPropertiesDAO.getPropertyByKey("simon.tmp.subpath");
					try {
						File file = new File(tempPath);
						if (!file.exists()) {
							FileUtils.forceMkdir(file);
						}
						File flags = new File(getFlagStore());
						if (!flags.exists()) {
							FileUtils.forceMkdir(flags);
						}
						File ref = new File(getReferenceDocumentStore());
						if (!ref.exists()) {
							FileUtils.forceMkdir(ref);
						}
						File sign = new File(getSignatureStore());
						if (!sign.exists()) {
							FileUtils.forceMkdir(sign);
						}
						File log = new File(tempPath + "/log");
						if (!log.exists()) {
							FileUtils.forceMkdir(log);
						}
						File log4j = new File(tempPath + "/log/log4j.xml");
						if (!log4j.exists()) {
							InputStream io = SystemConfig.class.getResourceAsStream("log4j.xml");
							IOUtils.copy(io, new FileOutputStream(tempPath + "/log/log4j.xml"));
						}
						File xhtml2fo = new File(tempPath + "/xhtml-to-xslfo.xsl");
						if (!xhtml2fo.exists()) {
							InputStream io = SystemConfig.class.getResourceAsStream("xhtml-to-xslfo.xsl");
							IOUtils.copy(io, new FileOutputStream(tempPath + "/xhtml-to-xslfo.xsl"));
						}
					} catch (IOException e) {
						getLog().error(e.getMessage(), e);
					}
					simonLocation = SysPropertiesDAO.getPropertyByKey("simon.properties.location");
				}
			});
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException(e);
		} 
	}

	private static void initLogger() {
		String logFilePath = tempPath + "/log/log4j.xml";
		LoggerProvider.initialize(logFilePath, LogManager.getCurrentLoggers());
	}

	private static void loadProperties() throws IOException {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(simonLocation);
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
		return tempPath + "/flags/";
	}
	
	public static String getLog4J() {
		return tempPath + "/log/log4j.xml";
	}
	
	public static String getReferenceDocumentStore() {
		return tempPath + "/ref/";
	}
	
	public static String getSignatureStore() {
		return tempPath + "/sign/";
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
		return tempPath + "/xhtml-to-xslfo.xsl";
	}

	public static String getServer() {
		return server;
	}
}
