package com.tdil.simon.actions.validations;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.DownloadController;

public class CountryValidation {

	private static Logger getLog() {
		return LoggerProvider.getLogger(CountryValidation.class);
	}
	
	public static String validateName(String text, String fieldName, ValidationError validation) {
		return FieldValidation.validateText(text, fieldName, 100, validation);
	}
	
	public static byte[] validateFlag(FileItem fileItem, String fieldName, boolean add, ValidationError validation) {
		if (fileItem == null && add) {
			validation.setFieldError(fieldName, ValidationErrors.CANNOT_BE_EMPTY);
			return null;
		}
		if (!fileItem.getName().toUpperCase().endsWith("PNG")) {
			validation.setFieldError(fieldName, ValidationErrors.FLAG_MUST_BE_PNG);
			return null;
		}
		InputStream io = null;
		try {
			io = fileItem.getInputStream();
			return IOUtils.toByteArray(io);
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			validation.setGeneralError(e.getMessage());
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (IOException e) {
					getLog().error(e.getMessage(), e);
					validation.setGeneralError(e.getMessage());
				}
			}
			fileItem.delete();
		}
		return null;
	}
	
	public static void validateFlag(FormFile fileItem, String fieldName, boolean add, ValidationError validation) {
		boolean isEmpty = fileItem.getFileSize() == 0;
		if (isEmpty && add) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.CANNOT_BE_EMPTY);
			return;
		}
		if (isEmpty) {
			return;
		}
		String fileName = fileItem.getFileName();
		if (!fileName.toUpperCase().endsWith(".PNG")) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.FLAG_MUST_BE_PNG);
			return;
		}
		return;
	}
}
