package com.tdil.simon.actions.validations;

import java.util.Map;
import java.util.TreeMap;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.response.ValidationError;

public class ParagraphValidation {

	public static void validateParagraphs(TreeMap<Integer, String> paragraphs, ValidationError validation) {
		if (paragraphs.isEmpty()) {
			validation.setGeneralError(ValidationErrors.NO_PARAGRAPH);
			return;
		}
		for (Map.Entry<Integer, String> entry : paragraphs.entrySet()) {
			FieldValidation.validateText(entry.getValue(), "paragraph" + entry.getKey(), 20000, validation);
		}
	}
	
	public static int validateParagraphNumber(String number, ValidationError validation) {
		if (StringUtils.isEmptyOrWhitespaceOnly(number)) {
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
	
	public static String validateObservation(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 20000, validation);
	}
}
