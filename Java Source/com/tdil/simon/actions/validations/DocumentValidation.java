package com.tdil.simon.actions.validations;

import com.tdil.simon.actions.response.ValidationError;

public class DocumentValidation {

	public static String validateTitle(String title, ValidationError validation) {
		return FieldValidation.validateText(title, "title", 255, validation);
	}
	
	public static String validateIntroduction(String introduction, ValidationError validation) {
		return FieldValidation.validateText(introduction, "introduction", 4000000, validation);
	}

	public static String validateConsolidation(String consolidateText, ValidationError validation) {
		return FieldValidation.validateText(consolidateText, "consolidateText", 4000000, validation);
		
	}
}
