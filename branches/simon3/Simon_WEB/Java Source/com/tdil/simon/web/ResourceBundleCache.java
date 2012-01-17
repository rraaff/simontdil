package com.tdil.simon.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.data.ibatis.ResourceBundleDAO;
import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleCache {
	
	private static ThreadLocal<String> userLanguage = new ThreadLocal<String>();

	// Map<String language, Map<String contexto, Map<String vale, valor>>>
	private static Map<String, Map<String, Map<String, String>>> cache = new ConcurrentHashMap<String, Map<String, Map<String, String>>>();

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
		return cache.get(getCurrentLanguage()).get(context).get(key);
	}
	
	public static String get(String context, String key, String lang) {
		Map<String, String> map = cache.get(lang).get(context);
		if (map != null) {
			return map.get(key);
		} else {
			return "";
		}
	}
	
	public static List<String> getAllLanguages() {
		List<String> result = new ArrayList<String>();
		result.addAll(cache.keySet());
		Collections.sort(result);
		return result;
	}
	
	public static void put(String lang, String context, String key, String value) {
		put(cache, lang, context, key, value, true);
	}
	
	public static void put(Map<String, Map<String, Map<String, String>>> cache, String lang, String context, String key, String value, boolean escape) {
		String valueToCache = value;
		if (valueToCache != null && escape) {
			valueToCache = StringUtils.replace(valueToCache, "\"", "&apos;");
			valueToCache = StringUtils.replace(valueToCache, "\'", "&apos;");
			valueToCache = StringUtils.replace(valueToCache, "<", "&lt;");
			valueToCache = StringUtils.replace(valueToCache, ">", "&gt;");
		}
		Map<String, Map<String, String>> contextMap = cache.get(lang);
		if (contextMap == null) {
			contextMap = new ConcurrentHashMap<String, Map<String,String>>();
			cache.put(lang, contextMap);
		}
		Map<String, String> keyMap = contextMap.get(context);
		if (keyMap == null) {
			keyMap = new ConcurrentHashMap<String, String>();
			contextMap.put(context, keyMap);
		}
		keyMap.put(key, valueToCache);
	}
	
	public static void reload() throws SQLException {
		List<ResourceBundle> resourceBundleList = ResourceBundleDAO.selectAll();
		cache.clear();
		for (ResourceBundle resourceBundle : resourceBundleList) {
			put(cache, resourceBundle.getRbLanguage(), resourceBundle.getRbContext(), resourceBundle.getRbKey(), resourceBundle.getRbValue(), true);
		}
	}
	
	public static Map<String, List<String>> getResourceBundleForExport() throws SQLException {
		Map<String, List<String>> result = new TreeMap<String, List<String>>();
		List<ResourceBundle> resourceBundleList = ResourceBundleDAO.selectAll();
		int i = 0;
		for (ResourceBundle resourceBundle : resourceBundleList) {
			List<String> keys = result.get(resourceBundle.getRbContext());
			if (keys == null) {
				keys = new ArrayList<String>();
				result.put(resourceBundle.getRbContext(), keys);
			}
			if (!keys.contains(resourceBundle.getRbKey())) {
				keys.add(resourceBundle.getRbKey());
			}
		}
		return result;
	}
}

