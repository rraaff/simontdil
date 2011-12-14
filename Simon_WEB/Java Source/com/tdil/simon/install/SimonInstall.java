package com.tdil.simon.install;

import java.io.IOException;
import java.sql.SQLException;

import com.tdil.simon.data.ibatis.GenerateCleanDatabase;

public class SimonInstall {

	public static void main(String[] args) throws SQLException, IOException {
		if (args == null || args.length != 6) {			
			System.out.println("Usage SimonInstall [conn url] [DBUSER] [PASSWORD] [PAIS HOST] [Admin Password] [Subdir]");
			System.out.println("Example SimonInstall jdbc:mysql://localhost:3306/SIMON SIMON_USER SIMON_USER Argentina Admin simon");
		} else {
			GenerateCleanDatabase.createDataInDatabase(args[0], args[1], args[2], args[3],args[4],args[5]);
			System.out.println("finished");
		}
	}
}
