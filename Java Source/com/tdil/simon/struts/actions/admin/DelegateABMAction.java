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
import com.tdil.simon.struts.actions.moderator.ABMAction;
import com.tdil.simon.struts.forms.DelegateABMForm;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class DelegateABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final DelegateABMForm delegateABMForm = (DelegateABMForm) form;

		if (isIndexedOperationByKey(request, "botones", "borrarFirma")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					delegateABMForm.deleteSignature(getIndexClicked(request));
					delegateABMForm.init();
				}
			});
			DelegateSiteCache.refresh();
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "desactivar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					delegateABMForm.delete(getIndexClicked(request));
					delegateABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			ValidationError error = (ValidationError)TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
					DelegateABMForm delegateABMForm = (DelegateABMForm)form;
					ValidationError error = delegateABMForm.reactivate(getIndexClicked(request));
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
			return mapping.findForward("continue");
		}
		
		if (StringUtils.equalsUnescaped(delegateABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			delegateABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(delegateABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "crear"))
				|| delegateABMForm.getOperation().equals(ResourceBundleCache.get(getServletInfo(), "modificar"))) {
			return this.validateAndSave(delegateABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "delegateABM";
	}
}
