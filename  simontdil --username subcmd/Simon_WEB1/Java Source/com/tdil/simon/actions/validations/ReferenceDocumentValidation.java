package com.tdil.simon.actions.validations;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;

public class ReferenceDocumentValidation {

	private static final Logger Log = LoggerProvider.getLogger(ReferenceDocumentValidation.class);
	
	public static String validateTitle(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 100, validation);
	}
	
	public static String[] validateDocument(FileItem fileItem, String fieldName, boolean add, ValidationError validation) {
		if (fileItem == null && add) {
			validation.setFieldError(fieldName, ValidationErrors.CANNOT_BE_EMPTY);
			return new String[2];
		}
		if (fileItem == null) {
			return null;
		}
		String documentName = fileItem.getName();
		if (!documentName.toUpperCase().endsWith(".DOC") &&
				!documentName.toUpperCase().endsWith(".PDF") &&
				!documentName.toUpperCase().endsWith(".PPT")) {
			validation.setFieldError(fieldName, ValidationErrors.INVALID_DOC_TYPE);
			return new String[2];
		}
		String extension = documentName.substring(documentName.length() - 3).toUpperCase();
		return new String [] {documentName, extension};
	}
}
