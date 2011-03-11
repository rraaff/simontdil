package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.SysProperties;

public class SysPropertiesDAO {

	public static List getProperties() throws SQLException {
		return IBatisManager.sqlMapper.queryForList(
				"selectAllProperties");
	}
	
	public static String getPropertyByKey(String key) throws SQLException {
		return (String)IBatisManager.sqlMapper.queryForObject(
				"selectPropertyByKey", key);
	}
	
	public static void insertProperty(SysProperties sysProperties) throws SQLException {
		IBatisManager.sqlMapper.insert("insertProperty", sysProperties);
	}
	
	public static void updateProperty(SysProperties sysProperties) throws SQLException {
		IBatisManager.sqlMapper.update("updateProperty", sysProperties);
	}

}
