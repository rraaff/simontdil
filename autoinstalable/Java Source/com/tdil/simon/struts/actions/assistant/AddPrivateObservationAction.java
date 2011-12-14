package com.tdil.simon.struts.actions.assistant;

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
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.ObservationForm;

public class AddPrivateObservationAction extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.ASSISTANT};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO validaciones
		String pNumber=request.getParameter("pNumber");
		String newPar=request.getParameter("newPar");
		String pText=request.getParameter("pText");
		String pVersion=request.getParameter("pVersion");
		ObservationForm observationForm = new ObservationForm();
		observationForm.setParagraphNumber(pNumber);
		observationForm.setNewParagraph(newPar);
		observationForm.setParagraphText(pText);
		observationForm.setVersionId(pVersion);
		observationForm.setUser(this.getLoggedUser(request));
		// TODO Try catch y devolver error
		HashMap result = (HashMap)TransactionProvider.executeInTransaction(this, observationForm);
		this.writeJsonResponse(result, response);
		return null;
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		// TODO validaciones
		ObservationForm observationForm = (ObservationForm)form;
		Integer versionId = Integer.valueOf(observationForm.getVersionId());
		Integer number = Integer.valueOf(observationForm.getParagraphNumber());
		boolean newParagraph = "true".equals(observationForm.getNewParagraph());
		String text = observationForm.getParagraphText();
		Paragraph paragraph = ParagraphDAO.getParagraph(versionId, number);
		observationForm.setParagraphNumberForDiplay(paragraph.getParagraphNumberForDisplay());
		Observation observation = new Observation();
		observation.setParagraphId(paragraph.getId());
		observation.setPrivateObservation(true);
		observation.setAddNewParagraph(newParagraph);
		observation.setUserId(observationForm.getUser().getId());
		observation.setObservationText(text);
		observation.setDeleted(false);
		int id = ObservationDAO.insertObservation(observation);
		observation.setId(id);
		HashMap result = new HashMap();
		result.put("result", "OK");
		return result;
	}
	
}
