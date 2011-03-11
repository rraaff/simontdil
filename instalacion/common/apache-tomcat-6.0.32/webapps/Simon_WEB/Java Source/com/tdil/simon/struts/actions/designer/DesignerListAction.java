package com.tdil.simon.struts.actions.designer;

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
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.utils.ImageSubmitData;
import com.tdil.simon.utils.ImageTagUtil;

public class DesignerListAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DESIGNER};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String image = ImageTagUtil.getName(request);
		if (image != null) {
			final ImageSubmitData imageSubmitData = new ImageSubmitData(image);
			if (imageSubmitData.isParsed())  {
				if ("editImage".equals(imageSubmitData.getProperty())) {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							// versionListForm.deleteVersion(imageSubmitData.getPosition()); TODO EDITA
						}
					});
					return mapping.findForward("edit");
				}
			}
		}
		return null;
	}

}
