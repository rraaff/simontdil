package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.valueobjects.ObservationSummaryVO;

public class ObservationDAO {

	public static List selectAllObservationsFor(int paragraphID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllObservationsForParagraph", paragraphID);
	}

	public static List selectNotDeletedObservationsFor(int paragraphID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllObservationsForParagraphNotDeleted", paragraphID);
	}

	public static List selectNotDeletedObservationsForVersion(int versionID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllObservationsForVersionNotDeleted", versionID);
	}
	
	public static Integer countNotDeletedObservationsForVersion(int versionID) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.queryForObject("countAllObservationsForVersionNotDeleted", versionID);
	}

	public static List selectNotDeletedPrivateObservationsForVersion(int versionID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedPrivateObservationsForVersion", versionID);
	}
	
	public static List selectNotDeletedPrivateObservationsForParagraph(int paragraphId) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedPrivateObservationsForParagraph", paragraphId);
	}

	public static List searchObservations(HashMap parameters) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("searchObservations", parameters);
	}

	public static Observation getObservation(int id) throws SQLException {
		return (Observation) IBatisManager.sqlMapper.queryForObject("selectObservationById", id);
	}

	public static Integer insertObservation(Observation observation) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.insert("insertObservation", observation);
	}

	public static void logicallyDeleteObservation(Observation Observation) throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteObservation", Observation);
	}

	public static void logicallyDeleteObservationById(int id) throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteObservationById", id);
	}

	/*******/
	public static ObservationSummaryVO countPrivateObservationsForVersionInNegotiation() throws SQLException {
		return (ObservationSummaryVO) IBatisManager.sqlMapper
				.queryForObject("countPrivateObservationsForVersionInNegotiation");
	}
	
	public static ObservationSummaryVO countPrivateObservationsForNegotiatedParagraph(int paragraph) throws SQLException {
		return (ObservationSummaryVO) IBatisManager.sqlMapper
				.queryForObject("countPrivateObservationsForNegotiatedParagraph", paragraph);
	}

	public static Integer countPrivateObservationsForParagrapth(int paragraphdId) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.queryForObject("countPrivateObservationsForParagrapth", paragraphdId);
	}
}
