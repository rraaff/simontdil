package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

public class SysPropertiesDAO {

	public static List getProperties() throws SQLException {
		return IBatisManager.sqlMapper.queryForList(
				"selectAllProperties");
	}
	
	public static String getPropertyByKey(String key) throws SQLException {
		return (String)IBatisManager.sqlMapper.queryForObject(
				"selectPropertyByKey", key);
	}

}
