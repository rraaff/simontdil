package com.tdil.simon.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public final class DatasourceManager {

	private static DataSource datasource;

	protected static DataSource getDatasource() {
		return datasource;
	}

	protected static void setDatasource(DataSource ds) {
		DatasourceManager.datasource = ds;
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		return datasource.getConnection(); // Allocate and use a connection
	}

}
