package com.tdil.simon.database;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.struts.forms.TransactionalValidationForm;
import com.tdil.simon.utils.LoggerProvider;

public class TransactionProvider {

	
	public static void executeInTransaction(TransactionalAction transactionalAction) throws SQLException, ValidationException {
		boolean commited = false;
		try {
			IBatisManager.beginTransaction();
			transactionalAction.executeInTransaction();
            IBatisManager.commitTransaction();
            commited = true;
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(TransactionProvider.class);
	}
	
	public static Object executeInTransaction(TransactionalActionWithValue transactionalAction, ActionForm form) throws SQLException, ValidationException {
		Object result = null;
		try {
			IBatisManager.beginTransaction();
			result = transactionalAction.executeInTransaction(form);
            IBatisManager.commitTransaction();
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return result;
	}
	
	public static void validateInTransaction(TransactionalValidationForm form, ValidationError validationError) throws SQLException {
		boolean commited = false;
		try {
			IBatisManager.beginTransaction();
			form.validateInTransaction(validationError);
            IBatisManager.commitTransaction();
            commited = true;
		} finally {
			try {
				IBatisManager.endTransaction();
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
	}
}
