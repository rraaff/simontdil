package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.DocumentSubTypeABMForm;

public class GoToDocumentSubTypeABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final DocumentSubTypeABMForm anABMForm = (DocumentSubTypeABMForm) form;
		final int documentTypeId = Integer.parseInt(request.getParameter("documentTypeId"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				anABMForm.reset();
				anABMForm.setDocumentTypeId(documentTypeId);
				anABMForm.init();
			}
		});

		return mapping.findForward("continue");
	}

}