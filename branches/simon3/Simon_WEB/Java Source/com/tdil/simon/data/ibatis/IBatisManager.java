package com.tdil.simon.data.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.utils.LoggerProvider;

public class IBatisManager {
	/**
	 * SqlMapClient instances are thread safe, so you only need one. In this
	 * case, we'll use a static singleton. So sue me. ;-)
	 */
	static SqlMapClient sqlMapper;

	
	private static Logger getLog() {
		return LoggerProvider.getLogger(IBatisManager.class);
	}
	
	public synchronized static void init(String configName, Properties properties) {
		try {
			Reader reader = Resources.getResourceAsReader("com/tdil/simon/data/ibatis/" + configName);
			sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader, properties);
			reader.close();
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			throw new RuntimeException("Something bad happened while building the SqlMapClient instance." + e, e);
		}
	}
	
	public static void beginTransaction() throws SQLException {
		sqlMapper.startTransaction();
	}
	
	public static void commitTransaction() throws SQLException {
		sqlMapper.commitTransaction();
	}
	
	public static void endTransaction() throws SQLException {
		sqlMapper.endTransaction();
	}
	
	/** User management */
	
	public static List<SystemUser> selectAllUsers() throws SQLException {
		return sqlMapper.queryForList("selectAllUsers");
	}

	public static SystemUser selectUserById(int id) throws SQLException {
		return (SystemUser) sqlMapper.queryForObject("selectUserById", id);
	}

	public static void insertUser(SystemUser user) throws SQLException {
		sqlMapper.insert("insertUser", user);
	}

	public static void updateUser(SystemUser user) throws SQLException {
		sqlMapper.update("updateUser", user);
	}

	public static void deleteUser(int id) throws SQLException {
		sqlMapper.delete("deleteUser", id);
	}

}
