package com.tdil.simon.struts.actions.moderator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.struts.forms.SelectPrincipalDocumentForm;

public class GoToSelectPrincipalDocument extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SelectPrincipalDocumentForm listForm = (SelectPrincipalDocumentForm)form;
		List<Document> result = DocumentDAO.selectNotDeletedDocumentWithConsolidatedVersions();
//		Document oldPrincipal = DocumentDAO.selectPrincipalDocument();
//		if (oldPrincipal != null) {
//			listForm.setSelectedDocument(oldPrincipal.getId());
//		}
		listForm.setOptions(result);
		
		return mapping.findForward("continue");
	}
}
