package com.tdil.simon.struts.actions.moderator;

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
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ObservationForm;
import com.tdil.simon.struts.forms.PrivateMessageForm;

public class AddObservationAction extends SimonAction implements TransactionalActionWithValue {


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO validaciones
		String pNumber=request.getParameter("pNumber");
		String newPar=request.getParameter("newPar");
		String pText=request.getParameter("pText");
		String pVersion=request.getParameter("pVersion");
		System.out.println(pText);
		ObservationForm observationForm = new ObservationForm();
		observationForm.setParagraphNumber(pNumber);
		observationForm.setNewParagraph(newPar);
		observationForm.setParagraphText(pText);
		observationForm.setVersionId(pVersion);
		observationForm.setUserId(this.getLoggedUser(request).getId());
		// TODO Try catch y devolver error
		HashMap result = (HashMap)TransactionProvider.executeInTransaction(this, observationForm);
		JSONObject json = JSONObject.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
//		response.setHeader("X-JSON", json.toString());
//		return mapping.findForward("ajaxReturn");

	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		// TODO validaciones
		ObservationForm observationForm = (ObservationForm)form;
		Integer versionId = Integer.valueOf(observationForm.getVersionId());
		Integer number = Integer.valueOf(observationForm.getParagraphNumber());
		boolean newParagraph = "true".equals(observationForm.getNewParagraph());
		String text = observationForm.getParagraphText();
		Paragraph paragraph = ParagraphDAO.getParagraph(versionId, number);
		Observation observation = new Observation();
		observation.setParagraphId(paragraph.getId());
		observation.setPrivateObservation(false);
		observation.setAddNewParagraph(newParagraph);
		observation.setUserId(observationForm.getUserId());
		observation.setObservationText(text);
		observation.setDeleted(false);
		ObservationDAO.insertObservation(observation);
		HashMap result = new HashMap();
		result.put("result", "OK");
		return result;
	}
	
}
