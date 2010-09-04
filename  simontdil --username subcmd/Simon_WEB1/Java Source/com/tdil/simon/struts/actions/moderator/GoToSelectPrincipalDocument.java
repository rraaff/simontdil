package com.tdil.simon.struts.actions.moderator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.SelectPrincipalDocumentForm;

/**
 * @deprecated
 * @author mgodoy
 * 
 */
public class GoToSelectPrincipalDocument extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SelectPrincipalDocumentForm listForm = (SelectPrincipalDocumentForm) form;
		List<Document> result = DocumentDAO.selectNotDeletedDocumentWithConsolidatedVersions();
		// Document oldPrincipal = DocumentDAO.selectPrincipalDocument();
		// if (oldPrincipal != null) {
		// listForm.setSelectedDocument(oldPrincipal.getId());
		// }
		listForm.setOptions(result);

		return mapping.findForward("continue");
	}
}
