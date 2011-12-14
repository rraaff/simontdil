package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.NegotiateParagraphForm;

/**
 * @deprecated
 * @author mgodoy
 * 
 */
public class SaveNegotiatedParagraphAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		NegotiateParagraphForm negotiateParagraphForm = (NegotiateParagraphForm) form;
		TransactionProvider.executeInTransaction(this, form);
		if ("Guardar".equals(negotiateParagraphForm.getOperation())) {
			return mapping.findForward("stay");
		} else {
			return mapping.findForward("continue");
		}
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		NegotiateParagraphForm negotiateParagraphForm = (NegotiateParagraphForm) form;
		Paragraph paragraph = ParagraphDAO.getParagraph(negotiateParagraphForm.getParagraphId());
		paragraph.setParagraphText(negotiateParagraphForm.getParagraphText());
		ParagraphDAO.updateParagraph(paragraph);
		if ("Guardar y Continuar".equals(negotiateParagraphForm.getOperation())) {

		} else {

		}
		return null;
	}
}
