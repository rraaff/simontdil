package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class ConsolidateDocumentAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;

		if (StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			return mapping.findForward("cancel");
		}

		if (StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "consolidar"))) {
			ValidationError error = createDocumentForm.validateConsolidation(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else {
				createDocumentForm.setConsolidated(true);
				createDocumentForm.save();
				
				EmailUtils.sendNewConsolidatedVersionEmail(this.getLoggedUser(request), createDocumentForm);
				return mapping.findForward("save");
			}
		}
		return null;
	}

	private String getServletInfo() {
		return "consolidateDocument";
	}
}
