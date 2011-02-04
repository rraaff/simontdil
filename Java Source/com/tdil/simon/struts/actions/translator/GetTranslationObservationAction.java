package com.tdil.simon.struts.actions.translator;

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
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.ObservationForm;

public class GetTranslationObservationAction extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.TRANSLATOR, UserTypeValidation.DELEGATE};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO validaciones
		String pNumber=request.getParameter("pNumber");
		String pVersion=request.getParameter("pVersion");
		ObservationForm observationForm = new ObservationForm();
		observationForm.setParagraphNumber(pNumber);
		observationForm.setVersionId(pVersion);
		observationForm.setUser(this.getLoggedUser(request));
		// TODO Try catch y devolver error
		HashMap result = (HashMap)TransactionProvider.executeInTransaction(this, observationForm);
		this.writeJsonResponse(result, response);
		return null;
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ObservationForm observationForm = (ObservationForm)form;
		Observation observation = observationForm.getPortuguesTranslation();
		HashMap result = new HashMap();
		result.put("result", "OK");
		if (observation != null) {
			result.put("exists", "true");
			result.put("translation", observation.getObservationText());
		} else {
			result.put("exists", "false");
			result.put("translation", "");
		}
		return result;
	}
	
}
