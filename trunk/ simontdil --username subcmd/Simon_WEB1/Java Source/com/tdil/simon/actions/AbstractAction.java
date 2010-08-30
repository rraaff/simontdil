package com.tdil.simon.actions;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.Error;
import com.tdil.simon.actions.response.ResponseType;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.utils.LoggerProvider;

public abstract class AbstractAction implements Cloneable {
	
	private static final Logger Log = LoggerProvider.getLogger(AbstractAction.class);
	
	private Object responseData;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public abstract void takeValuesFrom(HttpServletRequest req);
	
	public void takeValuesFrom(List<FileItem> fileItems) {
		// Subclasses should redefine
	}
	
	public final ActionResponse execute(HttpServletRequest req) {
		ValidationError validation = new ValidationError();
		this.validate(req, validation);
		if (!validation.hasError()) {
			try {
				return this.basicExecute(req);
			} catch (SQLException e) {
				Log.error(e.getMessage(), e);
				return new ActionResponse(ResponseType.ERROR, new Error(e.getMessage()));
			} catch (ValidationException e) {
				Log.error(e.getMessage(), e);
				return new ActionResponse(ResponseType.VALIDATION_ERROR, e.getError());
			}
		} else {
			return new ActionResponse(ResponseType.VALIDATION_ERROR, validation);
		}
	}
	
	private final void validate(HttpServletRequest req, ValidationError validation) {
		this.getUserTypeValidation().validate(req, validation);
		if (!validation.hasError()) {
			this.basicValidate(req, validation);
		}
	}

	protected abstract UserTypeValidation getUserTypeValidation();
	
	protected abstract ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException;
	
	protected abstract void basicValidate(HttpServletRequest req, ValidationError validation);

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
}
