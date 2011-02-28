package com.tdil.simon.web;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.tdil.simon.data.ibatis.ResourceBundleDAO;
import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleCache {

	private static ConcurrentMap<String, String> cache = new ConcurrentHashMap<String, String>();
	
	public static String get(String context, String key) {
		return cache.get(context + "-" + key);
	}
	
	public static void put(String context, String key, String value) {
		cache.put(context + "-" + key, value);
	}
	
	public static void reload() throws SQLException {
		List<ResourceBundle> resourceBundleList = ResourceBundleDAO.selectAll();
		for (ResourceBundle resourceBundle : resourceBundleList) {
			cache.put(resourceBundle.getRbContext() + "-" + resourceBundle.getRbKey(), resourceBundle.getRbValue());
		}
	}
}
