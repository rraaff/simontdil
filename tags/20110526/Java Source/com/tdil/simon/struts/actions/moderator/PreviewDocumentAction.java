package com.tdil.simon.struts.actions.moderator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

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

		if (StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "editarParrafos"))) {
			request.getSession().setAttribute("paragraphNegotiated", "true");
			return mapping.findForward("editParagraphs");
		}
		if (!createDocumentForm.isDesigner() && !createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "grabarBorrador"))) {
			createDocumentForm.setVersionStatus(Version.DRAFT);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		
		if (createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "grabarBorrador"))) {
			createDocumentForm.setVersionStatus(Version.DRAFT);
			createDocumentForm.save();
			return mapping.findForward("save");
		}

		if (!createDocumentForm.isDesigner() && !createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "grabarYContinuar"))) {
			createDocumentForm.setVersionStatus(Version.IN_NEGOTIATION);
			createDocumentForm.save();
			return mapping.findForward("save");
		}
		if (createDocumentForm.isDesigner() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "grabarDisenio"))) {
			//createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			return mapping.findForward("designerHome");
		}
		if (StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "ponerEnFirma"))) {
			createDocumentForm.setVersionStatus(Version.IN_SIGN);
			createDocumentForm.save();
			DelegateSiteCache.refresh();
			return mapping.findForward("goHome");
		}
		if (!createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "finalizar"))) {
			createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			DelegateSiteCache.refresh();
			return mapping.findForward("goHome");
		}
		
		if (createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "grabarIdiomaAlternativo"))) {
			createDocumentForm.setVersionStatus(Version.FINAL);
			createDocumentForm.save();
			return mapping.findForward("designerHome");
		}
		
		if (!createDocumentForm.isPortugues() && StringUtils.equalsUnescaped(createDocumentForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "consolidar"))) {
			return mapping.findForward("consolidate");
		}
		return null;
	}

	private String getServletInfo() {
		return "previewDocument";
	}
}
