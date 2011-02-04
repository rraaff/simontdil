package com.tdil.simon.actions.response;

import org.apache.struts.action.ActionMessages;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -2231195954556054706L;

	private ValidationError error;
	
	public ValidationException(ValidationError error) {
		super();
		this.error = error;
	}

	public ValidationError getError() {
		return error;
	}

	public void setError(ValidationError error) {
		this.error = error;
	}
	
	public ActionMessages asMessages() {
		return error.asMessages();
	}
	
}
