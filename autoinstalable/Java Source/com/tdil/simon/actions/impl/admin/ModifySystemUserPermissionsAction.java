package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public class ModifySystemUserPermissionsAction extends ModifySystemUserAction {

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.oid = IdValidation.validate(this.id, "id", validation);
		this.administratorBoolean = FieldValidation.validateBoolean(this.administrator, validation);
		this.moderatorBoolean = FieldValidation.validateBoolean(this.moderator, validation);
		this.designerBoolean = FieldValidation.validateBoolean(this.designer, validation);
		if (!this.administratorBoolean && !this.moderatorBoolean && !this.designerBoolean) {
			validation.setGeneralError("No ha seleccionado el tipo de usuario");
			return;
		}
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}

	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser toModify = SystemUserDAO.getUser(this.oid);
		if (toModify == null) {
			throw new ValidationException(new ValidationError("No existe un usuario con el id indicado"));
		}
		if (toModify.isDelegate()) {
			throw new ValidationException(new ValidationError("No existe un usuario con el id indicado"));
		}
		toModify.setAdministrator(this.administratorBoolean);
		toModify.setModerator(this.moderatorBoolean);
		toModify.setDesigner(this.designerBoolean);
		toModify.setDeleted(false);
		SystemUserDAO.updateUser(toModify);
	}
}
