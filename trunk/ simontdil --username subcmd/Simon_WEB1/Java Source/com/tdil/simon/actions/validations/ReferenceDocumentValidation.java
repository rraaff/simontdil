package com.tdil.simon.actions.validations;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;
import org.xhtmlrenderer.simple.extend.form.FormField;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;

public class ReferenceDocumentValidation {

	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ReferenceDocumentValidation.class);
	}
	
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
	
	public static void validateDocument(FormFile fileItem, String fieldName, boolean add, ValidationError validation) {
		boolean isEmpty = fileItem.getFileSize() == 0;
		if (isEmpty && add) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.CANNOT_BE_EMPTY);
			return;
		}
		if (isEmpty) {
			return;
		}
		String documentName = fileItem.getFileName();
		if (!documentName.toUpperCase().endsWith(".DOC") &&
				!documentName.toUpperCase().endsWith(".PDF") &&
				!documentName.toUpperCase().endsWith(".PPT")) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.INVALID_DOC_TYPE);
			return;
		}
		return;
	}
}
