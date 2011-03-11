package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.forms.NotificationEmailABMForm;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.ResourceBundleCache;

public class NotificationEmailSaveAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final NotificationEmailABMForm notificationEmailABMForm = (NotificationEmailABMForm) form;

		if (notificationEmailABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificar"))) {
			return this.validateAndSave(notificationEmailABMForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "notificationEmail";
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(NotificationEmailSaveAction.class);
	}

}
