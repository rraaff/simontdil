package com.tdil.simon.data.ibatis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;

public class GenerateData {

	public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
		IBatisManager.init("SqlMapConfig-JDBC-MYSQL.xml");
		IBatisManager.sqlMapper.startTransaction();
		
		Country argentina = CountryDAO.getCountry("Argentina");
		int countryId = 0;
		if (argentina == null) {
			argentina = new Country();
			argentina.setName("Argentina");
			argentina.setDeleted(false);
			argentina.setHost(true);
			countryId = CountryDAO.insertCountry(argentina);
		} else {
			countryId = argentina.getId();
		}
		SystemUser admin = SystemUserDAO.getUser("Admin");
		if (admin == null) {
			admin = new SystemUser();
			admin.setName("Admin");
			admin.setUsername("Admin");
			admin.setPassword("Admin");
			admin.setAdministrator(true);
			admin.setCountryId(countryId);
			admin.setEmail("admin@tdil.com");
			admin.setJob("Administrador");
			admin.setCountryDesc("REPUBLICA ARGENTINA");
			SystemUserDAO.insertUser(admin);
		}
		
		SystemUser mod = SystemUserDAO.getUser("Mod");
		if (mod == null) {
			mod = new SystemUser();
			mod.setName("Mod");
			mod.setUsername("Mod");
			mod.setPassword("Mod");
			mod.setAdministrator(false);
			mod.setModerator(true);
			mod.setCountryId(countryId);
			mod.setEmail("mod@tdil.com");
			mod.setJob("");
			mod.setCountryDesc("");
			SystemUserDAO.insertUser(mod);
		}
		Site site = new Site();
		site.setName(Site.DELEGATE);
		site.setStatus(Site.NORMAL);
		SiteDAO.insertSite(site);
		
		Site moderator = new Site();
		moderator.setName(Site.MODERATOR);
		moderator.setStatus(Site.NORMAL);
		SiteDAO.insertSite(moderator);
		
		Site publicSite = new Site();
		publicSite.setName(Site.PUBLIC);
		publicSite.setStatus(Site.NORMAL);
		SiteDAO.insertSite(publicSite);
		IBatisManager.sqlMapper.commitTransaction();
	}
}
