package com.tdil.simon.actions.impl.moderator;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.VersionDAO;

public class ListVersionAction extends AbstractAction {

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.MODERATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		List result = VersionDAO.selectVersionsVOForList();
		return ActionResponse.newOKResponse(result);
	}
	
}
