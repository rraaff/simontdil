package com.tdil.simon.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.model.SystemUser;

public enum UserTypeValidation {

	ADMINISTRATOR {
		@Override
		public void validate(HttpServletRequest req, ValidationError validation) {
			if (validateLogged(req, validation)) {
				SystemUser logged = (SystemUser)req.getSession().getAttribute("user");
				if (!logged.isAdministrator()) {
					validation.setGeneralError("Acci�n inv�lida");
				}
			}
		}
	},
	MODERATOR {
		@Override
		public void validate(HttpServletRequest req, ValidationError validation) {
			if (validateLogged(req, validation)) {
				SystemUser logged = (SystemUser)req.getSession().getAttribute("user");
				if (!logged.isModerator()) {
					validation.setGeneralError("Acci�n inv�lida");
				}
			}
		}
	},
	DELEGATE {
		@Override
		public void validate(HttpServletRequest req, ValidationError validation) {
			if (validateLogged(req, validation)) {
				SystemUser logged = (SystemUser)req.getSession().getAttribute("user");
				if (!logged.isDelegate() && !logged.isModerator()) {
					validation.setGeneralError("Acci�n inv�lida");
				}
			}
		}
	},
	DESIGNER {
		@Override
		public void validate(HttpServletRequest req, ValidationError validation) {
			if (validateLogged(req, validation)) {
				SystemUser logged = (SystemUser)req.getSession().getAttribute("user");
				if (!logged.isDesigner()) {
					validation.setGeneralError("Acci�n inv�lida");
				}
			}
		}
	},
	ANONYMOUS {
		@Override
		public void validate(HttpServletRequest req, ValidationError validation) {
			// NOTHING TO DO
		}
	};
	
	public abstract void validate(HttpServletRequest req, ValidationError validation);

	public static boolean validateLogged(HttpServletRequest req,
			ValidationError validation) {
		HttpSession session = req.getSession(false);
		if (session == null) {
			validation.setGeneralError("No est� logeado");
			return false;
		}
		SystemUser user = (SystemUser)session.getAttribute("user");
		if (user == null) {
			validation.setGeneralError("No est� logeado");
			return false;
		}
		return true;
	}
}
