package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tdil.simon.data.model.Document;

public class DocumentDAO {

	public static List selectAllDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocuments");
	}
	
	public static List selectNotDeletedDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentsNotDeleted");
	}
	
	public static List selectNotDeletedDocumentWithConsolidatedVersions() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedDocumentWithConsolidatedVersions");
	}

	public static Document selectPrincipalDocument(boolean documentTypeOne, boolean documentTypeTwo) throws SQLException {
		HashMap params = new HashMap();
		params.put("typeOne", documentTypeOne);
		params.put("typeTwo", documentTypeTwo);
		return (Document)IBatisManager.sqlMapper.queryForObject("selectPrincipalDocument", params);
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

	public static void logicallyDeleteDocument(Document document)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteDocument", document);
	}

	public static void removeAllPrincipal() throws SQLException {
		IBatisManager.sqlMapper.update("removeAllPrincipal");
	}

	public static void markNotPrincipal(boolean documentTypeOne, boolean documentTypeTwo) throws SQLException {
		HashMap params = new HashMap();
		params.put("typeOne", documentTypeOne);
		params.put("typeTwo", documentTypeTwo);
		IBatisManager.sqlMapper.update("updateAllAsNotPrincipal", params);
	}


}
