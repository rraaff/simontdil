package com.tdil.simon.actions;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.response.ValidationException;

public interface TransactionalActionWithValue {

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException;
}
