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
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.SystemUserABMForm;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class SystemUserABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final SystemUserABMForm systemUserABMForm = (SystemUserABMForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					systemUserABMForm.delete(getIndexClicked(request));
					systemUserABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					systemUserABMForm.reactivate(getIndexClicked(request));
					systemUserABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		
		
		if (StringUtils.equalsUnescaped(systemUserABMForm.getOperation(),ResourceBundleCache.get("systemUserABM", "cancelar"))) {
			systemUserABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(systemUserABMForm.getOperation(),ResourceBundleCache.get("systemUserABM", "crear"))
				|| systemUserABMForm.getOperation().equals(ResourceBundleCache.get("systemUserABM", "modificar"))) {
			return this.validateAndSave(systemUserABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}
}
