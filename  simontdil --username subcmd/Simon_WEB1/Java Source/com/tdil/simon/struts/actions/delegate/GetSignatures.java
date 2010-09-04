package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.DelegateNegotiationForm;

// TODO HACERLO
public class GetSignatures extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DELEGATE};
	
	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm) form;
		HashMap<String, String> result = (HashMap<String, String>)TransactionProvider.executeInTransaction(this, negotiationForm);
		JSONObject json = JSONObject.fromObject(result);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm) form;
		HashMap<String, String> result = new HashMap<String, String>();
		List<SignatureVO> all = SignatureDAO.selectSignaturesFor(negotiationForm.getVersionVO().getVersion().getId());
		for (SignatureVO signatureVO : all) {
			if (!negotiationForm.contains(signatureVO)) {
				negotiationForm.getSignatures().add(signatureVO);
				result.put("delegate", signatureVO.getDelegateName());
				result.put("signature", signatureVO.getSignatureFileName());
			}
		}
		return result;
	}
}
