package com.tdil.simon.struts.actions.delegate;

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
import com.tdil.simon.struts.forms.PrivateMessageForm;

public class AddPrivateMessageForVersion extends SimonAction implements TransactionalActionWithValue {


	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO validaciones
		String versionID=request.getParameter("versionID");
		String paragraphNumber=request.getParameter("paragraphNumber");
		String message=request.getParameter("message");
		
		HashMap result = (HashMap)TransactionProvider.executeInTransaction(this, new PrivateMessageForm(versionID, paragraphNumber, message));
		JSONObject json = JSONObject.fromObject(result);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");

	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		// TODO validaciones
		PrivateMessageForm privateMessageForm = (PrivateMessageForm)form;
		Integer versionId = Integer.valueOf(privateMessageForm.getVersionID());
		Integer number = Integer.valueOf(privateMessageForm.getParagraphNumber());
		Paragraph paragraph = ParagraphDAO.getParagraph(versionId, number);
		Observation observation = new Observation();
		observation.setParagraphId(paragraph.getId());
		observation.setPrivateObservation(true);
		observation.setObservationText(privateMessageForm.getMessage());
		observation.setDeleted(false);
		ObservationDAO.insertObservation(observation);
		HashMap result = new HashMap();
		result.put("MESSAGE", "OK");
		return result;
	}
	
}
