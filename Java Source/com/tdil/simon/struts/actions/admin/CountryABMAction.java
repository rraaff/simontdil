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
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;
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

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					ValidationError error = (ValidationError)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
						public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
							CountryABMForm countryABMForm = (CountryABMForm)form;
							ValidationError error = countryABMForm.delete(imageSubmitData.getPosition());
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
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							countryABMForm.reactivate(imageSubmitData.getPosition());
							countryABMForm.init();
						}
					});
				}
				return mapping.findForward("continue");
			}
		}
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

	protected boolean isIndexedOperation(final HttpServletRequest request, String context, String key) {
		String op = request.getParameter("indexOperation");
		if (op == null || op.length() == 0) {
			return false;
		}
		return op.equals(ResourceBundleCache.get(context, key));
	}
	protected int getIndexClicked(final HttpServletRequest request) {
		return Integer.parseInt(request.getParameter("indexClicked"));
	}
	protected String getParamClicked(final HttpServletRequest request) {
		return request.getParameter("indexClicked");
	}
	
	private String getServletInfo() {
		return "countryABM";
	}


}
