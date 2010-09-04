package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.NegotiationUtils;

public class ParagraphsNavigationAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.back"))) {
			if (createDocumentForm.getParagraph() > 0) {
				createDocumentForm.setParagraph(createDocumentForm.getParagraph() - 1);
				boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
				request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
				if (inNegotiation) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
						}
					});
				}
			}
			return mapping.findForward("previous");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.next"))) {
			createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
			boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
			request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
			if (inNegotiation) {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
					}
				});
			}
			return mapping.findForward("next");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.save"))) {
			createDocumentForm.save();
			return mapping.findForward("stay");
		}

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.pushData"))) {
			createDocumentForm.save();
			if (NegotiationUtils.isInNegotiation(createDocumentForm)) {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
					}
				});
			}
			return mapping.findForward("stay");
		}

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.add"))) {
			createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
			return mapping.findForward("stay");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.hide"))) {
			createDocumentForm.getParagraphStatus()[createDocumentForm.getParagraph()] = true;
			return mapping.findForward("stay");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.unhide"))) {
			createDocumentForm.getParagraphStatus()[createDocumentForm.getParagraph()] = false;
			return mapping.findForward("stay");
		}

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.modifyIntroduction"))) {
			request.getSession().setAttribute("paragraphNegotiated", "false");
			return mapping.findForward("modifyIntroduction");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.preview"))) {
			return mapping.findForward("preview");
		}
		return mapping.findForward("stay");
	}
}
