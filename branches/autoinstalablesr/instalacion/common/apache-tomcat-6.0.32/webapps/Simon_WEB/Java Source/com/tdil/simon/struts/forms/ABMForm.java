package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;

public interface ABMForm {

	public abstract void reset() throws SQLException;

	public abstract void init() throws SQLException;

	public ValidationError validate();
	
	public abstract void save() throws SQLException, ValidationException;

}