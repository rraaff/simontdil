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
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;
import com.tdil.simon.web.ResourceBundleCache;

public class ReferenceDocumentABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ReferenceDocumentABMForm referenceDocumentABMForm = (ReferenceDocumentABMForm) form;

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							referenceDocumentABMForm.delete(imageSubmitData.getPosition());
							referenceDocumentABMForm.init();
						}
					});
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							referenceDocumentABMForm.reactivate(imageSubmitData.getPosition());
							referenceDocumentABMForm.init();
						}
					});
				}
				return mapping.findForward("continue");
			}
		}
		if (referenceDocumentABMForm.getOperation().equals(ResourceBundleCache.get("referenceDocumentABM", "cancelar"))) {
			referenceDocumentABMForm.reset();
		}
		if (referenceDocumentABMForm.getOperation().equals(ResourceBundleCache.get("referenceDocumentABM", "crear"))
				|| referenceDocumentABMForm.getOperation().equals(ResourceBundleCache.get("referenceDocumentABM", "modificar"))) {
			return this.validateAndSave(referenceDocumentABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}


}
