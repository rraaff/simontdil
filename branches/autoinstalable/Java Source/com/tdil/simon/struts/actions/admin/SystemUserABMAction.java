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
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;
import com.tdil.simon.web.ResourceBundleCache;

public class SystemUserABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final SystemUserABMForm systemUserABMForm = (SystemUserABMForm) form;

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							systemUserABMForm.delete(imageSubmitData.getPosition());
							systemUserABMForm.init();
						}
					});
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							systemUserABMForm.reactivate(imageSubmitData.getPosition());
							systemUserABMForm.init();
						}
					});
				}
				return mapping.findForward("continue");
			}
		}
		if (systemUserABMForm.getOperation().equals(ResourceBundleCache.get("systemUserABM", "cancelar"))) {
			systemUserABMForm.reset();
		}
		if (systemUserABMForm.getOperation().equals(ResourceBundleCache.get("systemUserABM", "crear"))
				|| systemUserABMForm.getOperation().equals(ResourceBundleCache.get("systemUserABM", "modificar"))) {
			return this.validateAndSave(systemUserABMForm, request, mapping);
		}
		return mapping.findForward("continue");
	}
}
