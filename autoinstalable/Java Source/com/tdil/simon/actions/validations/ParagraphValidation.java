package com.tdil.simon.actions.validations;

import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.actions.response.ValidationError;

public class ParagraphValidation {

	public static void validateParagraphs(TreeMap<Integer, String> paragraphs, ValidationError validation) {
		if (paragraphs.isEmpty()) {
			validation.setGeneralError(ValidationErrors.NO_PARAGRAPH);
			return;
		}
		for (Map.Entry<Integer, String> entry : paragraphs.entrySet()) {
			FieldValidation.validateText(entry.getValue(), "paragraph" + entry.getKey(), 4000000, validation);
		}
	}
	
	public static int validateParagraphNumber(String number, ValidationError validation) {
		if (StringUtils.isEmpty(number)) {
			validation.setFieldError("paragraph", ValidationErrors.CANNOT_BE_EMPTY);
		} else {
			String idToValidate = number.trim();
			try {
				return Integer.parseInt(idToValidate);
			} catch (NumberFormatException e) {
				validation.setFieldError("paragraph", ValidationErrors.INVALID_NUMBER);
			}
		}
		return 0;
	}
	
	public static String validateParagraphText(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 4000000, validation);
	}
	
	public static String validateParagraphDetail(String text, String fieldName, ValidationError validation) {
		if (!StringUtils.isEmpty(text)) {
			if (text.indexOf(".") != -1) {
				validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.CAN_NOT_CONTAIN_DOT);
			}
		}
		return FieldValidation.validateTextForLength(text, fieldName, 100, validation);
	}
	
	public static String validateParagraphTextForLength(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateTextForLength(text, fieldName, 4000000, validation);
	}
	
	public static String validateObservation(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 4000000, validation);
	}
}
