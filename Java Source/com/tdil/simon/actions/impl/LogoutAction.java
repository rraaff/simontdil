package com.tdil.simon.actions.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;

public class LogoutAction extends AbstractAction {

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ANONYMOUS;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
	}

	@Override
	protected ActionResponse basicExecute(HttpServletRequest req) {
		invalidateSessionIfExists(req);
		return ActionResponse.newOKResponse();
	}


	private void invalidateSessionIfExists(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			try {
				session.invalidate();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
	}

}
