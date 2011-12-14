package com.tdil.simon.data.ibatis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.Logo;
import com.tdil.simon.data.model.NotificationEmail;
import com.tdil.simon.data.model.ResourceBundle;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SysProperties;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.utils.CryptoUtils;

public class GenerateCleanDatabase {
	
	private static String emailskeys[] = {"newpassword", "passworreset", "newversion", "newobservation"};
	private static String emailsFrom[] = {"sejec@cancilleria.gob.ar", "sejec@cancilleria.gob.ar", "sejec@cancilleria.gob.ar" ,"sejec@cancilleria.gob.ar"};
	private static String emailsTo[] = {"to", "sejec@cancilleria.gob.ar", "sejec@cancilleria.gob.ar", "sejec@cancilleria.gob.ar"};
	private static String emailsSubject[] = {"Nueva clave de acceso", "Usuario solicito blanqueo de clave", "Nueva version consolidada", "Nueva observacion"};
	
	private static String logos[] = { "mails.footer", "footer.png", "mails.header", "header.png",
			"header.logo", "logo.png", "footer.logoCumbres",
			"logoCumbres.png", 
			/*"header.logoCumbresPDFsRTFs", "logoCumbresPDFsRTFs.png", */
			"header.logoHeaderPDFsRTFs", "logoHeaderPDFsRTFs.png",
			/*"header.logoSegibPDFsRTFs", "logoSegibPDFsRTFs.png",*/
			"header.logoSegundoPDFsRTFs", "logoSegundoPDFsRTFs.png", 
			"others.splashSegib", "splashSegib.png" };

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
		createDataInDatabase("jdbc:mysql://localhost:3306/SIMON", "SIMON_USER", "SIMON_USER", "Argentina", "Admin", "simon");
	}
	
	public static void createDataInDatabase(String connectionURL, String dbUser, String dbPassword, String countryHost, String adminPassword, 
			String subpath) throws SQLException, IOException {
		Properties p = new Properties(); 
		p.setProperty("connectionURL", connectionURL);
		p.setProperty("username", dbUser);  
		p.setProperty("password", dbPassword);
			  
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml", p);
		IBatisManager.sqlMapper.startTransaction();
		// Create country default
		Country c = CountryDAO.getCountryHost();
		int countryId;
		String countryDesc;
		if (c == null) {
			Country argentina = new Country();
			argentina.setName(countryHost);
			argentina.setHost(true);
			argentina.setDeleted(false);
			argentina.setLanguage("Español");
			countryId = CountryDAO.insertCountry(argentina);
			countryDesc = countryHost;
		} else {
			countryId = c.getId();
			countryDesc = c.getName();
		}
		// Create admin user
		SystemUser sysUser = SystemUserDAO.getUser("Admin");
		if (sysUser == null) {
			sysUser = new SystemUser();
			sysUser.setName("Admin");
			sysUser.setAdministrator(true);
			sysUser.setCountryId(countryId);
			sysUser.setCountryDesc(countryDesc);
			sysUser.setUsername("Admin");
			sysUser.setPassword(CryptoUtils.getHashedValue(adminPassword));
			sysUser.setDeleted(false);
			SystemUserDAO.insertUser(sysUser);
		}
		// Create sites
		Site site = SiteDAO.getSite(Site.DELEGATE);
		if (site == null) {
			site = new Site();
			site.setName(Site.DELEGATE);
			site.setStatus(Site.NORMAL);
			site.setDeleted(false);
			SiteDAO.insertSite(site);
		}
		site = SiteDAO.getSite(Site.MODERATOR);
		if (site == null) {
			site = new Site();
			site.setName(Site.MODERATOR);
			site.setStatus(Site.NORMAL);
			site.setDeleted(false);
			SiteDAO.insertSite(site);
		}
		site = SiteDAO.getSite(Site.PUBLIC);
		if (site == null) {
			site = new Site();
			site.setName(Site.PUBLIC);
			site.setStatus(Site.NORMAL);
			site.setDeleted(false);
			SiteDAO.insertSite(site);
		}
		String propvalue = SysPropertiesDAO.getPropertyByKey("simon.mailserver");
		if (propvalue == null) {
			SysProperties sysProperties = new SysProperties();
			sysProperties.setPropKey("simon.mailserver");
			sysProperties.setPropValue("localhost");
			sysProperties.setDeleted(false);
			SysPropertiesDAO.insertProperty(sysProperties);
		}
		propvalue = SysPropertiesDAO.getPropertyByKey("simon.server.name");
		if (propvalue == null) {
			SysProperties sysProperties = new SysProperties();
			sysProperties.setPropKey("simon.server.name");
			sysProperties.setPropValue("localhost");
			sysProperties.setDeleted(false);
			SysPropertiesDAO.insertProperty(sysProperties);
		}
		propvalue = SysPropertiesDAO.getPropertyByKey("simon.tmp.subpath");
		if (propvalue == null) {
			SysProperties sysProperties = new SysProperties();
			sysProperties.setPropKey("simon.tmp.subpath");
			sysProperties.setPropValue(subpath);
			sysProperties.setDeleted(false);
			SysPropertiesDAO.insertProperty(sysProperties);
		}
		propvalue = SysPropertiesDAO.getPropertyByKey("simon.server.url");
		if (propvalue == null) {
			SysProperties sysProperties = new SysProperties();
			sysProperties.setPropKey("simon.server.url");
			sysProperties.setPropValue("http://localhost:8180/Simon"); // TODO PARAMETRO COMO EL RESTO
			sysProperties.setDeleted(false);
			SysPropertiesDAO.insertProperty(sysProperties);
		}
		
		propvalue = SysPropertiesDAO.getPropertyByKey(SysProperties.EXTERNAL_LINK1);
		if (propvalue == null) {
			SysProperties sysProperties = new SysProperties();
			sysProperties.setPropKey(SysProperties.EXTERNAL_LINK1);
			sysProperties.setPropValue("<a href=\"http://desa.focalae-acreditacion.mrec.ar/index.php?ac=f\" target=\"_blank\">");
			sysProperties.setDeleted(false);
			SysPropertiesDAO.insertProperty(sysProperties);
		}
		
		for (int i = 0; i < 4; i++) {
			NotificationEmail email = NotificationEmailDAO.getEmail(emailskeys[i]);
			if (email == null) {
				email = new NotificationEmail();
				email.setEmailKey(emailskeys[i]);
				email.setEmailFrom(emailsFrom[i]);
				email.setEmailSubject(emailsSubject[i]);
				email.setEmailText(IOUtils.toString(GenerateCleanDatabase.class.getResourceAsStream(emailskeys[i] + ".txt")));
				email.setEmailTo(emailsTo[i]);
				email.setDeleted(false);
				NotificationEmailDAO.insertEmail(email);
			}
		}
		
		for (int i = 0; i < logos.length; i+=2) {
			Logo logo = LogoDAO.getLogo(logos[i]);
			if (logo == null) {
				InputStream logoInput = GenerateCleanDatabase.class.getResourceAsStream(logos[i + 1]);
				try {
					byte data[] = IOUtils.toByteArray(logoInput);
					logo = new Logo();
					logo.setDeleted(false);
					logo.setLogoKey(logos[i]);
					logo.setLogoData(data);
					LogoDAO.insertLogo(logo);
				} finally {
					logoInput.close();
				}
			}
		}

		InputStream rbs = GenerateCleanDatabase.class.getResourceAsStream("resourceBundle.txt");
		BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(rbs));
		String line = bufferedRead.readLine();
		while (line != null) {
			int first = line.indexOf('-');
			String lang = line.substring(0, first);
			int second = line.indexOf('-', first + 1);
			String context = line.substring(first + 1, second);
			String key = line.substring(second + 1, line.indexOf('='));
			String value = line.substring(line.indexOf('=') + 1);
			ResourceBundle rb = ResourceBundleDAO.getResourceBundle(lang, context, key);
			if (rb == null) {
				rb = new ResourceBundle();
				rb.setRbLanguage(lang);
				rb.setRbContext(context);
				rb.setRbKey(key);
				rb.setRbValue(value);
				ResourceBundleDAO.insertResourceBundle(rb);
			}
			line = bufferedRead.readLine();
		}

		IBatisManager.sqlMapper.commitTransaction();
		try {
			bufferedRead.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
