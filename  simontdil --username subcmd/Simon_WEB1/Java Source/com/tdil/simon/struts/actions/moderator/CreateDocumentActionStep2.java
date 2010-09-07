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
import com.tdil.simon.utils.NegotiationUtils;

public class CreateDocumentActionStep2 extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
		createDocumentForm.setStep(1);
		boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
		request.getSession().setAttribute("docNegotiated", inNegotiation ? "true" : "false");
		request.getSession().setAttribute("paragraphNegotiated", "false");
		ValidationError error = createDocumentForm.validateStep1(mapping, request);
		if(error.hasError()) {
			return redirectToFailure(error, request, mapping);
		} else {
			return mapping.findForward("continue");
		}
	}

}
