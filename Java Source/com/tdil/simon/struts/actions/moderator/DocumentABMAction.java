package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ListForm;
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;

public class DocumentABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ListForm versionListForm = (ListForm) form;

		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("deleteImages".equals(imageSubmitData.getProperty())) {
					try {
						TransactionProvider.executeInTransaction(new TransactionalAction() {
							public void executeInTransaction() throws SQLException, ValidationException {
								versionListForm.deleteVersion(imageSubmitData.getPosition());
							}
						});
					} catch (ValidationException e) {
						ActionErrors errors = new ActionErrors();
						errors.add(e.asMessages());
						addErrors(request, errors);
					}
				}
				if ("reactivateImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							versionListForm.reactivateVersion(imageSubmitData.getPosition());
						}
					});
				}
				if ("disableCommentsImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							versionListForm.disableCommentsForVersion(imageSubmitData.getPosition());
						}
					});
				}
				if ("enableCommentsImages".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							versionListForm.enableCommentsForVersion(imageSubmitData.getPosition());
						}
					});
				}
				return mapping.findForward("continue");
			}
		}
		return null;
	}

}
