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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.SearchObservationsForm;

public class SearchObservationsAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SearchObservationsForm searchObservationsForm = (SearchObservationsForm)form;
		if (searchObservationsForm.getOperation().equals(ApplicationResources.getMessage("searchObservations.search"))) {
			TransactionProvider.executeInTransaction(this, form);
			return mapping.findForward("continue");
		}
		if (searchObservationsForm.getOperation().equals(ApplicationResources.getMessage("searchObservations.back"))) {
			return mapping.findForward("back");
		}
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		SearchObservationsForm searchObservationsForm = (SearchObservationsForm)form;
		searchObservationsForm.search();
		return null;
	}
}
