package com.tdil.simon.struts.actions.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ExportResourceBundleForm;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class ExportResourceBundleAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ExportResourceBundleForm anABMForm = (ExportResourceBundleForm) form;
		if (StringUtils.equalsUnescaped(anABMForm.getOperation(),ResourceBundleCache.get("botones", "exportar"))) {
			ValidationError error = anABMForm.basicValidate();
			if (!error.hasError()) {
				request.getSession().setAttribute("exportRBConf", anABMForm);
				return new ActionForward("/downloadLanguages.do", true);
			} else {
				return redirectToFailure(error, request, mapping);
			}
		};
		if (StringUtils.equalsUnescaped(anABMForm.getOperation(),ResourceBundleCache.get("botones", "volver"))) {
			return mapping.findForward("back");
		};
		return mapping.findForward("continue");
	}

}
