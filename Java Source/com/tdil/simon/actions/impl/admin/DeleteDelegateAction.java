package com.tdil.simon.actions.impl.admin;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.model.SystemUser;

public class DeleteDelegateAction extends DeleteUserAction {

	@Override
	protected void validateDelete(SystemUser toDelete) throws ValidationException {
		if (!toDelete.isDelegate()) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
	}

}
