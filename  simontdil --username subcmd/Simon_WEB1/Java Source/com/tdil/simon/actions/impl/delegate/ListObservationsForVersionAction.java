package com.tdil.simon.actions.impl.delegate;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.database.TransactionProvider;

public class ListObservationsForVersionAction extends AbstractAction implements TransactionalAction {

	private String id;
	private int oid;
	
	private SystemUser user;
	
	@Override
	protected ActionResponse basicExecute(HttpServletRequest req)
			throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse(this.getResponseData());
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		List observations = ObservationDAO.selectNotDeletedObservationsForVersion(this.oid);
		if (this.user.isDelegate()) {
			for (Object o : observations) {
				ObservationVO vo = (ObservationVO)o;
				DelegateAuditDAO.registerAction(this.user.getId(), this.user.getCountryId(), vo.getId(), DelegateAudit.VIEW_OBSERVATION);
			}
		}
		this.setResponseData(observations);
	}

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
		oid = IdValidation.validate(this.id, "id", validation);
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.DELEGATE;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.id = req.getParameter("id");
		this.user = (SystemUser)req.getSession().getAttribute("user");
	}

}
