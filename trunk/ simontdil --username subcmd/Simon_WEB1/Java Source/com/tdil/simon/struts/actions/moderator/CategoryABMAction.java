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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.forms.CategoryABMForm;
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;
import com.tdil.simon.utils.LoggerProvider;

public class CategoryABMAction extends ABMAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final CategoryABMForm categoryABMForm = (CategoryABMForm) form;

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							categoryABMForm.delete(imageSubmitData.getPosition());
							categoryABMForm.init();
						}
					});
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							categoryABMForm.reactivate(imageSubmitData.getPosition());
							categoryABMForm.init();
						}
					});
				}
				return mapping.findForward("continue");
			}
		}
		if (categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.cancel"))) {
			categoryABMForm.reset();
		}
		if (categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.create"))
				|| categoryABMForm.getOperation().equals(ApplicationResources.getMessage("categoryABM.modify"))) {
			return this.validateAndSave(categoryABMForm, request, mapping);
		} 
		return mapping.findForward("continue");
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(CategoryABMAction.class);
	}

}
