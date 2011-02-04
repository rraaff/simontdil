package com.tdil.simon.struts.actions.admin;

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
import com.tdil.simon.struts.forms.ResetPasswordForm;

public class ResetPasswordAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ResetPasswordForm resetPasswordForm = (ResetPasswordForm) form;

		if (resetPasswordForm.getOperation().equals(ApplicationResources.getMessage("resetPassword.resetPassword"))) {
			TransactionProvider.executeInTransaction(this, resetPasswordForm);
		}
		return mapping.findForward("stay");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ResetPasswordForm resetPasswordForm = (ResetPasswordForm) form;
		resetPasswordForm.resetSelectedPasswords();
		resetPasswordForm.init();
		return null;
	}
}
