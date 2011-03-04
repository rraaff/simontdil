package com.tdil.simon.data.ibatis;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class DatabaseCreator {

	public static void main(String[] args) throws IOException {
		java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		System.out.println("Ingrese el server de la base:");
		String server = stdin.readLine();

		System.out.println("Ingrese el port del server de la base:");
		String port = stdin.readLine();

		System.out.println("Ingrese el usuario root de la base:");
		String rootUser = stdin.readLine();

		System.out.println("Ingrese el password del usuario root de la base:");
		String rootPassword = stdin.readLine();

		System.out.println("Ingrese el nombre de la base a crear:");
		String base = stdin.readLine();

		System.out.println("Ingrese el usuario de la base a crear:");
		String user = stdin.readLine();

		System.out.println("Ingrese el password del usuario a crear:");
		String password = stdin.readLine();
		
		System.out.println("Ingrese el nombre del pais host:");
		String countryHost = stdin.readLine();
		
		System.out.println("Ingrese el password del usuario Admin:");
		String adminPassword = stdin.readLine();
		
		System.out.println("Ingrese el subpath para el directorio temporal(debe ser unico para cada instalacion de simon):");
		String subpath = stdin.readLine();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			{
				Connection conexion = null;
				try {
					conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "", rootUser,
						rootPassword);
				} catch (Exception e) {
					System.out.println("No se ha podido conectar como root, revise los parametros del server de base de datos");
					e.printStackTrace(System.out);
					System.exit(-1);
				}
				String createDatabaseStatements = IOUtils.toString(DatabaseCreator.class
						.getResourceAsStream("ddl_create_datase.sql"));
				createDatabaseStatements = StringUtils.replace(createDatabaseStatements, "${DATABASE}", base);
				createDatabaseStatements = StringUtils.replace(createDatabaseStatements, "${USER}", user);
				createDatabaseStatements = StringUtils.replace(createDatabaseStatements, "${PASSWORD}", password);
				String statements[] = StringUtils.split(createDatabaseStatements, "/");
				Statement st = conexion.createStatement();
				for (String statement : statements) {
					st.executeUpdate(statement);
				}
				st.close();
				conexion.close();
			}
			{
				Connection conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + base,
						user, password);
				String createSimon = IOUtils.toString(DatabaseCreator.class
						.getResourceAsStream("ddl_create_tables.sql"));
				String statements[] = StringUtils.split(createSimon, "/");
				Statement st = conexion.createStatement();
				for (String statement : statements) {
					st.executeUpdate(statement);
				}
				st.close();
				conexion.close();
			}
			{
				GenerateCleanDatabase.createDataInDatabase("jdbc:mysql://" + server + ":" + port + "/" + base, user, password, countryHost, adminPassword, subpath);
				writeInstalationNotes(user, password, "jdbc:mysql://" + server + ":" + port + "/" + base, adminPassword);
			}
		} catch (Exception e) {
			System.out.println("La instalacion ha fallado:");
			e.printStackTrace(System.out);
		}
	}
	
	public static void writeInstalationNotes(String dbUser, String dbPassword, String connectionURL, String adminPassword) throws FileNotFoundException, IOException {
		StringBuffer instalationNotes = new StringBuffer();
		instalationNotes.append("Se ha creado la base de datos exitosamente.\n");
		instalationNotes.append("Debera modificar el server.xml del tomcat definiendo un contexto como el que sigue:\n\n");
		instalationNotes.append("<Context path=\"/Simon\" reloadable=\"true\" docBase=\"Simon_WEB/Web Content/\" workDir=\"Simon_WEB/work\" >\n");
		instalationNotes.append("   <Logger className=\"org.apache.catalina.logger.SystemOutLogger\" verbosity=\"4\" timestamp=\"true\"/>\n");
		instalationNotes.append("   <Resource name=\"jdbc/SimonDB\" auth=\"Container\"\n");
		instalationNotes.append("   	type=\"javax.sql.DataSource\" removeAbandoned=\"true\"\n");
		instalationNotes.append("   	removeAbandonedTimeout=\"30\" maxActive=\"100\"\n");
		instalationNotes.append("   	maxIdle=\"30\" maxWait=\"10000\" username=\""+dbUser+"\"\n");
		instalationNotes.append("  	password=\""+dbPassword+"\"\n");
		instalationNotes.append("   	driverClassName=\"com.mysql.jdbc.Driver\"\n");
		instalationNotes.append("   	url=\""+connectionURL+"\" />\n");
		instalationNotes.append("</Context>\n\n");
		instalationNotes.append("Una vez levantado el sistema debera:\n");
		instalationNotes.append("\t1)Cargarle la imagen al pais host\n");
		instalationNotes.append("\t2)Cambiar la definicion de la propiedad URL del Servidor\n\n");
		instalationNotes.append("Los datos para el login son: usuario Admin y password " + adminPassword);
		
		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		IOUtils.write(instalationNotes.toString(), new FileOutputStream(System.getProperty("user.home") + "/simon_" + dateFormat.format(calendar.getTime()) + ".txt"));
		System.out.println("Instalacion exitosa, se ha generado el archivo con las notas en " + System.getProperty("user.home") + "/simon_" + dateFormat.format(calendar.getTime()) + ".txt");
	}
}
