package com.tdil.simon.data.ibatis;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class DatabaseCreator {

	private static String installationPath;
	private static String server;
	private static String port;
	private static String rootUser = "root";
	private static String rootPassword;
	private static String base;
	private static String user;
	private static String password;
	private static String countryHost;
	private static String adminPassword;
	private static String subpath;

	public static void main(String[] args) throws IOException {
		parseParams(args);

		java.io.BufferedReader stdin = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		if (installationPath == null) {
			System.out.println("Ingrese el directorio base de la instalacion de simon (ej. c:)");
			installationPath = stdin.readLine();
		}
		
		if (server == null) {
			System.out.println("Ingrese el server de la base:");
			server = stdin.readLine();
		}

		if (port == null) {
			System.out.println("Ingrese el port del server de la base:");
			port = stdin.readLine();
		}

		if (rootPassword == null) {
			System.out.println("Ingrese el password del usuario root de la base:");
			rootPassword = stdin.readLine();
		}

		if (base == null) {
			System.out.println("Ingrese el nombre de la base a crear:");
			base = stdin.readLine();
		}

		if (user == null) {
			System.out.println("Ingrese el usuario de la base a crear:");
			user = stdin.readLine();
		}

		if (password == null) {
			System.out.println("Ingrese el password del usuario a crear:");
			password = stdin.readLine();
		}

		if (countryHost == null) {
			System.out.println("Ingrese el nombre del pais host:");
			countryHost = stdin.readLine();
		}

		if (adminPassword == null) {
			System.out.println("Ingrese el password del usuario Admin:");
			adminPassword = stdin.readLine();
		}

		if (subpath == null) {
			System.out
					.println("Ingrese el subpath para el directorio temporal(debe ser unico para cada instalacion de simon):");
			subpath = stdin.readLine();
		}
		
		
		try {
			// create database for simon and user
			Class.forName("com.mysql.jdbc.Driver");
			{
				Connection conexion = null;
				try {
					conexion = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "", rootUser,
							rootPassword);
				} catch (Exception e) {
					System.out
							.println("No se ha podido conectar como root, revise los parametros del server de base de datos");
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
			// Generate database structure
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
			// Generate database data
			{
				GenerateCleanDatabase.createDataInDatabase("jdbc:mysql://" + server + ":" + port + "/" + base, user,
						password, countryHost, adminPassword, subpath);
			}
			// modify setclasspah del tomcat
			
			// modify setclasspah del tomcat
			{
				writeInstalationNotes(installationPath, user, password, "jdbc:mysql://" + server + ":" + port + "/" + base, adminPassword);
			}
		} catch (Exception e) {
			System.out.println("ERROR: La instalacion ha fallado:");
			e.printStackTrace(System.out);
		}
	}

	private static void parseParams(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("installationPath")) {
				installationPath = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("server")) {
				server = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("port")) {
				port = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("rootPassword")) {
				rootPassword = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("base")) {
				base = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("user")) {
				user = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("password")) {
				password = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("countryHost")) {
				countryHost = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("adminPassword")) {
				adminPassword = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
			if (args[i].startsWith("subpath")) {
				subpath = args[i].substring(args[i].indexOf("=") + 1, args[i].length());
			}
		}
	}

	public static void writeInstalationNotes(String installationPath, String dbUser, String dbPassword, String connectionURL,
			String adminPassword) throws FileNotFoundException, IOException {
		StringBuffer context = new StringBuffer();
		context.append("\n<Context path=\"/Simon\" reloadable=\"true\" docBase=\"Simon_WEB/Web Content/\" workDir=\"Simon_WEB/work\" >\n");
		context.append("   <Logger className=\"org.apache.catalina.logger.SystemOutLogger\" verbosity=\"4\" timestamp=\"true\"/>\n");
		context.append("   <Resource name=\"jdbc/SimonDB\" auth=\"Container\"\n");
		context.append("   	type=\"javax.sql.DataSource\" removeAbandoned=\"true\"\n");
		context.append("   	removeAbandonedTimeout=\"30\" maxActive=\"100\"\n");
		context.append("   	maxIdle=\"30\" maxWait=\"10000\" username=\"" + dbUser + "\"\n");
		context.append("  	password=\"" + dbPassword + "\"\n");
		context.append("   	driverClassName=\"com.mysql.jdbc.Driver\"\n");
		context.append("   	url=\"" + connectionURL + "\" />\n");
		context.append("</Context>\n\n");
		File file = new File(installationPath+"/simon/apache-tomcat-6.0.32/bin/setclasspath.bat");
		if (!file.exists()) {
			System.out.println("ERROR: El path de instalacion es invalido. NO se pudo encontrar el archivo: "+installationPath+"/simon/apache-tomcat-6.0.32/bin/setclasspath.bat\n");
			System.out.println("La instalacion ha fallado\n");
			System.exit(-1);
		} else {
			InputStream input = new FileInputStream(installationPath+"/simon/apache-tomcat-6.0.32/bin/setclasspath.bat");
			String contents = IOUtils.toString(input);
			try {
				input.close();
			} catch (Exception e) {}
			if (contents.indexOf("set JAVA_HOME=") == -1) {
				contents = "set JAVA_HOME=" + installationPath + "/simon/jdk1.6.21" + "\n" + contents;
				OutputStream output = new FileOutputStream(installationPath+"/simon/apache-tomcat-6.0.32/bin/setclasspath.bat");
				IOUtils.write(contents, output);
				try {
					output.close();
				} catch (Exception e) {}
			}
			
			input = new FileInputStream(installationPath+"/simon/apache-tomcat-6.0.32/conf/server.xml");
			contents = IOUtils.toString(input);
			try {
				input.close();
			} catch (Exception e) {}
			int insert = contents.indexOf("</Host>");
			contents = contents.substring(0, insert) + context.toString() + contents.substring(insert);
			OutputStream output = new FileOutputStream(installationPath+"/simon/apache-tomcat-6.0.32/conf/server.xml");
			IOUtils.write(contents, output);
			try {
				output.close();
			} catch (Exception e) {}
		}

		StringBuffer instalationNotes = new StringBuffer();
		instalationNotes.append("Se ha creado la base de datos exitosamente.\n");
		instalationNotes.append("Debera levantar el tomcat ejecutando el siguiente comando:\n");
		instalationNotes.append("\t1)windows: "+installationPath+"/simon/apache-tomcat-6.0.32/bin/startup.bat\n");
		instalationNotes.append("\t2)linux: sudo "+installationPath+"/simon/apache-tomcat-6.0.32/bin/startup.sh\n");
		instalationNotes.append("Podra accederlo mediante en la URL htt://SERVER_NAME:8080/Simon\n");
		instalationNotes.append("Una vez levantado el sistema debera:\n");
		instalationNotes.append("\t1)Cargarle la imagen al pais host\n");
		instalationNotes.append("\t2)Cambiar la definicion de la propiedad URL del Servidor\n\n");
		instalationNotes.append("Los datos para el login son: usuario Admin y password " + adminPassword);

		Calendar calendar = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		IOUtils.write(instalationNotes.toString(), new FileOutputStream(installationPath + "/simon/"
				+ dateFormat.format(calendar.getTime()) + ".txt"));
		System.out.println("Instalacion exitosa, se ha generado el archivo con las notas en "
				+ installationPath + "/simon/" + dateFormat.format(calendar.getTime()) + ".txt");
	}
}
