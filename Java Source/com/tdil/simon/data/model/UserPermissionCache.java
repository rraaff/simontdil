package com.tdil.simon.data.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tdil.simon.data.ibatis.GroupPermissionDAO;

public class UserPermissionCache {

	private Map<String, Set<Integer>> permissions;
	
	public UserPermissionCache(SystemUser user) throws SQLException {
		super();
		this.build(user);
	}
	
	protected void build(SystemUser user) throws SQLException {
		permissions = new HashMap<String, Set<Integer>>();
		permissions.put(GroupPermission.DOCUMENT_SUB_TYPE, new HashSet<Integer>());
		permissions.put(GroupPermission.SUBCATEGORY, new HashSet<Integer>());
		List<GroupPermission> list = GroupPermissionDAO.selectGroupPermissionForUser(user);
		for (GroupPermission permission : list) {
			permissions.get(permission.getObjectType()).add(permission.getObjectId());
		}
	}
	
	public boolean canAccess(Document document) {
		return this.canAccessDocumentType(document.getDocumentSubTypeId());
	}

	public boolean canAccessDocumentType(int documentTypeId) {
		return permissions.get(GroupPermission.DOCUMENT_SUB_TYPE).contains(documentTypeId);
	}
	
	
	
}
