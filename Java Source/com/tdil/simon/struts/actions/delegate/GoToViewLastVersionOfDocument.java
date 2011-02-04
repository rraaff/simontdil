package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ViewVersionForm;

public class GoToViewLastVersionOfDocument extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ViewVersionForm viewVersionForm = (ViewVersionForm) form;
		viewVersionForm.setUser(this.getLoggedUser(request));
		final int documentID = Integer.parseInt(request.getParameter("documentID"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				Version version = VersionDAO.getVersionForNegotiation(documentID);
				viewVersionForm.init(version.getId());
				DelegateAuditDAO.registerViewVersion(viewVersionForm.getUser(), viewVersionForm.getVersion().getVersion());
			}
		});

		return mapping.findForward("continue");
	}

}
