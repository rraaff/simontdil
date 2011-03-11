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
import com.tdil.simon.data.valueobjects.ObservationSummaryVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.CountPrivMesagesForm;
import com.tdil.simon.utils.ObservationUtils;

public class CountPrivateMessagesForVersion extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR, UserTypeValidation.MODERATOR};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CountPrivMesagesForm countPrivMesagesForm = new CountPrivMesagesForm();
		countPrivMesagesForm.setFull(request.getParameter("full"));
		countPrivMesagesForm.setDocNegotiation((String) request.getSession().getAttribute("docNegotiated"));
		countPrivMesagesForm.setParagraphNegotiation((String) request.getSession().getAttribute("paragraphNegotiated"));
		HashMap hm = null;
		if (countPrivMesagesForm.mustAnswer()) {
			hm = (HashMap) TransactionProvider.executeInTransaction(this, countPrivMesagesForm);
		} else {
			HashMap result = new HashMap();
			result.put("count", "0");
			result.put("maxId", "0");
			hm = result;
		}
		this.writeJsonResponse(hm, response);
		return null;
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		CountPrivMesagesForm countPrivMesagesForm = (CountPrivMesagesForm) form;
		if ("true".equals(countPrivMesagesForm.getFull())) {
			HashMap hm = new HashMap();
			ObservationSummaryVO observationSummaryVO = ObservationUtils.countPrivateObservationsForNegotiatedVersion();
			hm.put("count", String.valueOf(observationSummaryVO.getCount()));
			hm.put("maxId", String.valueOf(observationSummaryVO.getMaxId()));
			return hm;
		} else {
			HashMap hm = new HashMap();
			ObservationSummaryVO observationSummaryVO = ObservationUtils.countPrivateObservationsForNegotiatedParagraph();
			hm.put("count", String.valueOf(observationSummaryVO.getCount()));
			hm.put("maxId", String.valueOf(observationSummaryVO.getMaxId()));
			return hm;
		}
	}
}
