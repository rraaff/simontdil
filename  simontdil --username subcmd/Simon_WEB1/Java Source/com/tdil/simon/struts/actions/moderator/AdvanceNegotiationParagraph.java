package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;

public class AdvanceNegotiationParagraph extends Action implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
