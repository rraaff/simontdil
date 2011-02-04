package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;
import java.util.HashMap;

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
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.utils.DelegateSiteCache;

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
		createDocumentForm.setDetail(request.getParameter("detail"));
		createDocumentForm.setAppend(request.getParameter("append"));
		createDocumentForm.setNewParagraphText(request.getParameter("newParagraphText"));
		HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, createDocumentForm);
		//JSONObject json = JSONObject.fromObject(result);
		DelegateSiteCache.refresh();
		this.writeJsonResponse(result, response);
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
		boolean notHadBack = createDocumentForm.getParagraph() == 0;
		boolean wasLast = createDocumentForm.getLast().equals("true");
		String error = createDocumentForm.validateMove();
		if (StringUtils.isEmpty(error)) {
			String actualParagraph = createDocumentForm.performMove();
			result.put("result", "OK");
			result.put("actualParagraph", actualParagraph);
			boolean hasBack = createDocumentForm.getParagraph() != 0;
			boolean isLast = createDocumentForm.getLast().equals("true");
			if (!notHadBack && hasBack) {
				result.put("changeBack", "true");
			} else {
				result.put("changeBack", "false");
			}
			
			if (wasLast && !isLast) {
				result.put("changeLast", "true");
			} else {
				result.put("changeLast", "false");
			}
			if (!"true".equals(createDocumentForm.getAppend())) {
				result.put("paragraphs", createDocumentForm.getAllParagraphs());
			}
		} else {
			result.put("result", "ERROR");
			result.put("error", error);
		}
		return result;
	}
}
