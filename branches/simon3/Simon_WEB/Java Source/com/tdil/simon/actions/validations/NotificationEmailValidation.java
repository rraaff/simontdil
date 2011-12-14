package com.tdil.simon.actions.validations;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;

public class NotificationEmailValidation {

	private static Logger getLog() {
		return LoggerProvider.getLogger(NotificationEmailValidation.class);
	}
	
	public static String validateFrom(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 100, validation);
	}
	
	public static String validateTo(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 100, validation);
	}
	
	public static String validateSubject(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 100, validation);
	}
	
	public static String validateText(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 16000, validation);
	}
	
}
