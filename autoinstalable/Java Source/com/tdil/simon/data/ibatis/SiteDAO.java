package com.tdil.simon.data.ibatis;

import java.sql.SQLException;

import com.tdil.simon.data.model.Site;

public class SiteDAO {

	public static Site getSite(String name) throws SQLException {
		return (Site) IBatisManager.sqlMapper.queryForObject(
				"selectSiteByName", name);
	}
	
	public static void insertSite(Site site) throws SQLException {
		IBatisManager.sqlMapper.insert("insertSite", site);
	}

	public static void updateSite(Site site) throws SQLException {
		Site.flushCache(); // no esta bien...pero
		IBatisManager.sqlMapper.update("updateSite", site);
	}

}
