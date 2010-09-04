package com.tdil.simon.struts.actions.admin;

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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CountryABMForm;
import com.tdil.simon.struts.forms.DelegateABMForm;

public class CountryABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CountryABMForm countryABMForm = (CountryABMForm) form;

		if (countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.cancel"))) {
			countryABMForm.reset();
		}
		if (countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.create"))
				|| countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.modify"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					countryABMForm.save();
				}
			});
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					countryABMForm.reset();
					countryABMForm.init();
				}
			});
		}
		return mapping.findForward("continue");
	}


}
