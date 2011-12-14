package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.SystemUser;

public class ReferenceDocumentDAO {

	/*List<ReferenceDocumentVO>*/
	public static List selectAllReferenceDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllReferenceDocuments");
	}
	
	/*List<ReferenceDocumentVO>*/
	public static List selectNotDeletedReferenceDocument() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllReferenceDocumentsNotDeleted");
	}
	public static List selectNotDeletedReferenceDocument(SystemUser user) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllReferenceDocumentsNotDeletedForUser", user.getId());
	}
	
	public static List selectNotDeletedReferenceDocumentForModeratorHome() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedReferenceDocumentForModeratorHome");
	}
	
	public static List selectNotDeletedReferenceDocumentForModeratorHome(SystemUser user) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectNotDeletedReferenceDocumentForModeratorHomeForUser", user.getId());
	}
	
	public static ReferenceDocument getReferenceDocument(int id) throws SQLException {
		return (ReferenceDocument) IBatisManager.sqlMapper.queryForObject(
				"selectReferenceDocumentById", id);
	}
	
	public static ReferenceDocument getReferenceDocument(String title) throws SQLException {
		return (ReferenceDocument) IBatisManager.sqlMapper.queryForObject(
				"selectReferenceDocumentByTitle", title);
	}

	public static Integer insertReferenceDocument(ReferenceDocument document) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertReferenceDocument", document);
	}
	
	public static void updateReferenceDocument(ReferenceDocument document) throws SQLException {
		IBatisManager.sqlMapper.update("updateReferenceDocument", document);
	}
	
	public static void updateReferenceDocumentWithDocument(ReferenceDocument document) throws SQLException {
		IBatisManager.sqlMapper.update("updateReferenceDocumentWithDocument", document);
	}

	public static void logicallyDeleteReferenceDocument(ReferenceDocument document)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteReferenceDocument", document);
	}

	public static ReferenceDocument getReferenceDocumentWithDocument(Integer id) throws SQLException {
		return (ReferenceDocument) IBatisManager.sqlMapper.queryForObject(
				"selectReferenceDocumentByIdWithDocument", id);
	}
}
