package com.tdil.simon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.NotificationEmailDAO;
import com.tdil.simon.data.ibatis.ResourceBundleDAO;
import com.tdil.simon.data.ibatis.SysPropertiesDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.NotificationEmail;
import com.tdil.simon.data.model.SysProperties;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.LanguageBean;
import com.tdil.simon.utils.LoggerProvider;

public class SystemConfig {

	public static String mailServer;
	
	private static String server;
	private static String serverUrl;
	private static String tempPath;
	
	private static String externalLink1;
	
	private static String systemLanguage = "castellano"; // TODO 
	private static List<LanguageBean> allLanguage;
	
	public static List<LanguageBean> getAllLanguage() {
		return allLanguage;
	}

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
	
	public static void loadPropertiesFromDB() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					mailServer = SysPropertiesDAO.getPropertyByKey(SysProperties.MAIL_SERVER);
					server = SysPropertiesDAO.getPropertyByKey(SysProperties.SERVER_NAME);
					serverUrl = SysPropertiesDAO.getPropertyByKey(SysProperties.SERVER_URL);
					tempPath = System.getProperty("java.io.tmpdir") + "/" + SysPropertiesDAO.getPropertyByKey(SysProperties.SERVER_PATH);
					externalLink1 = SysPropertiesDAO.getPropertyByKey(SysProperties.EXTERNAL_LINK1);
					List<LanguageBean> allLanguage1 = new ArrayList<LanguageBean>();
					for (String lang : ResourceBundleDAO.selectAvailabeLanguages()) {
						allLanguage1.add(new LanguageBean(lang));
					}
					allLanguage = allLanguage1;
					Country host = CountryDAO.getCountryHost();
					systemLanguage = host.getLanguage();
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
						File logo = new File(getLogoStore());
						if (!logo.exists()) {
							FileUtils.forceMkdir(logo);
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
						ResourceBundleCache.reload();
					} catch (IOException e) {
						getLog().error(e.getMessage(), e);
					}
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
		return "1000";
	}
	
	public static String getClientParagrahRefreshTime() {
		return "1000";
	}
	
	public static String getClientSignaturesRefreshTime() {
		return "1000";
	}
	
	public static String getMailServer() {
		return mailServer;
	}
	
	public static String getServerUrl() {
		return serverUrl;
	}
		
	public static String getFlagStore() {
		return tempPath + "/flags/";
	}
	
	public static String getTempPath() {
		return tempPath;
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
	
	public static String getLogoStore() {
		return tempPath + "/logo/";
	}

	public static NotificationEmail getMailForNewPassword() {
		try {
			return NotificationEmailDAO.getEmail(NotificationEmail.newpassword);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
		
	public static NotificationEmail getMailForPasswordReset() {
		try {
			return NotificationEmailDAO.getEmail(NotificationEmail.passworreset);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static NotificationEmail getMailForNewVersion() {
		try {
			return (NotificationEmail)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(org.apache.struts.action.ActionForm form) throws SQLException ,ValidationException {
					return NotificationEmailDAO.getEmail(NotificationEmail.newversion);
					};
				}, null);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}
	
	public static NotificationEmail getMailForNewObservation() {
		try {
			return (NotificationEmail)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(org.apache.struts.action.ActionForm form) throws SQLException ,ValidationException {
					return NotificationEmailDAO.getEmail(NotificationEmail.newobservation);
					};
				}, null);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return null;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return null;
		}
	}

	public static String getXHTML2FO() {
		return tempPath + "/xhtml-to-xslfo.xsl";
	}

	public static String getServer() {
		return server;
	}

	public static String getSystemLanguage() {
		return systemLanguage;
	}

	public static String getExternalLink1() {
		return externalLink1;
	}

}
