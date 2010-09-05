package com.tdil.simon.struts.actions.moderator;

import java.io.FileNotFoundException;
import java.io.IOException;
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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ReferenceDocumentABMForm;

public class ReferenceDocumentABMAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ReferenceDocumentABMForm referenceDocumentABMForm = (ReferenceDocumentABMForm) form;

		if (referenceDocumentABMForm.getOperation().equals(ApplicationResources.getMessage("referenceDocumentABM.cancel"))) {
			referenceDocumentABMForm.reset();
		}
		if (referenceDocumentABMForm.getOperation().equals(ApplicationResources.getMessage("referenceDocumentABM.create"))
				|| referenceDocumentABMForm.getOperation().equals(ApplicationResources.getMessage("referenceDocumentABM.modify"))) {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					try {
						referenceDocumentABMForm.save();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					// TODO Auto-generated method stub
					referenceDocumentABMForm.reset();
					referenceDocumentABMForm.init();
				}
			});
		}
		return mapping.findForward("continue");
	}


}
