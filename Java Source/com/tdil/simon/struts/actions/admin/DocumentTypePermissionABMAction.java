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
import com.tdil.simon.struts.forms.DocumentTypePermissionABMForm;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.PermissionCache;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class DocumentTypePermissionABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final DocumentTypePermissionABMForm documentTypePermissionABMForm = (DocumentTypePermissionABMForm) form;

		if (isIndexedOperation(request, "botones", "quitar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					documentTypePermissionABMForm.delete(getIndexClicked(request));
					documentTypePermissionABMForm.init();
				}
			});
			PermissionCache.refresh();
			return mapping.findForward("continue");
		}
		if (StringUtils.equalsUnescaped(documentTypePermissionABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "cancelar"))) {
			documentTypePermissionABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(documentTypePermissionABMForm.getOperation(),ResourceBundleCache.get(getServletInfo(), "agregar"))) {
			ActionForward result = this.validateAndSave(documentTypePermissionABMForm, request, mapping);
			PermissionCache.refresh();
			return result;
		} 
		return mapping.findForward("continue");
	}

	private String getServletInfo() {
		return "documentTypePermissionABM";
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(DocumentTypePermissionABMAction.class);
	}

}
