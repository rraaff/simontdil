package com.tdil.simon.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.DatabaseCreator;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.PermissionCache;
import com.tdil.simon.web.SystemConfig;

public class DBCPoolingListener implements ServletContextListener {
	
	private static final String versions[][] = new String[][] {{"2","0"}};
	
	public void contextInitialized(ServletContextEvent sce) {

		try {
			// Obtain our environment naming context
			Context envCtx = (Context) new InitialContext().lookup("java:comp/env");
			// Look up our data source
			DataSource ds = (DataSource) envCtx.lookup("jdbc/SimonDB");
			DatasourceManager.setDatasource(ds);			
			// TODO aca se puede actualizar la base????
			upgradeDb(ds);
			
			IBatisManager.init("SqlMapConfig-JNDI.xml", new Properties());		
			PermissionCache.refresh();
			DelegateSiteCache.refresh();
			SystemConfig.init();
		} catch (NamingException e) {
			getLog().error(e.getMessage(), e);
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private void upgradeDb(DataSource ds) {
		Connection conn = null;
		Statement st = null;
		 try {
			 conn = ds.getConnection();
			 conn.setAutoCommit(false);
			 st = conn.createStatement();
			 String version[] = getVersion(st);
			 for (String upgrade[] : versions) {
				 if (mustBeRun(upgrade, version)) {
					 runUpgrade(upgrade, st, conn);
					 conn.commit();
				 }
			 }
		 } catch (Exception e) {
			System.out.println("Error actualizando la version");
			e.printStackTrace(System.out);
			System.exit(-1);
		 } finally {
			 if (st != null) {
				 try {
					 st.close();
				 } catch (Exception e) {
					// TODO: handle exception
				}
			 }
			 if (conn != null) {
				 try {
					 conn.close();
				 } catch (Exception e) {
				}
			 }
		 }
	}

	private void runUpgrade(String[] upgrade, Statement st, Connection conn) throws IOException, SQLException {
		String createSimon = IOUtils.toString(DatabaseCreator.class
				.getResourceAsStream("upgrade_"+upgrade[0]+ "."+ upgrade[1] + ".sql"));
		String statements[] = StringUtils.split(createSimon, "/");
		int index = 0;
		for (String statement : statements) {
			if (shouldBeRun(st, upgrade[0]+ "."+ upgrade[1] + "." + index)) {
				System.out.println("Running script " + upgrade[0]+ "."+ upgrade[1] + "." + index);
				System.out.println(statement);
				st.executeUpdate(statement);
				st.executeUpdate("INSERT INTO DEPLOYED_SCRIPTS(scriptId) VALUES ('" + upgrade[0]+ "."+ upgrade[1] + "." + index + "')");
				conn.commit();
			} else {
				System.out.println("Skipping script " + upgrade[0]+ "."+ upgrade[1] + "." + index);
			}
			index = index + 1;
		}
	}

	private boolean shouldBeRun(Statement st, String scriptId) throws SQLException {
		if (scriptId.equals("2.0.0")) {
			return true;
		} else {
			ResultSet rs = null;
			try {
				rs = st.executeQuery("select count(*) from DEPLOYED_SCRIPTS where scriptId = '"+scriptId+"'");
				if (rs.next()) {
					int executed = rs.getInt(1);
					return executed == 0;
				}
				return true;
			} finally {
				if (rs != null) {
					rs.close();
				}
			}	
		}
		
	}

	private boolean mustBeRun(String[] upgrade, String[] version) {
		int currentMayor = Integer.parseInt(version[0]);
		int currentMinor = Integer.parseInt(version[1]);
		
		int upgradeMayor = Integer.parseInt(upgrade[0]);
		int upgradeMinor = Integer.parseInt(upgrade[1]);
		
		if (currentMayor < upgradeMayor) {
			return true;
		}
		if (currentMayor > upgradeMayor) {
			return false;
		}
		// Iguales
		if (currentMinor < upgradeMinor) {
			return true;
		}
		return false;
	}

	private String[] getVersion(Statement st) throws SQLException {
		String result[] = new String[]{"1","0"};
		ResultSet rs = null;
		ResultSet rs1 = null;
		try {
			rs = st.executeQuery("select propValue from SYSPROPERTIES where propKey = 'simon.mayorVersion'");
			if (rs.next()) {
				result[0] = rs.getString(1);
			}
			rs1 = st.executeQuery("select propValue from SYSPROPERTIES where propKey = 'simon.minorVersion'");
			if (rs1.next()) {
				result[1] = rs1.getString(1);
			}
			return result;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (rs1 != null) {
				rs1.close();
			}
		}
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(DBCPoolingListener.class);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
