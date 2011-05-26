package com.tdil.simon.actions.validations;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.actions.response.ValidationError;

public class LoginValidation {

	public static void validate(String name, String password, ValidationError validation) {
		if (StringUtils.isEmpty(name)) {
			if (StringUtils.isEmpty(password)) {
				validation.setGeneralError("Complete el Usuario y la Clave. Inténtelo nuevamente.");
			} else {
				validation.setGeneralError("Complete el Usuario. Inténtelo nuevamente.");
			}
		} else {
			if (StringUtils.isEmpty(password)) {
				validation.setGeneralError("Complete la clave. Inténtelo nuevamente.");
			}
		}
	}

}
