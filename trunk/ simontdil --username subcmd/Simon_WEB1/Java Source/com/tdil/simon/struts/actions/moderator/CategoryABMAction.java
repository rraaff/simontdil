package com.tdil.simon.struts.actions.moderator;

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
import com.tdil.simon.struts.forms.CategoryABMForm;
import com.tdil.simon.struts.forms.CountryABMForm;

public class CategoryABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CategoryABMForm categoryABMForm = (CategoryABMForm) form;

		if (categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.cancel"))) {
			categoryABMForm.reset();
		}
		if (categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.create"))
				|| categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.modify"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					categoryABMForm.save();
				}
			});
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					categoryABMForm.reset();
					categoryABMForm.init();
				}
			});
		}
		return mapping.findForward("continue");
	}


}
