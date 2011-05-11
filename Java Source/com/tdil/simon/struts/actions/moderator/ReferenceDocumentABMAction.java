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
import com.tdil.simon.struts.forms.ReferenceDocumentABMForm;
import com.tdil.simon.utils.StringUtils;
import com.tdil.simon.web.ResourceBundleCache;

public class ReferenceDocumentABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ReferenceDocumentABMForm referenceDocumentABMForm = (ReferenceDocumentABMForm) form;

		if (isIndexedOperation(request, "botones", "desactivar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					referenceDocumentABMForm.delete(getIndexClicked(request));
					referenceDocumentABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		if (isIndexedOperation(request, "botones", "activar")) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					referenceDocumentABMForm.reactivate(getIndexClicked(request));
					referenceDocumentABMForm.init();
				}
			});
			return mapping.findForward("continue");
		}
		
		if (StringUtils.equalsUnescaped(referenceDocumentABMForm.getOperation(),ResourceBundleCache.get("referenceDocumentABM", "cancelar"))) {
			referenceDocumentABMForm.reset();
		}
		if (StringUtils.equalsUnescaped(referenceDocumentABMForm.getOperation(),ResourceBundleCache.get("referenceDocumentABM", "crear"))
				|| referenceDocumentABMForm.getOperation().equals(ResourceBundleCache.get("referenceDocumentABM", "modificar"))) {
			return this.validateAndSave(referenceDocumentABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}


}
