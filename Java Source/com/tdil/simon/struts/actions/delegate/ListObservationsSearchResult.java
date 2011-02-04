package com.tdil.simon.struts.actions.delegate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.SearchObservationsForm;

public class ListObservationsSearchResult extends SimonAction {

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
		if (searchObservationsForm.getOperation().equals(ApplicationResources.getMessage("searchObservations.backFromSearch"))) {
			return mapping.findForward("goToSearch");
		}
		return null;
	}

}
