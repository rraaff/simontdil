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
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.UserGroupABMForm;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class UserGroupABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final UserGroupABMForm userGroupABMForm = (UserGroupABMForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					userGroupABMForm.delete(getIndexClicked(request));
					userGroupABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					userGroupABMForm.reactivate(getIndexClicked(request));
					userGroupABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		
		if (StringUtils.equalsUnescaped(userGroupABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			userGroupABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(userGroupABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "crear"))
				|| userGroupABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificar"))) {
			return this.validateAndSave(userGroupABMForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "userGroupABM";
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(UserGroupABMAction.class);
	}

}
