package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.ListForm;

public class GoToListPrivateObservations extends Action implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ListForm listForm = (ListForm)form;
		listForm.addParam("paragraphNegotiated", (String)request.getSession().getAttribute("paragraphNegotiated"));
		TransactionProvider.executeInTransaction(this, form);
		return mapping.findForward("continue");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ListForm listForm = (ListForm)form;
		if ("true".equals(listForm.getParam("paragraphNegotiated"))) {
			List<ObservationVO> result = ObservationDAO.selectNotDeletedPrivateObservationsForParagraph(Site.getDELEGATE_SITE().getDataId());
			listForm.setList(result);
			return null;
		} else {
			Version version = VersionDAO.getVersionUnderWork();
			List<ObservationVO> result = ObservationDAO.selectNotDeletedPrivateObservationsForVersion(version.getId());
			listForm.setList(result);
			return null;
		}
	}
}
