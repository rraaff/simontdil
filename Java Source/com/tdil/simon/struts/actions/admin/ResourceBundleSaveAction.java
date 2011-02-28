package com.tdil.simon.struts.actions.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.ResourceBundleForm;
import com.tdil.simon.utils.LoggerProvider;

public class ResourceBundleSaveAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ResourceBundleForm resourceBundleForm = (ResourceBundleForm) form;

		if (resourceBundleForm.getOperation().equals(ApplicationResources.getMessage("resourceBundle.cancel"))) {
			resourceBundleForm.reset();
		}
		if (resourceBundleForm.getOperation().equals(ApplicationResources.getMessage("resourceBundle.search"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					resourceBundleForm.search();
				}
			});
		}
		if (resourceBundleForm.getOperation().equals(ApplicationResources.getMessage("resourceBundle.create"))
				|| resourceBundleForm.getOperation().equals(ApplicationResources.getMessage("resourceBundle.modify"))) {
			return this.validateAndSave(resourceBundleForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(ResourceBundleSaveAction.class);
	}

}
