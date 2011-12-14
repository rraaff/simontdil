package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.CountryDAO;

public class ListCountryAction extends AbstractAction {

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		List result = CountryDAO.selectAllCountriesVO();
		return ActionResponse.newOKResponse(result);
	}
	
}
