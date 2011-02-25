package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.forms.SysPropertyABMForm;
import com.tdil.simon.utils.LoggerProvider;

public class SysPropertySaveAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final SysPropertyABMForm sysForm = (SysPropertyABMForm) form;

		if (sysForm.getOperation().equals(ApplicationResources.getMessage("sysProperty.cancel"))) {
			sysForm.reset();
		}
		if (sysForm.getOperation().equals(ApplicationResources.getMessage("sysProperty.create"))
				|| sysForm.getOperation().equals(ApplicationResources.getMessage("sysProperty.modify"))) {
			return this.validateAndSave(sysForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SysPropertySaveAction.class);
	}

}
