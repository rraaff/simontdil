package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public class ModifySystemUserAction extends SystemUserAction implements TransactionalAction {

	protected String id;
	protected int oid;

	@Override
	protected boolean isAdd() {
		return false;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		super.takeValuesFrom(req);
		this.id = req.getParameter("id");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		super.basicValidate(req, validation);
		this.oid = IdValidation.validate(this.id, "id", validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser toModify = SystemUserDAO.getUser(this.oid);
		if (toModify == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
		if (toModify.isDelegate()) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
		toModify.setName(this.name);
		toModify.setEmail(this.email);
		toModify.setAdministrator(this.administratorBoolean);
		toModify.setModerator(this.moderatorBoolean);
		toModify.setDesigner(this.designerBoolean);
		toModify.setDelegate(false);
		toModify.setCanSign(false);
		toModify.setJob("");
		toModify.setCountryDesc("");
		toModify.setDeleted(false);
		SystemUserDAO.updateUser(toModify);
	}
}
