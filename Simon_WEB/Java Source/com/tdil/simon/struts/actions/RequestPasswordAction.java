package com.tdil.simon.struts.actions;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.RequestPasswordForm;

public class RequestPasswordAction extends Action implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean result = (Boolean)TransactionProvider.executeInTransaction(this, form);
		if (result) {
			return mapping.findForward("continue");
		} else {
			ValidationError error = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			ActionMessages msg = error.asMessages();
			if (msg != null) {
				request.setAttribute("hasError", "true");
				addMessages(request, msg);
			}
			ActionMessages errors = error.asActionsErrors();
			if (errors != null) {
				addErrors(request, errors);	
			}
			return mapping.findForward("stay");
		}
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		RequestPasswordForm requestPasswordForm = (RequestPasswordForm)form;
		return requestPasswordForm.requestPassword();
	}
}
