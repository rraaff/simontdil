package com.tdil.simon.actions.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.mysql.jdbc.StringUtils;

public class ValidationError {

	private String generalError = "";
	private Map<String, String> fieldErrors = new HashMap<String, String>();

	public ValidationError() {
		super();
	}
	
	public ValidationError(String error) {
		super();
		setGeneralError(error);
	}
	
	public boolean hasError() {
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getGeneralError())) {
			return true;
		}
		return fieldErrors.size() > 0;
	}
	
	public void setFieldError(String field, String error) {
		getFieldErrors().put(field, error);
	}
	
	public String getGeneralError() {
		return generalError;
	}

	public void setGeneralError(String generalError) {
		this.generalError = generalError;
	}

	public Map<String, String> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(Map<String, String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	
	public ActionMessages asMessages() {
		ActionMessages messages = new ActionMessages();
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getGeneralError())) {
			messages.add("general", new ActionMessage(this.getGeneralError()));
		}
		for (Map.Entry<String, String> error : this.getFieldErrors().entrySet()) {
			messages.add(error.getKey(), new ActionMessage(error.getValue()));
		}
		return messages;
	}
	
	public ActionErrors asActionsErrors() {
		if (!this.hasError()) {
			return null;
		}
		ActionErrors errors = new ActionErrors();
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getGeneralError())) {
			errors.add("general", new ActionMessage(this.getGeneralError()));
		}
		for (Map.Entry<String, String> error : this.getFieldErrors().entrySet()) {
			errors.add(error.getKey(), new ActionMessage(error.getValue()));
		}
		return errors;
	}

}
