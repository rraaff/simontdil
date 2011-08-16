package com.tdil.simon.test.factory;

import java.sql.SQLException;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.UserGroup;

public class UserGroupFactory {

	public static UserGroup getGroupByName(String name) throws SQLException {
		UserGroup result = null;
		IBatisManager.beginTransaction();
		result = UserGroupDAO.getUserGroup(name);
		IBatisManager.commitTransaction();
		return result;
	}
}
