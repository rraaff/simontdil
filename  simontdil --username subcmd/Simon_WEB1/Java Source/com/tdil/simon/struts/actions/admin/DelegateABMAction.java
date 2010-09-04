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
import com.tdil.simon.struts.forms.DelegateABMForm;

public class DelegateABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final DelegateABMForm delegateABMForm = (DelegateABMForm) form;

		if (delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.cancel"))) {
			delegateABMForm.reset();
		}
		if (delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.create"))
				|| delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.modify"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					delegateABMForm.save();
				}
			});
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					delegateABMForm.reset();
					delegateABMForm.refreshUserList();
				}
			});
		}
		return mapping.findForward("continue");
	}
}
