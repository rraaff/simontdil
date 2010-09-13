package com.tdil.simon.struts.actions.admin;

import java.sql.SQLException;

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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.DelegateABMForm;
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;

public class DelegateABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final DelegateABMForm delegateABMForm = (DelegateABMForm) form;

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							delegateABMForm.delete(imageSubmitData.getPosition());
							delegateABMForm.init();
						}
					});
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					ValidationError error = (ValidationError)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
						public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
							DelegateABMForm delegateABMForm = (DelegateABMForm)form;
							ValidationError error = delegateABMForm.reactivate(imageSubmitData.getPosition());
							if (error != null && error.hasError()) {
								return error;
							} else {
								delegateABMForm.init();
								return null;
							}
						}
					}, delegateABMForm);
					if (error != null && error.hasError()) {
						return redirectToFailure(error, request, mapping);
					}
				}
				return mapping.findForward("continue");
			}
		}
		if (delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.cancel"))) {
			delegateABMForm.reset();
		}
		if (delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.create"))
				|| delegateABMForm.getOperation().equals(ApplicationResources.getMessage("delegateABM.modify"))) {
			return this.validateAndSave(delegateABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}
}
