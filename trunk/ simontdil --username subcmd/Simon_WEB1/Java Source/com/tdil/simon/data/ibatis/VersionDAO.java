package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionForListVO;

public class VersionDAO {

	public static List selectAllVersionsFor(int documentID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllVersionsForDocument", documentID);
	}
	
	public static List selectNotDeletedVersionsFor(int documentID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllVersionsForDocumentNotDeleted", documentID);
	}
	
	public static Version getLastVersionForDocument(int documentID) throws SQLException {
		return (Version)IBatisManager.sqlMapper.queryForObject("getLastVersionForDocument", documentID);
	}
	
	public static Version getVersionForNegotiation(int documentID) throws SQLException {
		return (Version)IBatisManager.sqlMapper.queryForObject("selectVersionForNegotiation", documentID);
	}
	
	public static Version getVersionUnderNegotiation() throws SQLException {
		return (Version)IBatisManager.sqlMapper.queryForObject("selectVersionUnderNegotiation");
	}
	public static VersionForListVO selectPrincipalVersion(boolean documentTypeOne, boolean documentTypeTwo) throws SQLException {
		HashMap params = new HashMap();
		params.put("typeOne", documentTypeOne);
		params.put("typeTwo", documentTypeTwo);
		return (VersionForListVO)IBatisManager.sqlMapper.queryForObject("selectPrincipalVersion", params);
	}
	
	public static Version getVersionForDocumentAndNumber(int documentId, int number) throws SQLException {
		HashMap params = new HashMap();
		params.put("documentId", documentId);
		params.put("number", number);
		return (Version) IBatisManager.sqlMapper.queryForObject(
				"getVersionForDocumentAndNumber", params);
	}
	
	public static List<VersionForListVO> selectVersionsVOForList() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectVersionsVOForList");
	}
	
	public static List getAllVersionNumbersFor(int documentID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllVersionNumbersForDocument", documentID);
	}

	public static Version getVersion(int id) throws SQLException {
		return (Version) IBatisManager.sqlMapper.queryForObject(
				"selectVersionById", id);
	}
	
	public static Integer getMaxConsolidatedVersionFor(int documentId) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.queryForObject(
				"getMaxConsolidatedVersionFor", documentId);
	}

	public static Integer insertVersion(Version version) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertVersion", version);
	}
	
	public static void updateVersionStatus(Version version) throws SQLException {
		IBatisManager.sqlMapper.update("updateVersionStatus", version);
	}
	
	public static void updateVersion(Version version) throws SQLException {
		IBatisManager.sqlMapper.update("updateVersion", version);
	}

	public static void logicallyDeleteVersion(Version version)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteVersion", version);
	}

	public static Version getVersionUnderWork() throws SQLException {
		return (Version) IBatisManager.sqlMapper.queryForObject("selectVersionForWork");
	}

	public static void updateVersionUnderNegotiationToConsolidatedForDocument(int documentId) throws SQLException {
		IBatisManager.sqlMapper.update("updateVersionUnderNegotiationToConsolidatedForDocument", documentId);
	}


}
