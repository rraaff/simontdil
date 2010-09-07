package com.tdil.simon.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;

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
			return mapping.findForward("invalidAction");
		}
		if (this.getLoggedUser(request).isDelegate()) {
			if (Site.IN_NEGOTIATION.equals(Site.getDELEGATE_SITE().getStatus())) {
				return mapping.findForward("goToDelegateNegotiation");
			} 
		} 
		return this.basicExecute(mapping, form, request, response);
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
}
