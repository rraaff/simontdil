package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class PreviewDocumentAction extends SimonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.editParagraphs"))) {
			return mapping.findForward("editParagraphs");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.save"))) {
			createDocumentForm.setVersionStatus(Version.DRAFT);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.saveAndContinue"))) {
			createDocumentForm.setVersionStatus(Version.IN_NEGOTIATION);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.saveAndSign"))) {
			createDocumentForm.setVersionStatus(Version.IN_SIGN);
			createDocumentForm.save();
			return mapping.findForward("goHome");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.consolidate"))) {
			return mapping.findForward("consolidate");
		}
		return null;
	}
}
