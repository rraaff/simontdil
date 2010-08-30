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
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.LoggedUserForm;

public class GetDelegateSiteStatus extends SimonAction implements TransactionalActionWithValue {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoggedUserForm loggedUserForm = new LoggedUserForm();
		loggedUserForm.setUser(this.getLoggedUser(request));
		HashMap<String, String> result = (HashMap<String, String>) TransactionProvider.executeInTransaction(this, loggedUserForm);
		JSONObject json = JSONObject.fromObject(result);
		response.setHeader("X-JSON", json.toString());
		return mapping.findForward("ajaxReturn");

	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		
		LoggedUserForm loggedUserForm = (LoggedUserForm)form;
		HashMap<String, String> result = new HashMap<String, String>();
		Site delegateSite = Site.getDELEGATE_SITE();
		
		if (!Site.NORMAL.equals(delegateSite.getStatus())) {
			Document doc = DocumentDAO.getDocumentUnderWork();
			if ((doc.isTypeOne() && loggedUserForm.getUser().isTypeOne()) ||
					(doc.isTypeTwo() && loggedUserForm.getUser().isTypeTwo())) {
				result.put("sitestatus", delegateSite.getStatus());
				Paragraph paragraph = ParagraphDAO.getParagraph(delegateSite.getDataId());
				if (paragraph != null) {
					result.put("paragraphNumber", String.valueOf(paragraph.getParagraphNumber()));
					result.put("paragraphText", String.valueOf(paragraph.getParagraphText()));
				} else {
					result.put("paragraphNumber", "0");
				}
			} else {
				result.put("sitestatus", Site.NORMAL);
			}
		} else {
			result.put("sitestatus", Site.NORMAL);
		}
		return result;
	}

}
