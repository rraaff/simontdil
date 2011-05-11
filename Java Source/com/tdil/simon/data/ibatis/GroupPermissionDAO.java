package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.GroupPermission;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;

public class GroupPermissionDAO {

	public static List<GroupPermission> selectGroupPermissionForUser(SystemUser user) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectGroupPermissionForUser", user.getId());
	}
	
	public static List selectSubCategoryPermission(UserGroup userGroup) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectSubCategoryPermission", userGroup.getId());
	}
	
	public static List selectDocumentTypePermission(UserGroup userGroup) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectDocumentTypePermission", userGroup.getId());
	}
	
	public static void insertGroupPermission(GroupPermission userGroup) throws SQLException {
		IBatisManager.sqlMapper.insert("insertGroupPermission", userGroup);
	}

	public static void deleteGroupPermission(GroupPermission userGroup)
			throws SQLException {
		IBatisManager.sqlMapper.update("deleteGroupPermission", userGroup);
	}


}
