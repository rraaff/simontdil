package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.DelegateSiteCache;

public class PreviewDocumentAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR, UserTypeValidation.DESIGNER };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;

		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.editParagraphs"))) {
			request.getSession().setAttribute("paragraphNegotiated", "true");
			return mapping.findForward("editParagraphs");
		}
		if (!createDocumentForm.isDesigner() && !createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.save"))) {
			createDocumentForm.setVersionStatus(Version.DRAFT);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		
		if (createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.save"))) {
			createDocumentForm.setVersionStatus(Version.DRAFT);
			createDocumentForm.save();
			return mapping.findForward("save");
		}

		if (!createDocumentForm.isDesigner() && !createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.saveAndContinue"))) {
			createDocumentForm.setVersionStatus(Version.IN_NEGOTIATION);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		if (createDocumentForm.isDesigner() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.designSave"))) {
			//createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			return mapping.findForward("designerHome");
		}
		if (createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.saveAndSign"))) {
			createDocumentForm.setVersionStatus(Version.IN_SIGN);
			createDocumentForm.save();
			DelegateSiteCache.refresh();
			return mapping.findForward("goHome");
		}
		if (!createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.saveAndFinalize"))) {
			createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			DelegateSiteCache.refresh();
			return mapping.findForward("goHome");
		}
		
		if (createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.portuguesSave"))) {
			createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			return mapping.findForward("designerHome");
		}
		
		if (!createDocumentForm.isPortugues() && createDocumentForm.getOperation().equals(ApplicationResources.getMessage("createDocument.preview.consolidate"))) {
			return mapping.findForward("consolidate");
		}
		return null;
	}
}
