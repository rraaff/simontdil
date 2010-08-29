package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class EditVersionAction extends SimonAction implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;
		 int versionID = Integer.parseInt(request.getParameter("id"));
		createDocumentForm.setTemporaryVersionId(versionID);
		TransactionProvider.executeInTransaction(this, form);
		 return mapping.findForward("continue");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;
		 createDocumentForm.initWith(createDocumentForm.getTemporaryVersionId());
		 return null;
	}
}
