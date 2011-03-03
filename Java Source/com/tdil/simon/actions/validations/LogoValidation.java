package com.tdil.simon.actions.validations;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;


import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.utils.LoggerProvider;

public class LogoValidation {

	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LogoValidation.class);
	}
	

	public static byte[] validateLogo(FormFile fileItem, String fieldName, boolean add, ValidationError validation) {
		boolean isEmpty = fileItem.getFileSize() == 0;
		if (isEmpty && add) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.CANNOT_BE_EMPTY);
			return null;
		}
		if (isEmpty) {
			return null;
		}
		String logoName = fileItem.getFileName();
		if (!logoName.toUpperCase().endsWith(".PNG")) {
			validation.setFieldError(fieldName, fieldName + "." + ValidationErrors.INVALID_LOGO_TYPE);
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
			// TODO fileItem.delete();
		}
		return null;
	}
}
