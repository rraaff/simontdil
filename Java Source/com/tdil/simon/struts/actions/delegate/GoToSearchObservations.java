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
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.SearchObservationsForm;

public class GoToSearchObservations extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchObservationsForm searchObservationsForm = (SearchObservationsForm) form;
		searchObservationsForm.setUser(this.getLoggedUser(request));
		searchObservationsForm.setVersionId(Integer.valueOf((String) request.getAttribute("versionId")));
		TransactionProvider.executeInTransaction(this, form);
		return mapping.findForward("continue");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		SearchObservationsForm searchObservationsForm = (SearchObservationsForm) form;
		searchObservationsForm.init();
		return null;
	}
}
