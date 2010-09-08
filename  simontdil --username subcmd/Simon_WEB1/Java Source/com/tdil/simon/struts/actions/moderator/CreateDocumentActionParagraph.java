package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.NegotiationUtils;

public class CreateDocumentActionParagraph extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.back"))) {
			return mapping.findForward("back");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.addParagraphs"))) {
			ValidationError error = createDocumentForm.validateStep2(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else {
				final boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
				if (inNegotiation) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							if (!createDocumentForm.getParagraphHidden()) {
								request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
								NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
							}
						}
					});
				}
				return mapping.findForward("addParagraphs");
			}
		}
		return null;
	}
}
