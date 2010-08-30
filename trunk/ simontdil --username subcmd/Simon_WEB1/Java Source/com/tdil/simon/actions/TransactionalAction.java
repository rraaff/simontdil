package com.tdil.simon.actions;

import java.sql.SQLException;

import com.tdil.simon.actions.response.ValidationException;

public interface TransactionalAction {

	public void executeInTransaction() throws SQLException, ValidationException;
}
