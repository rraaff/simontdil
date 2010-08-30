package com.tdil.simon.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;

public class SimonAction extends Action {

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
		if (this.getLoggedUser(request).isDelegate()) {
			if (Site.IN_NEGOTIATION.equals(Site.getDELEGATE_SITE().getStatus())) {
				return mapping.findForward("goToDelegateNegotiation");
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
