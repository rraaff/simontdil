package com.tdil.simon.data.ibatis;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;

import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.ResourceBundle;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;

public class GenerateCleanDatabase {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException, ParseException {
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml");
		IBatisManager.sqlMapper.startTransaction();
		// Create country default
		Country c = CountryDAO.getCountry("Argentina");
		if (c == null) {
			Country argentina = new Country();
			argentina.setName("Argentina");
			argentina.setHost(true);
			argentina.setDeleted(false);
			CountryDAO.insertCountry(argentina);
		}
		// Create admin user
		SystemUser sysUser = SystemUserDAO.getUser("Admin");
		if (sysUser == null) {
			sysUser = new SystemUser();
			sysUser.setName("Admin");
			sysUser.setAdministrator(true);
			sysUser.setUsername("Admin");
			sysUser.setPassword("Admin");
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

		InputStream rbs = GenerateCleanDatabase.class.getResourceAsStream("resourceBundle.txt");
		BufferedReader bufferedRead = new BufferedReader(new InputStreamReader(rbs));
		String line = bufferedRead.readLine();
		while (line != null) {
			String context = line.substring(0, line.indexOf('-'));
			String key = line.substring(line.indexOf('-') + 1, line.indexOf('='));
			String value = line.substring(line.indexOf('=') + 1);
			ResourceBundle rb = ResourceBundleDAO.getResourceBundle(context, key);
			if (rb == null) {
				rb = new ResourceBundle();
				rb.setRbContext(context);
				rb.setRbKey(key);
				rb.setRbValue(value);
				ResourceBundleDAO.insertResourceBundle(rb);
			} 
			line = bufferedRead.readLine();
		}
		
		IBatisManager.sqlMapper.commitTransaction();
	}
}
