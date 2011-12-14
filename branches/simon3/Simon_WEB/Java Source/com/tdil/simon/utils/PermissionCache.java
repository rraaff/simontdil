package com.tdil.simon.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserPermissionCache;
import com.tdil.simon.data.valueobjects.UserVO;
import com.tdil.simon.database.TransactionProvider;

public class PermissionCache {

	private static Map<Integer, UserPermissionCache> userToPermissions = new ConcurrentHashMap<Integer, UserPermissionCache>();

	public static synchronized void refresh() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					userToPermissions.clear();
					List<UserVO> users = SystemUserDAO.selectDelegateUsers();
					for (UserVO userVO : users) {
						userToPermissions.put(userVO.getId(), new UserPermissionCache(userVO));
					}
				}
			});
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(PermissionCache.class);
	}

	public static UserPermissionCache getUserPermissionCache(SystemUser systemUser) {
		return userToPermissions.get(systemUser.getId());
	}

}
