package com.tdil.simon.actions.impl;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.impl.admin.UserAction;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.SystemUserValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class RequestPasswordResetAction extends UserAction implements TransactionalAction {

	private static final Logger Log = LoggerProvider.getLogger(RequestPasswordResetAction.class);
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ANONYMOUS;
	}
	
	@Override
	protected boolean isAdd() {
		return false;
	}

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.username = SystemUserValidation.validateUsername(this.username, "username", validation);
		this.email = SystemUserValidation.validateEmail(this.email, "email", validation);
	}
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.username = req.getParameter("username");
		this.email = req.getParameter("email");
	}
	
	@Override
	protected ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser systemUser = SystemUserDAO.getUserForLogin(this.username);
		if (systemUser != null && systemUser.getEmail().equals(this.email)) {
			systemUser.setPasswordResetRequest(true);
			SystemUserDAO.updateUser(systemUser);
			try {
				EmailUtils.sendAdminEmailUserRequestPasswordReset(systemUser.getName(), systemUser.getPassword());
			} catch (MessagingException e) {
				Log.error(e.getMessage(), e);
			} 
		} else {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
	}

}
