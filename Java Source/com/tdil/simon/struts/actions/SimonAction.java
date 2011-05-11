package com.tdil.simon.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.ResourceBundleCache;

public abstract class SimonAction extends Action {

	public SystemUser getLoggedUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return null;
		}
		SystemUser user = (SystemUser)session.getAttribute("user");
		if (user == null) {
			return null;
		}
		return user;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SystemUser user = getLoggedUser(request);
		if (user == null) {
			return mapping.findForward("notLogged");
		}
		if (!UserTypeValidation.isValid(user, this.getPermissions())) {
			getLog().fatal("Invalid action for " + this.getClass().getName() + " user " + user.getName());
			return mapping.findForward("invalidAction");
		}
		if (DelegateSiteCache.shouldProceedToNegotiation(this.getLoggedUser(request))){
			return mapping.findForward("goToDelegateNegotiation");
		} 
		try {
			return this.basicExecute(mapping, form, request, response);
		} catch (RuntimeException e) {
			if (e instanceof UnAuthorizedAccessException) {
				UnAuthorizedAccessException ex = (UnAuthorizedAccessException)e;
				ex.setAction(this.getClass());
				getLog().error(ex.getMessage(), ex);
				return mapping.findForward("invalidAction");
			} else {
				// TODO doc_type
				return mapping.findForward("invalidAction");
			}
		}
	}
	
	protected abstract UserTypeValidation[] getPermissions();
	
	protected abstract ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception;

	protected ActionForward redirectToFailure(ValidationError error, HttpServletRequest request, ActionMapping mapping) {
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
	
	protected boolean isIndexedOperation(final HttpServletRequest request, String context, String key) {
		return isIndexedOperationByKey(request, context, key);
	}
	
	public static boolean isIndexedOperationByKey(final HttpServletRequest request, String context, String key) {
		String op = request.getParameter("indexOperation");
		return isIndexedOperationByKey(op, context, key);
	}

	public static boolean isIndexedOperationByKey(String op, String context, String key) {
		if (op == null || op.length() == 0) {
			return false;
		}
		return op.equals(context + "-" + key);
	}

	protected int getIndexClicked(final HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("indexClicked"));
	}

	protected String getParamClicked(final HttpServletRequest request) {
		return request.getParameter("indexClicked");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SimonAction.class);
	}
}
