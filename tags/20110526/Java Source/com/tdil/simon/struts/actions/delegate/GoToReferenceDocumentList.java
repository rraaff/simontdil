package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ListForm;

public class GoToReferenceDocumentList extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ListForm listForm = (ListForm) form;
		listForm.setUser(this.getLoggedUser(request));
		TransactionProvider.executeInTransaction(this, form);
		return mapping.findForward("continue");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ListForm listForm = (ListForm) form;
		if (listForm.getUser().isDelegate()) {
			listForm.setList(ReferenceDocumentDAO.selectNotDeletedReferenceDocument(listForm.getUser()));
		} else {
			listForm.setList(ReferenceDocumentDAO.selectNotDeletedReferenceDocument());
		}
		return null;
	}
	
}
