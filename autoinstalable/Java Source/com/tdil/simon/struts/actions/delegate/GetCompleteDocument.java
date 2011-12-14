package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.LoggedUserForm;
import com.tdil.simon.utils.DelegateSiteCache;

public class GetCompleteDocument extends AjaxSimonAction {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DELEGATE};
	
	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoggedUserForm loggedUserForm = new LoggedUserForm();
		loggedUserForm.setUser(this.getLoggedUser(request));
		HashMap<String, String> result = (HashMap<String, String>) executeInTransaction(loggedUserForm);
//		System.out.println(result);
		this.writeJsonResponse(result, response);
		return null;
	}
	
	// TODO no es en transaccion
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		LoggedUserForm loggedUserForm = (LoggedUserForm) form;
		HashMap<String, Object> result = new HashMap<String, Object>();

		String delegateSiteStatus = DelegateSiteCache.getDelegateSiteStatus();
		if (Site.NORMAL.equals(delegateSiteStatus)) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		Document doc = DelegateSiteCache.getDocumentUnderWork();
		if (!loggedUserForm.getUser().hasPermissionFor(doc)) {
			result.put("sitestatus", Site.NORMAL);
			return result;
		}
		List<String> pNumbers = new ArrayList<String>();
		List<String> pComments = new ArrayList<String>();
		List<String> pTexts = new ArrayList<String>();
		for (Paragraph p : DelegateSiteCache.getAllParagraphs()) {
			pNumbers.add(String.valueOf(p.getParagraphNumber()));
			pComments.add(p.getNumberDetail() != null ? p.getNumberDetail() : "");
			pTexts.add(p.getParagraphText());
		}
		result.put("paragraphNumber", pNumbers.toArray(new String[0]));
		result.put("paragraphComments", pComments.toArray(new String[0]));
		result.put("paragraph", pTexts.toArray(new String[0]));
		return result;
	}

}
