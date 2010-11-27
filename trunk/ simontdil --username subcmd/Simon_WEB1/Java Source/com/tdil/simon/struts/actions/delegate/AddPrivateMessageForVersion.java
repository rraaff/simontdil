package com.tdil.simon.struts.actions.delegate;

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
import com.tdil.simon.data.model.Site;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.AjaxSimonAction;
import com.tdil.simon.struts.forms.PrivateMessageForm;

public class AddPrivateMessageForVersion extends AjaxSimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] {UserTypeValidation.DELEGATE};

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO validaciones
		String message=request.getParameter("message");
		PrivateMessageForm privateMessageForm = (PrivateMessageForm)form;
		HashMap result = (HashMap)TransactionProvider.executeInTransaction(this, new PrivateMessageForm(null, null, message, this.getLoggedUser(request).getId()));
		this.writeJsonResponse(result, response);
		return null;
//		return mapping.findForward("ajaxReturn");

	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		// TODO validaciones
		PrivateMessageForm privateMessageForm = (PrivateMessageForm)form;
		Paragraph paragraph = ParagraphDAO.getParagraph(Site.getDELEGATE_SITE().getDataId());
		
		HashMap result = new HashMap();
		if (paragraph != null) {
			Observation observation = new Observation();
			observation.setParagraphId(paragraph.getId());
			observation.setPrivateObservation(true);
			observation.setUserId(privateMessageForm.getUserId());
			observation.setObservationText(privateMessageForm.getMessage());
			observation.setDeleted(false);
			ObservationDAO.insertObservation(observation);
			result.put("result", "OK");
		}
		return result;
	}
	
}
