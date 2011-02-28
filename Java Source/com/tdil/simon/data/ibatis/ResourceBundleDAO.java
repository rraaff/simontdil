package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleDAO {

	public static List<String> selectAvailabeContexts() throws SQLException {
		return (List<String>)IBatisManager.sqlMapper.queryForList("selectAvailableContext");
	}
	
	public static void updateResourceBundle(ResourceBundle resourceBundle) throws SQLException {
		IBatisManager.sqlMapper.update("updateResourceBundle", resourceBundle);
	}
	
	public static void insertResourceBundle(ResourceBundle resourceBundle) throws SQLException {
		IBatisManager.sqlMapper.insert("insertResourceBundle", resourceBundle);
	}
	
	public static ResourceBundle getResourceBundle(String context, String key) throws SQLException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("rbContext", context);
		params.put("rbKey", key);
		return (ResourceBundle) IBatisManager.sqlMapper.queryForObject("selectResourceBundleByContextAndKey", params);
	}
	
	public static List searchResourceBundle(String context, String value) throws SQLException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("rbContext", context);
		params.put("rbValue", value != null ? "%" + value.toUpperCase() + "%" : value);
		return IBatisManager.sqlMapper.queryForList("searchResourceBundleByContextAndValue", params);
	}

	public static List<ResourceBundle> selectAll() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllResourceBundle");
	}
}
