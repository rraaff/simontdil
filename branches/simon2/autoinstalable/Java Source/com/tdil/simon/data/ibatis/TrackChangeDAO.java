package com.tdil.simon.data.ibatis;

import java.sql.SQLException;

import com.tdil.simon.data.model.TrackChange;

public class TrackChangeDAO {

	public static TrackChange getLastVersionForDocument(int paragraphId) throws SQLException {
		return (TrackChange)IBatisManager.sqlMapper.queryForObject("getTrackChangeForParagraph", paragraphId);
	}
	
	public static Integer insertTrackChange(TrackChange change) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.insert("insertChange", change);
	}
	
	public static Integer updateTrackChange(TrackChange change) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.insert("updateChange", change);
	}

}
