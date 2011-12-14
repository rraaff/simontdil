package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.UserGroup;

public class UserGroupDAO {

	public static List selectAllUserGroup() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllUserGroup");
	}
	
	public static List selectAllUserGroupNotDeleted() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllUserGroupNotDeleted");
	}
	
	public static UserGroup getUserGroup(int id) throws SQLException {
		return (UserGroup) IBatisManager.sqlMapper.queryForObject(
				"selectUserGroupById", id);
	}

	public static UserGroup getUserGroup(String name) throws SQLException {
		return (UserGroup) IBatisManager.sqlMapper.queryForObject(
				"selectUserGroupByName", name);
	}
	
	public static void insertUserGroup(UserGroup userGroup) throws SQLException {
		IBatisManager.sqlMapper.insert("insertUserGroup", userGroup);
	}

	public static void updateUserGroup(UserGroup userGroup) throws SQLException {
		IBatisManager.sqlMapper.update("updateUserGroup", userGroup);
	}

	public static void logicallyDeleteUserGroup(UserGroup userGroup)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteUserGroup", userGroup);
	}


}
