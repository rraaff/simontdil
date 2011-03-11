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
import com.tdil.simon.actions.validations.LoginValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public class LoginAction extends AbstractAction implements TransactionalAction {

	private String username;
	private String password;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ANONYMOUS;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.username = req.getParameter("username");
		this.password = req.getParameter("password");
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
		if (exists.isTemporaryPassword()) {
			throw new ValidationException(new ValidationError(ValidationErrors.PASSWORD_EXPIRED));
		}
		if (!exists.getPassword().equals(this.password)) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
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
		LoginValidation.validate(this.username, this.password, validation);
	}

}
