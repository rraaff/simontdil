package com.tdil.simon.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.struts.forms.ChangePasswordForm;

public class InitChangePasswordAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ChangePasswordForm changePassword = (ChangePasswordForm) form;
		changePassword.setUsername((String)request.getAttribute("username"));
		changePassword.setEmail((String)request.getAttribute("email"));
		changePassword.setPassword((String)request.getAttribute("password"));
		return mapping.findForward("continue");
	}
}
