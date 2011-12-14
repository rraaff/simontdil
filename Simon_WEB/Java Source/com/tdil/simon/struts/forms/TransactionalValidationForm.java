package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;

public abstract class TransactionalValidationForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8894527716493869471L;

	public final ValidationError validate() {
		ValidationError error = this.basicValidate();
		if (!error.hasError()) {
			try {
				this.executeValidationInTransaction(error);
			} catch (SQLException e) {
				getLog().error(e.getMessage(), e);
			}
		}
		return error;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(TransactionalValidationForm.class);
	}
	
	public void executeValidationInTransaction(ValidationError validationError) throws SQLException {
		TransactionProvider.validateInTransaction(this, validationError);
	}
	
	public abstract ValidationError basicValidate();
	public abstract void validateInTransaction(ValidationError validationError) throws SQLException;
}
