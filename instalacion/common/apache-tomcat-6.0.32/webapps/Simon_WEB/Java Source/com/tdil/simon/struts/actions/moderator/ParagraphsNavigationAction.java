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
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.ImageTagUtil;
import com.tdil.simon.utils.NegotiationUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class ParagraphsNavigationAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR, UserTypeValidation.DESIGNER };

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
			createDocumentForm.save();
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
		
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "anterior"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForBack(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				if (createDocumentForm.getParagraph() > 0) {
					createDocumentForm.save();
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
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "siguiente"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.save();
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
//		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.save"))) {
//			createDocumentForm.save();
//			return mapping.findForward("stay");
//		}

		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "actualizarContenido"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
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
			}
			return mapping.findForward("stay");
		}
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "agregarParrafos"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForLength(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.save();
				createDocumentForm.setParagraph(Paragraph.INTRODUCTION_LIMIT);
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
		}

		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "agregar"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
				if (NegotiationUtils.isInNegotiation(createDocumentForm)) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(0);
						}
					});
					DelegateSiteCache.refresh();
				}
				return mapping.findForward("stay");
			}
		}
		
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "agregarAntes"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.addBefore();
				if (NegotiationUtils.isInNegotiation(createDocumentForm)) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(0);
						}
					});
					DelegateSiteCache.refresh();
				}
				return mapping.findForward("stay");
			}
		}
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "borrar"))) {
			createDocumentForm.deleteCurrent();
			if (NegotiationUtils.isInNegotiation(createDocumentForm)) {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						NegotiationUtils.updateDelegateSiteParagraph(createDocumentForm.getCurrentParagraphId());
					}
				});
				DelegateSiteCache.refresh();
			}
			return mapping.findForward("stay");
		}
		
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "agregarDespues"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraph(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.addAfter();
				if (NegotiationUtils.isInNegotiation(createDocumentForm)) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							NegotiationUtils.updateDelegateSiteParagraph(0);
						}
					});
					DelegateSiteCache.refresh();
				}
				return mapping.findForward("stay");
			}
		}
		
//		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.hide"))) {
//			createDocumentForm.getParagraphStatus()[createDocumentForm.getParagraph()] = true;
//			createDocumentForm.save();
//			return mapping.findForward("stay");
//		}
//		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.unhide"))) {
//			createDocumentForm.getParagraphStatus()[createDocumentForm.getParagraph()] = false;
//			createDocumentForm.save();
//			return mapping.findForward("stay");
//		}

		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificarIntroduccion"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForBack(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.save();
				createDocumentForm.setParagraph(0);
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
		}
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificarDocumento"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForBack(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.save();
				request.getSession().setAttribute("paragraphNegotiated", "false");
				return mapping.findForward("modifyDocument");
			}
		}
		if (createDocumentForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "previsualizar"))) {
			ValidationError error = createDocumentForm.validateCurrentParagraphForLength(mapping, request);
			if(error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else  {
				createDocumentForm.save();
				request.getSession().setAttribute("paragraphNegotiated", "false");
				return mapping.findForward("preview");
			}
		}
		return mapping.findForward("stay");
	}

	private String getServletInfo() {
		return "createDocumentStepParagraph";
	}
}
