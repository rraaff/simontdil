package com.tdil.simon.struts.actions.admin;

import java.sql.SQLException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.CountryABMForm;
import com.tdil.simon.web.LogOnceListener;
import com.tdil.simon.web.ResourceBundleCache;

public class CountryABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(final ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CountryABMForm countryABMForm = (CountryABMForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			ValidationError error = (ValidationError)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
					CountryABMForm countryABMForm = (CountryABMForm)form;
					ValidationError error = countryABMForm.delete(getIndexClicked(request));
					if (error != null && error.hasError()) {
						return error;
					} else {
						countryABMForm.init();
						return null;
					}
				}

			}, countryABMForm);
			if (error != null && error.hasError()) {
				return redirectToFailure(error, request, mapping);
			} else {
				// desloguear si hay alguien logueado con un pais borrado
				Set<Integer> deletedCountries = countryABMForm.getDeletedCountries();
				LogOnceListener.logoutDelegatesFor(deletedCountries);
			}
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					countryABMForm.reactivate(getIndexClicked(request));
					countryABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (countryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			countryABMForm.reset();
		}
		if (countryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "crear"))
				|| countryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificar"))) {
			return this.validateAndSave(countryABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "countryABM";
	}


}
