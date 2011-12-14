package com.tdil.simon.actions.validations;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;

public class DocumentTypeValidation {

	private static Logger getLog() {
		return LoggerProvider.getLogger(DocumentTypeValidation.class);
	}
	
	public static String validateName(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 255, validation);
	}
	
}
