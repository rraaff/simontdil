package com.tdil.simon.actions.impl.admin;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.SystemUserValidation;

public abstract class DelegateAction extends UserAction {

	protected String countryId;
	protected int countryIdInt;
	protected String canSign;
	protected boolean canSignBoolean;
	protected String typeOne;
	protected boolean typeOneBoolean;
	protected String typeTwo;
	protected boolean typeTwoBoolean;
	protected String job;
	protected String countryDesc;

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		super.basicValidate(req, validation);
		this.countryIdInt = IdValidation.validate(this.countryId, "countryId", validation);
		this.canSignBoolean = FieldValidation.validateBoolean(this.canSign, validation);
		this.typeOneBoolean = FieldValidation.validateBoolean(this.typeOne, validation);
		this.typeTwoBoolean = FieldValidation.validateBoolean(this.typeTwo, validation);
		if (this.canSignBoolean) {
			this.job = SystemUserValidation.validateJob(this.job, "job", validation);
			this.countryDesc = SystemUserValidation.validateCountryDesc(this.countryDesc, "countryDesc", validation);
		} else {
			this.job = "";
			this.countryDesc = "";
		}
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		super.takeValuesFrom(req);
		this.countryId = req.getParameter("countryId");
		this.canSign = req.getParameter("canSign");
		this.job = req.getParameter("job");
		this.countryDesc = req.getParameter("countryDesc");
		this.typeOne = req.getParameter("typeOne");
		this.typeTwo = req.getParameter("typeTwo");
	}

}
