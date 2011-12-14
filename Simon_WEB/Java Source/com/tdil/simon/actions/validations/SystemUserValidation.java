package com.tdil.simon.actions.validations;

import com.tdil.simon.actions.response.ValidationError;

public class SystemUserValidation {
	
	public static String validateUsername(String text, String field, ValidationError validation) {
		return FieldValidation.validateText(text, field, 20, validation);
	}

	public static String validatePassword(String text, String field, ValidationError validation) {
		return FieldValidation.validateText(text, field, 20, validation);
	}

	public static String validateName(String text, String field, ValidationError validation) {
		return FieldValidation.validateText(text, field, 255, validation);
	}

	public static String validateEmail(String email, String field, ValidationError validation) {
		return FieldValidation.validateEmail(email, field, validation);
	}
	
	public static String validateJob(String text, String field, ValidationError validation) {
		return FieldValidation.validateText(text, field, 100, validation);
	}

	public static String validateCountryDesc(String text, String field, ValidationError validation) {
		return FieldValidation.validateText(text, field, 150, validation);
	}
}
