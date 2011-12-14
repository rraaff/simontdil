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
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ViewVersionForm;
import com.tdil.simon.utils.LoggerProvider;

public class GoToViewVersionPopupAction extends SimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SystemUser user = getLoggedUser(request);
		if (user == null) {
			return mapping.findForward("notLogged");
		}
		if (!UserTypeValidation.isValid(user, this.getPermissions())) {
			getLog().fatal("Invalid action for " + this.getClass().getName() + " user " + user.getName());
			return mapping.findForward("invalidAction");
		}
		return this.basicExecute(mapping, form, request, response);
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		final ViewVersionForm viewVersionForm = (ViewVersionForm) form;
		viewVersionForm.setUser(this.getLoggedUser(request));
		final int versionID = Integer.parseInt(request.getParameter("versionID"));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				viewVersionForm.init(versionID);
				DelegateAuditDAO.registerViewVersion(viewVersionForm.getUser(), viewVersionForm.getVersion().getVersion());
			}
		});
//		if (viewVersionForm.isFinal()) {
//			if (viewVersionForm.hasTranslation()) {
//				return mapping.findForward("goToFinalVersion");
//			} else {
//				viewVersionForm.setShowSpanish(true);
//				return mapping.findForward("goToFinalVersionSingle");
//			}
//		} else {
			return mapping.findForward("continue");
//		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(GoToViewVersionPopupAction.class);
	}
}
