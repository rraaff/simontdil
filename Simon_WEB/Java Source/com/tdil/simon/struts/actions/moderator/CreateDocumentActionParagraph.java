package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class CreateDocumentActionParagraph extends SimonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.back"))) {
			return mapping.findForward("back");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.addParagraphs"))) {
			return mapping.findForward("addParagraphs");
		}
		return null;
	}
}
