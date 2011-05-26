package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.SelectPrincipalDocumentForm;

/**
 * @deprecated
 * @author mgodoy
 * 
 */
public class SelectPrincipalDocumentAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TransactionProvider.executeInTransaction(this, form);
		return mapping.findForward("home");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		SelectPrincipalDocumentForm selectPrincipalDocumentForm = (SelectPrincipalDocumentForm) form;
		Document newPrincipal = DocumentDAO.getDocument(selectPrincipalDocumentForm.getSelectedDocument());
		if (newPrincipal != null) {
			// TODO ver como queda esto con pablo
			DocumentDAO.removeAllPrincipal();
			newPrincipal.setPrincipal(true);
			DocumentDAO.updateDocument(newPrincipal);
		}
		return true;
	}
}
