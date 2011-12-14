package com.tdil.simon.actions.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.SystemUserValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public class LoginWithTemporaryAction extends AbstractAction implements TransactionalAction {

	private String username;
	private String email;
	private String password;
	private String newPassword;
	private String retypeNewPassword;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ANONYMOUS;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.username = req.getParameter("username");
		this.email = req.getParameter("email");
		this.password = req.getParameter("password");
		this.newPassword = req.getParameter("newPassword");
		this.retypeNewPassword = req.getParameter("retypeNewPassword");
	}

	@Override
	protected ActionResponse basicExecute(HttpServletRequest req)
			throws ValidationException, SQLException {
		invalidateSessionIfExists(req);
		TransactionProvider.executeInTransaction(this);
		SystemUser user = (SystemUser)this.getResponseData();
		user.setPassword(null); // only for security
		req.getSession().setAttribute("user", user);
		return ActionResponse.newOKResponse(user);
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser exists = SystemUserDAO.getUserForLogin(this.username);
		if (exists == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (!exists.getEmail().equals(this.email)) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (!exists.getPassword().equals(this.password)) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		exists.setPassword(this.newPassword);
		exists.setTemporaryPassword(false);
		SystemUserDAO.updateUser(exists);
		this.setResponseData(exists);
		if (exists.isDelegate()) {
			DelegateAuditDAO.registerAction(exists.getId(), exists.getCountryId(), exists.getId(), DelegateAudit.LOGIN);
		}
	}


	private void invalidateSessionIfExists(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			try {
				session.invalidate();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.username = SystemUserValidation.validateUsername(this.username, "username", validation);
		this.email = SystemUserValidation.validateEmail(this.email, "email", validation);
		this.password = SystemUserValidation.validatePassword(this.password, "password", validation);
		this.newPassword = SystemUserValidation.validatePassword(this.newPassword, "newPassword", validation);
		this.retypeNewPassword = SystemUserValidation.validatePassword(this.retypeNewPassword, "retypeNewPassword", validation);
		if (!this.newPassword.equals(this.retypeNewPassword)) {
			validation.setFieldError("newPassword", ValidationErrors.PASSWORD_NOT_EQUALS);
		}
	}

}
