package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.forms.LogoABMForm;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class LogoABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final LogoABMForm logoABMForm = (LogoABMForm) form;

		if (StringUtils.equalsUnescaped(logoABMForm.getOperation(),ResourceBundleCache.get("logoABM", "cancelar"))) {
			logoABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(logoABMForm.getOperation(),ResourceBundleCache.get("logoABM", "modificar"))) {
			return this.validateAndSave(logoABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}


}
