package com.tdil.simon.struts.actions.admin;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;

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

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							countryABMForm.delete(imageSubmitData.getPosition());
							countryABMForm.init();
						}
					});
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
		if (countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.cancel"))) {
			countryABMForm.reset();
		}
		if (countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.create"))
				|| countryABMForm.getOperation().equals(ApplicationResources.getMessage("countryABM.modify"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					try {
						countryABMForm.save();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
