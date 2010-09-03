package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.NegotiationUtils;

public class CreateDocumentActionParagraph extends SimonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.back"))) {
			return mapping.findForward("back");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.addParagraphs"))) {
			boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
			request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
				if (inNegotiation) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
						}
				});
			}
			return mapping.findForward("addParagraphs");
		}
		return null;
	}
}
