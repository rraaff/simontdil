package com.tdil.simon.struts.actions.delegate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.DelegateNegotiationForm;

public class SignVersionAction extends SimonAction implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm)form;
		negotiationForm.setUser(this.getLoggedUser(request));
		negotiationForm.setRequest(request);
		HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, negotiationForm);	
		JSONObject json = JSONObject.fromObject(result);
//		response.setHeader("X-JSON", json.toString());
		response.getOutputStream().write(json.toString().getBytes());
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