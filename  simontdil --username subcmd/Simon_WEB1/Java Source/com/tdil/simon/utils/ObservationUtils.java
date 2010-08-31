package com.tdil.simon.utils;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.valueobjects.ObservationSummaryVO;

public class ObservationUtils {
	
	private static final Logger Log = LoggerProvider.getLogger(ObservationUtils.class);

	public static ObservationSummaryVO countPrivateObservationsForNegotiatedVersion() {
		try {
			return ObservationDAO.countPrivateObservationsForVersionInNegotiation();
		} catch (SQLException e) {
			Log.error(e.getMessage(), e);
			return new ObservationSummaryVO();
		}
	}
}
