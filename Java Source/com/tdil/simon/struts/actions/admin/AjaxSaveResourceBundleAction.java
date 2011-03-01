package com.tdil.simon.struts.actions.admin;

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
import com.tdil.simon.struts.forms.ResourceBundleForm;
import com.tdil.simon.web.ResourceBundleCache;

public class AjaxSaveResourceBundleAction extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.ADMINISTRATOR};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String rbContext=request.getParameter("rbContext");
		String rbKey=request.getParameter("rbKey");
		String rbValue=request.getParameter("rbValue");
		ResourceBundleForm resourceBundleForm = new ResourceBundleForm();
		resourceBundleForm.setRbContext(rbContext);
		resourceBundleForm.setRbKey(rbKey);
		resourceBundleForm.setRbValue(rbValue);
		try {
			HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, resourceBundleForm);
			ResourceBundleCache.put(rbContext, rbKey, rbValue);
			this.writeJsonResponse(result, response);
		} catch (Exception e) {
			HashMap<String, String> result = new HashMap<String, String>();
			result.put("result", "error");
			result.put("error", "No se pudo salvar");
			result.put("rbValue", ResourceBundleCache.get(rbContext, rbKey));
			this.writeJsonResponse(result, response);
		}
		return null;
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ResourceBundleForm resourceBundleForm = (ResourceBundleForm)form;
		resourceBundleForm.save();
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("result", "OK");
		return result;
	}
	
}
