package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.GroupPermission;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;

public class GroupPermissionDAO {

	public static List<GroupPermission> selectGroupPermissionForUser(SystemUser user) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectGroupPermissionForUser", user.getId());
	}
	
	public static List selectCategoryPermission(UserGroup userGroup) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectCategoryPermission", userGroup.getId());
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
	
	public static GroupPermission selectCategoryPermission(int userGroupId, int categorId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userGroupId", userGroupId);
		params.put("categoryId", categorId);
		return (GroupPermission) IBatisManager.sqlMapper.queryForObject("selectCategoryPermissionForGroupAndCategory", params);
	}

	public static GroupPermission selectDocumentTypePermission(int userGroupId, int documentTypeId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userGroupId", userGroupId);
		params.put("documentTypeId", documentTypeId);
		return (GroupPermission) IBatisManager.sqlMapper.queryForObject("selectDocumentPermissionForGroupAndDocumentType", params);
	}


}
