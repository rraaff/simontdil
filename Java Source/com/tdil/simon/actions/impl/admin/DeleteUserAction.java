package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public abstract class DeleteUserAction extends AbstractAction implements TransactionalAction {

	private String id;
	private int oid;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.id = req.getParameter("id");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		oid = IdValidation.validate(this.id, "id", validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser toDelete = SystemUserDAO.getUser(oid);
		if (toDelete == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
		if (toDelete.getUsername().equals("Admin")) {
			throw new ValidationException(new ValidationError(ValidationErrors.ADMIN_CANNOT_BE_DELETED));
		}
		validateDelete(toDelete);
		SystemUserDAO.logicallyDeleteUser(toDelete);
	}

	protected abstract void validateDelete(SystemUser toDelete) throws ValidationException;
}
