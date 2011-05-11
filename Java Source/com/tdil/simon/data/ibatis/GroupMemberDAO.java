package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.GroupMember;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;

public class GroupMemberDAO {

	public static List selectGroupMemberByGroup(UserGroup userGroup) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectGroupMemberByGroup", userGroup.getId());
	}
	
	public static List selectGroupMemberBySystemUser(SystemUser systemUser) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectGroupMemberBySystemUser", systemUser.getId());
	}
	
	public static void insertGroupMember(GroupMember groupMember) throws SQLException {
		IBatisManager.sqlMapper.insert("insertGroupMember", groupMember);
	}

	public static void deleteGroupMember(GroupMember groupMember)
			throws SQLException {
		IBatisManager.sqlMapper.update("deleteGroupMember", groupMember);
	}


}
