package com.tdil.simon.struts.actions.delegate;

import java.io.IOException;
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
import com.tdil.simon.struts.forms.DelegateNegotiationForm;
import com.tdil.simon.utils.DelegateSiteCache;

public class SignVersionAction extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DELEGATE_AND_SIGN};
	
	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm)form;
		negotiationForm.setUser(this.getLoggedUser(request));
		negotiationForm.setRequest(request);
		HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, negotiationForm);	
		DelegateSiteCache.refresh();
		this.writeJsonResponse(result, response);
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm)form;
		try {
			negotiationForm.sign();
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("result", "OK");
			return result;
		} catch (IOException e) {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("result", "error");
			result.put("error", "general error");
			return result;
		}
	}
}
