package com.tdil.simon.actions.validations;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.actions.response.ValidationError;

public class IdValidation {

	public static Integer validate(String id, String field, ValidationError validation) {
		return validate(id, true, field, validation);
	}
	
	public static Integer validate(String id, boolean requiered, String field, ValidationError validation) {
		if (StringUtils.isEmpty(id)) {
			if (requiered) {
				validation.setFieldError(field, ValidationErrors.CANNOT_BE_EMPTY);
			} else {
				return null;
			}
		} else {
			String idToValidate = id.trim();
			try {
				return Integer.parseInt(idToValidate);
			} catch (NumberFormatException e) {
				validation.setFieldError(field, ValidationErrors.INVALID_NUMBER);
			}
		}
		return 0;
	}
}
