package com.tdil.simon.actions.impl.admin;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.FieldValidation;

public abstract class SystemUserAction extends UserAction {

	protected String administrator;
	protected boolean administratorBoolean;
	protected String moderator;
	protected boolean moderatorBoolean;
	protected String designer;
	protected boolean designerBoolean;

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		super.takeValuesFrom(req);
		this.administrator = req.getParameter("administrator");
		this.moderator = req.getParameter("moderator");
		this.designer = req.getParameter("designer");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		super.basicValidate(req, validation);
		this.administratorBoolean = FieldValidation.validateBoolean(this.administrator, validation);
		this.moderatorBoolean = FieldValidation.validateBoolean(this.moderator, validation);
		this.designerBoolean = FieldValidation.validateBoolean(this.designer, validation);
		if (!this.administratorBoolean && !this.moderatorBoolean && !this.designerBoolean) {
			validation.setGeneralError("No ha seleccionado el tipo de usuario");
			return;
		}
	}
}
