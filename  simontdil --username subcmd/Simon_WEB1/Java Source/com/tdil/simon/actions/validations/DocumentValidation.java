package com.tdil.simon.actions.validations;

import com.tdil.simon.actions.response.ValidationError;

public class DocumentValidation {

	public static String validateTitle(String title, ValidationError validation) {
		return FieldValidation.validateText(title, "title", 100, validation);
	}
	
	public static String validateIntroduction(String introduction, ValidationError validation) {
		return FieldValidation.validateText(introduction, "introduction", 5000, validation);
	}
}
