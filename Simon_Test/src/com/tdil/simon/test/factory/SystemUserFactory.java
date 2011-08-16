package com.tdil.simon.test.factory;

import java.sql.SQLException;

import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.test.utils.RandomUtils;
import com.tdil.simon.utils.CryptoUtils;

public class SystemUserFactory {

	public static SystemUser getModeratorActive() throws SQLException {
		IBatisManager.beginTransaction();
		// Create
		Country c = CountryDAO.getCountryHost();
		SystemUser sysUser = SystemUserDAO.getUser("mod_act");
		if (sysUser == null) {
			sysUser = new SystemUser();
			sysUser.setName("mod_act");
			sysUser.setModerator(true);
			sysUser.setCountryId(c.getId());
			sysUser.setCountryDesc(c.getName());
			sysUser.setUsername("mod_act");
			sysUser.setPassword(CryptoUtils.getHashedValue("mod_act"));
			sysUser.setDeleted(false);
			SystemUserDAO.insertUser(sysUser);
		}
		IBatisManager.commitTransaction();
		return sysUser;
	}
	
	public static SystemUser getNewDelegate() throws SQLException {
		SystemUser sysUser = new SystemUser();
		String username = RandomUtils.randomString("Neg ");
		IBatisManager.beginTransaction();
		// Create
		Country c = CountryDAO.getCountryHost();
		sysUser.setName(RandomUtils.randomString("Neg "));
		sysUser.setDelegate(true);
		sysUser.setCountryId(c.getId());
		sysUser.setCountryDesc(c.getName());
		sysUser.setUsername(username);
		sysUser.setPassword(CryptoUtils.getHashedValue("negociador"));
		sysUser.setDeleted(false);
		SystemUserDAO.insertUser(sysUser);
		sysUser = SystemUserDAO.getUser(username);
		IBatisManager.commitTransaction();
		return sysUser;
	}

	public static SystemUser getSystemUserByUsername(String userName) throws SQLException {
		SystemUser result = null;
		IBatisManager.beginTransaction();
		result = SystemUserDAO.getUser(userName);
		IBatisManager.commitTransaction();
		return result;
	}
}
