package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;

public class AdvanceNegotiationParagraph extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			TransactionProvider.executeInTransaction(this, form);
			return mapping.findForward("success");
		} catch (ValidationException e) {
			ActionErrors errors = new ActionErrors();
			errors.add(e.asMessages());
			addErrors(request, errors);
			return mapping.findForward("failure");
		}
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		// TODO Auto-generated method stub
		return null;
	}
}
