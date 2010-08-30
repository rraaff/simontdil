package com.tdil.simon.actions.validations;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.response.ValidationError;

public class LoginValidation {

	public static void validate(String name, String password, ValidationError validation) {
		if (StringUtils.isEmptyOrWhitespaceOnly(name)) {
			if (StringUtils.isEmptyOrWhitespaceOnly(password)) {
				validation.setGeneralError("Complete el Usuario y la Clave. Inténtelo nuevamente.");
			} else {
				validation.setGeneralError("Complete el Usuario. Inténtelo nuevamente.");
			}
		} else {
			if (StringUtils.isEmptyOrWhitespaceOnly(password)) {
				validation.setGeneralError("Complete la clave. Inténtelo nuevamente.");
			}
		}
	}

}
