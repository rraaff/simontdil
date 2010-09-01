package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.DelegateNegotiationForm;

public class GoToDelegateNegotiation extends SimonAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final DelegateNegotiationForm negotiationForm = (DelegateNegotiationForm)form;
		negotiationForm.setUser(this.getLoggedUser(request));
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				negotiationForm.init();
			}
		});
		if (negotiationForm.isGoToSignShow()) {
			return mapping.findForward("signShow");
		} else {
			return mapping.findForward("continue");
		}
	}
}
