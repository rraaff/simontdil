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
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.ImageTagUtil;
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

		String image = ImageTagUtil.getName(request);
		if (image != null && "jumpTo".equals(image)) {
			createDocumentForm.setParagraph(Integer.valueOf(createDocumentForm.getGoToParagraph()) - 1);
			boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
			if (!createDocumentForm.getParagraphHidden()) {
				request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
				if (inNegotiation) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
						}
					});
					DelegateSiteCache.refresh();
				}
			}
			return mapping.findForward("next");
		}
		
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.back"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForBack(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				if (createDocumentForm.getParagraph() > 0) {
					createDocumentForm.setParagraph(createDocumentForm.getParagraph() - 1);
					boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
					if (!createDocumentForm.getParagraphHidden()) {
						request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
						if (inNegotiation) {
							TransactionProvider.executeInTransaction(new TransactionalAction() {
								public void executeInTransaction() throws SQLException, ValidationException {
									NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
								}
							});
							DelegateSiteCache.refresh();
						}
					}
				}
			}
			return mapping.findForward("previous");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.next"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
				boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
				if (!createDocumentForm.getParagraphHidden()) {
					request.getSession().setAttribute("paragraphNegotiated", inNegotiation ? "true" : "false");
					if (inNegotiation) {
						TransactionProvider.executeInTransaction(new TransactionalAction() {
							public void executeInTransaction() throws SQLException, ValidationException {
								NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
							}
						});
						DelegateSiteCache.refresh();
					}
				}
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
				if (!createDocumentForm.getParagraphHidden()) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
						}
					});
					DelegateSiteCache.refresh();
				}
			}
			return mapping.findForward("stay");
		}

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.add"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
				return mapping.findForward("stay");
			}
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
			ValidationError error = createDocumentForm.validateCurrentParagraphForBack(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				request.getSession().setAttribute("paragraphNegotiated", "false");
				return mapping.findForward("modifyIntroduction");
			}
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.preview"))) {
			return mapping.findForward("preview");
		}
		return mapping.findForward("stay");
	}
}
