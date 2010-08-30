package com.tdil.simon.data.ibatis;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.ReferenceDocument;
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
		
		SystemUser dele1 = SystemUserDAO.getUser("Dele1");
		if (dele1 == null) {
			dele1 = new SystemUser();
			dele1.setName("Dele1");
			dele1.setUsername("Dele1");
			dele1.setPassword("Dele1");
			dele1.setAdministrator(false);
			dele1.setModerator(false);
			dele1.setDelegate(true);
			dele1.setTypeOne(true);
			dele1.setTypeTwo(false);
			dele1.setCountryId(countryId);
			dele1.setEmail("Dele1@tdil.com");
			dele1.setJob("");
			dele1.setCountryDesc("");
			SystemUserDAO.insertUser(dele1);
		}
		SystemUser dele2 = SystemUserDAO.getUser("Dele2");
		if (dele2 == null) {
			dele2 = new SystemUser();
			dele2.setName("Dele2");
			dele2.setUsername("Dele2");
			dele2.setPassword("Dele2");
			dele2.setAdministrator(false);
			dele2.setModerator(false);
			dele2.setDelegate(true);
			dele2.setTypeOne(false);
			dele2.setTypeTwo(true);
			dele2.setCountryId(countryId);
			dele2.setEmail("mod@tdil.com");
			dele2.setJob("");
			dele2.setCountryDesc("");
			SystemUserDAO.insertUser(dele2);
		}
		SystemUser dele12 = SystemUserDAO.getUser("Dele12");
		if (dele12 == null) {
			dele12 = new SystemUser();
			dele12.setName("Dele12");
			dele12.setUsername("Dele12");
			dele12.setPassword("Dele12");
			dele12.setAdministrator(false);
			dele12.setModerator(false);
			dele12.setDelegate(true);
			dele12.setTypeOne(true);
			dele12.setTypeTwo(true);
			dele12.setCountryId(countryId);
			dele12.setEmail("mod@tdil.com");
			dele12.setJob("");
			dele12.setCountryDesc("");
			SystemUserDAO.insertUser(dele12);
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
		
		for (int i = 0; i < 15; i++) {
			ReferenceDocument refDoc = new ReferenceDocument();
			refDoc.setTitle("Cumbre + " + i);
			refDoc.setFileName("file");
			ReferenceDocumentDAO.insertReferenceDocument(refDoc);
		}
		IBatisManager.sqlMapper.commitTransaction();
	}
}
