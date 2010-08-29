package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class ParagraphsNavigationAction extends SimonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.back"))) {
			if (createDocumentForm.getParagraph() > 0) {
				createDocumentForm.setParagraph(createDocumentForm.getParagraph() - 1);
			}
			return mapping.findForward("previous");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.next"))) {
			createDocumentForm.setParagraph(createDocumentForm.getParagraph() + 1);
			return mapping.findForward("next");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.save"))) {
			createDocumentForm.save();
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
			return mapping.findForward("modifyIntroduction");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.paragraphs.preview"))) {
			return mapping.findForward("preview");
		}
		return null;
	}
}
