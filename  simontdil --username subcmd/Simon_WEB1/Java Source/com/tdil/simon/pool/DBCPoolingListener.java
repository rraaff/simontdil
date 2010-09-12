package com.tdil.simon.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.utils.LoggerProvider;

public class DBCPoolingListener implements ServletContextListener {
	
	
	public void contextInitialized(ServletContextEvent sce) {

		try {
			// Obtain our environment naming context
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/SimonDB");
			DatasourceManager.setDatasource(ds);
			IBatisManager.init("SqlMapConfig-JNDI.xml");
		} catch (NamingException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DBCPoolingListener.class);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
