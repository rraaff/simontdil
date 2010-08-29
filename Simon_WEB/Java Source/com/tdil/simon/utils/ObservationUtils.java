package com.tdil.simon.utils;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.ObservationDAO;

public class ObservationUtils {
	
	private static final Logger Log = LoggerProvider.getLogger(ObservationUtils.class);

	public static Integer countPrivateObservationsForNegotiatedVersion() {
		try {
			return ObservationDAO.countPrivateObservationsForVersionInNegotiation();
		} catch (SQLException e) {
			Log.error(e.getMessage(), e);
			return 0;
		}
	}
}
