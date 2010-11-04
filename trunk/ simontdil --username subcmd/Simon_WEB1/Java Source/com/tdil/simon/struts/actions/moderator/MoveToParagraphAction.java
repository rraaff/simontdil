package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class MoveToParagraphAction extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CreateDocumentForm createDocumentForm = (CreateDocumentForm)form;
		createDocumentForm.setDestination(request.getParameter("destination"));
		createDocumentForm.setAppend(request.getParameter("append"));
		createDocumentForm.setNewParagraphText(request.getParameter("newParagraphText"));
		HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, createDocumentForm);
		//JSONObject json = JSONObject.fromObject(result);
		this.writeJsonResponse(result, response);
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		HashMap<String, String> result = new HashMap<String, String>();
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
		// TODO validaciones y retornar error
		String actualParagraph = createDocumentForm.performMove();
		// TODO retornar nuevo numero de parrafo actual si aplica
		result.put("result", "OK");
		return result;
	}
}
