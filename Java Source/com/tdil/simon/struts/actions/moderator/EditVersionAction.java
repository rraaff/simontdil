package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.NegotiationUtils;

public class EditVersionAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR, UserTypeValidation.DESIGNER};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
		int versionID;
		if (!StringUtils.isEmpty(request.getParameter("id"))) {
			versionID = Integer.parseInt(request.getParameter("id"));
		} else {
			versionID = Integer.parseInt((String)request.getAttribute("id"));
		}
		createDocumentForm.setTemporaryVersionId(versionID);
		TransactionProvider.executeInTransaction(this, form);
		boolean inNegotiation = NegotiationUtils.isInNegotiation(createDocumentForm);
		request.getSession().setAttribute("docNegotiated", inNegotiation ? "true" : "false");
		request.getSession().setAttribute("paragraphNegotiated", "false");
		return mapping.findForward("continue");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
		createDocumentForm.initWith(createDocumentForm.getTemporaryVersionId());
		return null;
	}
}
