package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.ObservationUtils;

public class CountPrivateMessagesForVersion extends Action implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap hm = (HashMap)TransactionProvider.executeInTransaction(this, form);
		JSONObject json = JSONObject.fromObject(hm);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		HashMap hm = new HashMap();
		String result = String.valueOf(ObservationUtils.countPrivateObservationsForNegotiatedVersion());
		hm.put("count",result);
		return hm;
	}
}
