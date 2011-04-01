package com.tdil.simon.struts.actions.moderator;

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
import com.tdil.simon.struts.forms.CategoryABMForm;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.ResourceBundleCache;

public class CategoryABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CategoryABMForm categoryABMForm = (CategoryABMForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					categoryABMForm.delete(getIndexClicked(request));
					categoryABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					categoryABMForm.reactivate(getIndexClicked(request));
					categoryABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		
		if (categoryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			categoryABMForm.reset();
		}
		if (categoryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "crear"))
				|| categoryABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificar"))) {
			return this.validateAndSave(categoryABMForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "categoryABM";
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(CategoryABMAction.class);
	}

}
