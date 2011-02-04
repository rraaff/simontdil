package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.SystemUser;

public class SystemUserDAO {

	public static List selectSystemUsers() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectSystemUsers");
	}

	public static List selectDelegateUsers() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectDelegateUsers");
	}

	/* List UserVO */
	public static List selectNotDeletedUsers() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedUsers");
	}

	public static List selectNotDeletedDelegateUsersForCountry(int countryId) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedDelegateUsersForCountry", countryId);
	}

	public static Integer selectCountFor(Integer countryId) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.queryForObject("selectSystemUserCount", countryId);
	}

	public static Integer selectCountCanSignFor(Integer countryId, boolean typeOne, boolean typeTwo) throws SQLException {
		int count = 0;
		if (typeOne) {
			count = count + (Integer) IBatisManager.sqlMapper.queryForObject("selectSystemUserCanSignCountTypeOne", countryId);
		}
		if (typeTwo) {
			count = count + (Integer) IBatisManager.sqlMapper.queryForObject("selectSystemUserCanSignCountTypeTwo", countryId);
		}
		return count;
	}
	
	public static List selectDelegateUsers(boolean typeOne, boolean typeTwo) throws SQLException {
		if (typeOne) {
			return IBatisManager.sqlMapper.queryForList("selectNotDeletedDelegateUsersTypeOne");
		}
		if (typeTwo) {
			return IBatisManager.sqlMapper.queryForList("selectNotDeletedDelegateUsersTypeTwo");
		}
		return new ArrayList();
	}
	
	public static List selectModerators() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedModerators");
	}

	public static SystemUser getUser(int id) throws SQLException {
		return (SystemUser) IBatisManager.sqlMapper.queryForObject("selectUserById", id);
	}

	public static SystemUser getUser(String userName) throws SQLException {
		return (SystemUser) IBatisManager.sqlMapper.queryForObject("selectUserByUsername", userName);
	}
	
	public static SystemUser getUserByEmail(String email) throws SQLException {
		return (SystemUser) IBatisManager.sqlMapper.queryForObject("selectUserByEmail", email);
	}

	public static SystemUser getUserForLogin(String userName) throws SQLException {
		return (SystemUser) IBatisManager.sqlMapper.queryForObject("selectUserByUsernameForLogin", userName.toUpperCase());
	}

	public static void insertUser(SystemUser systemUser) throws SQLException {
		IBatisManager.sqlMapper.insert("insertUser", systemUser);
	}

	public static void updateUser(SystemUser systemUser) throws SQLException {
		IBatisManager.sqlMapper.update("updateUser", systemUser);
	}
	
	public static void updatePassword(SystemUser systemUser) throws SQLException {
		IBatisManager.sqlMapper.update("updatePassword", systemUser);
	}

	public static void reactivateUser(SystemUser systemUser) throws SQLException {
		IBatisManager.sqlMapper.update("reactivateUser", systemUser);
	}

	public static void logicallyDeleteUser(SystemUser systemUser) throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteUser", systemUser);
	}
}
