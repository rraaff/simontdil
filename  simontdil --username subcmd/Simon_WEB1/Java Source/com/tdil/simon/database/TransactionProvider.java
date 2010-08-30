package com.tdil.simon.database;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.utils.LoggerProvider;

public class TransactionProvider {

	private static final Logger Log = LoggerProvider.getLogger(TransactionProvider.class);
	
	public static void executeInTransaction(TransactionalAction transactionalAction) throws SQLException, ValidationException {
		boolean commited = false;
		try {
			IBatisManager.beginTransaction();
			transactionalAction.executeInTransaction();
            IBatisManager.commitTransaction();
            commited = true;
		} finally {
			if (!commited) {
				try {
					IBatisManager.rollbackTransaction();
				} catch (SQLException e) {
					Log.error(e.getMessage(), e);
				}
			}
		}
	}
	
	public static Object executeInTransaction(TransactionalActionWithValue transactionalAction, ActionForm form) throws SQLException, ValidationException {
		boolean commited = false;
		Object result = null;
		try {
			IBatisManager.beginTransaction();
			result = transactionalAction.executeInTransaction(form);
            IBatisManager.commitTransaction();
            commited = true;
		} finally {
			if (!commited) {
				try {
					IBatisManager.rollbackTransaction();
				} catch (SQLException e) {
					Log.error(e.getMessage(), e);
				}
			}
		}
		return result;
	}
}
