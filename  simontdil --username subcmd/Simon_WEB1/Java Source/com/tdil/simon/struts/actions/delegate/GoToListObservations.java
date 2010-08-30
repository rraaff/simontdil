package com.tdil.simon.struts.actions.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.valueobjects.VersionForListVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ListForm;

public class GoToListObservations extends SimonAction implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		TransactionProvider.executeInTransaction(this, form);
		return mapping.findForward("continue");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ListForm listForm = (ListForm)form;
		List<VersionForListVO> result = VersionDAO.selectVersionsVOForList();
		listForm.setList(result);
		return null;
	}
}
