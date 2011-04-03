package com.tdil.simon.web;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.data.ibatis.ResourceBundleDAO;
import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleCache {
	
	private static ThreadLocal<String> userLanguage = new ThreadLocal<String>();

	private static ConcurrentMap<String, String> cache = new ConcurrentHashMap<String, String>();
	// TODO arreglar con map de map map...

	public static void setUserLanguage(String selectedLanguage) {
		userLanguage.set(selectedLanguage);
	}
	public static void clearUserLanguage() {
		userLanguage.remove();
	}
	
	private static String getCurrentLanguage() {
		String selected = userLanguage.get();
		if (selected == null) {
			selected = SystemConfig.getSystemLanguage();
		}
		return selected;
	}
	
	public static String get(String context, String key) {
		String result = cache.get(getCurrentLanguage() + "-" + context + "-" + key);
		if (result != null) {
			result = StringUtils.replace(result, "\"", "&apos;");
			result = StringUtils.replace(result, "\'", "&apos;");
			result = StringUtils.replace(result, "<", "&lt;");
			result = StringUtils.replace(result, ">", "&gt;");
		}
		return result;
	}
	
	public static void put(String context, String key, String value) {
		cache.put(context + "-" + key, value);
	}
	
	public static void reload() throws SQLException {
		List<ResourceBundle> resourceBundleList = ResourceBundleDAO.selectAll();
		for (ResourceBundle resourceBundle : resourceBundleList) {
			cache.put(resourceBundle.getRbLanguage() + "-" + resourceBundle.getRbContext() + "-" + resourceBundle.getRbKey(), resourceBundle.getRbValue());
		}
	}
}
