package com.tdil.simon.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.forms.LoginForm;
import com.tdil.simon.utils.CryptoUtils;
import com.tdil.simon.web.LogOnceListener;

public class LoginAction extends Action implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm login = (LoginForm) form;
		if (login.getOperation().equals(ApplicationResources.getMessage("login.requestPassword"))) {
			return mapping.findForward("requestPassword");
		}
		if (login.getOperation().equals(ApplicationResources.getMessage("login.enter"))) {
			try {
				SystemUser user = (SystemUser) TransactionProvider.executeInTransaction(this, form);
				user.setPassword(null);
				request.getSession().setAttribute("user", user);
				LogOnceListener.userHasLogged(user.getUsername(), user.isModerator(), request.getSession());
				if (user.isAdministrator()) {
					return mapping.findForward("admin");
				}
				if (login.isRedirectToNegotiation()) {
					return mapping.findForward("negotiation");
				}
				if (user.isModerator()) {
					return mapping.findForward("moderator");
				}
				if (user.isDelegate()) {
					return mapping.findForward("delegate");
				}
				return mapping.findForward("success");
			} catch (ValidationException e) {
				ActionErrors errors = new ActionErrors();
				errors.add(e.asMessages());
				addErrors(request, errors);
				return mapping.findForward("failure");
			}
		}
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		LoginForm loginform = (LoginForm) form;
		SystemUser exists = SystemUserDAO.getUserForLogin(loginform.getUsername());
		if (exists == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (exists.isTemporaryPassword()) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (!exists.getPassword().equals(CryptoUtils.getHashedValue(loginform.getPassword()))) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (exists.isDelegate()) {
			DelegateAuditDAO.registerAction(exists.getId(), exists.getCountryId(), exists.getId(), DelegateAudit.LOGIN);
		}
		loginform.init(exists);
		return exists;
	}
}
