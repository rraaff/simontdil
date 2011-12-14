package com.tdil.simon.actions.impl.admin;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.SystemUserValidation;

public abstract class UserAction extends AbstractAction {

	protected String username;
	protected String name;
	protected String email;
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		if (isAdd()) {
			this.username = SystemUserValidation.validateUsername(this.username, "username", validation);
		}
		this.name = SystemUserValidation.validateName(this.name, "name", validation);
		this.email = SystemUserValidation.validateEmail(this.email, "email", validation);
	}

	protected abstract boolean isAdd();

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		if (isAdd()) {
			this.username = req.getParameter("username");
		}
		this.name = req.getParameter("name");
		this.email = req.getParameter("email");
	}

}
