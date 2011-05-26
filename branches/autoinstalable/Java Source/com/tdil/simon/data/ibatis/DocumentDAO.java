package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.SystemUser;

public class DocumentDAO {

	public static List selectAllDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocuments");
	}
	
	public static List selectNotDeletedDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentsNotDeleted");
	}
	
	public static List selectNotDeletedNotRelevantDocumentsForModeratorHome() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedNotRelevantDocuments");
	}
	
	public static List selectNotDeletedNotRelevantDocumentsForModeratorHome(SystemUser user) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedNotRelevantDocumentsUsingTypes", user.getId());
	}
	
	public static List selectNotDeletedDocumentWithConsolidatedVersions() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedDocumentWithConsolidatedVersions");
	}

	public static Document getDocumentForNegotiation() throws SQLException {
		return (Document) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentForNegotiation");
	}
	
	public static Document getDocument(int id) throws SQLException {
		return (Document) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentById", id);
	}

	public static Integer insertDocument(Document document) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertDocument", document);
	}
	
	public static void updateDocument(Document document) throws SQLException {
		IBatisManager.sqlMapper.update("updateDocument", document);
	}
	
	public static void updateDocumentRelevance(int docId, int relevance) throws SQLException {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", docId);
		params.put("relevance", relevance);
		IBatisManager.sqlMapper.update("updateDocumentRelevance", params);
	}

	public static void logicallyDeleteDocument(Document document)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteDocument", document);
	}

	public static void removeAllPrincipal() throws SQLException {
		IBatisManager.sqlMapper.update("removeAllPrincipal");
	}

	public static void markNotPrincipal(int documentTypeId) throws SQLException {
		IBatisManager.sqlMapper.update("updateAllAsNotPrincipal", documentTypeId);
	}

	public static Document getDocumentUnderWork() throws SQLException {
		return (Document) IBatisManager.sqlMapper.queryForObject("getDocumentUnderWork");
	}

}
