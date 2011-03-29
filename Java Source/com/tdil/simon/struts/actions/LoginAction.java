package com.tdil.simon.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.LoginForm;
import com.tdil.simon.utils.CryptoUtils;
import com.tdil.simon.web.LogOnceListener;
import com.tdil.simon.web.ResourceBundleCache;

public class LoginAction extends Action implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm login = (LoginForm) form;
		if (login.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "pedirNuevaContraseña"))) {
			return mapping.findForward("requestPassword");
		}
		if (login.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "ingresar")) || 
				login.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "desloguearEIngresar"))) {
			try {
				boolean logout = login.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "desloguearEIngresar"));
				SystemUser user = (SystemUser) TransactionProvider.executeInTransaction(this, form);
				if (login.isRedirectToChangePassword()) {
					request.setAttribute("username", user.getUsername());
					request.setAttribute("email", user.getEmail());
					request.setAttribute("password", login.getPassword());
					login.setPassword("");
					return mapping.findForward("initChangePassword");
				}
				user.setPassword(null);
				LogOnceListener.userHasLogged(login, user.getUsername(), user.getCountryId(), user.isModerator(), request.getSession(), logout);
				request.getSession().setAttribute("user", user);
				if (user.isAdministrator()) {
					return mapping.findForward("admin");
				}
				if (user.isDesigner()) {
					return mapping.findForward("designer");
				}
				if (user.isAssistant()) {
					return mapping.findForward("assistant");
				}
				if (user.isTranslator()) {
					return mapping.findForward("translator");
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
				ValidationError error = e.getError();
				ActionMessages msg = error.asMessages();
				if (msg != null) {
					request.setAttribute("hasError", "true");
					addMessages(request, msg);
				}
				ActionMessages errors = error.asActionsErrors();
				if (errors != null) {
					addErrors(request, errors);	
				}
				return mapping.findForward("failure");
			}
		}
		return null;
	}

	private String getServletInfo() {
		return "login";
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		LoginForm loginform = (LoginForm) form;
		SystemUser exists = SystemUserDAO.getUserForLogin(loginform.getUsername());
		if (exists == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		// TODO Cambio para que tome mismo login que password
//		if (!loginform.getUsername().toUpperCase().equals(loginform.getPassword().toUpperCase())) {
//			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
//		}
		if (exists.isTemporaryPassword()) {
			if (exists.getPassword().equals(loginform.getPassword())) {
				loginform.setRedirectToChangePassword(true);
				return exists;
			} else {
				throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
			}
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
