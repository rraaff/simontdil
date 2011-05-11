package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.forms.ABMForm;
import com.tdil.simon.struts.forms.SysPropertyABMForm;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;
import com.tdil.simon.web.SystemConfig;

public class SysPropertySaveAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final SysPropertyABMForm sysForm = (SysPropertyABMForm) form;

		if (StringUtils.equalsUnescaped(sysForm.getOperation(),ResourceBundleCache.get("sysProperty", "modificar"))) {
			return this.validateAndSave(sysForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}
	
	@Override
	public ActionForward validateAndSave(ABMForm form, HttpServletRequest request, ActionMapping mapping) {
		try {
			return super.validateAndSave(form, request, mapping);
		} finally {
			SystemConfig.loadPropertiesFromDB();
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(SysPropertySaveAction.class);
	}

}
