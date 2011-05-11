package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.DocumentType;

public class DocumentTypeDAO {

	public static List selectAllDocumentType() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentType");
	}
	
	public static List selectAllDocumentTypeNotDeleted() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentTypeNotDeleted");
	}
	
	public static DocumentType getDocumentType(int id) throws SQLException {
		return (DocumentType) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentTypeById", id);
	}

	public static DocumentType getDocumentType(String name) throws SQLException {
		return (DocumentType) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentTypeByName", name);
	}
	
	public static void insertDocumentType(DocumentType documentType) throws SQLException {
		IBatisManager.sqlMapper.insert("insertDocumentType", documentType);
	}

	public static void updateDocumentType(DocumentType documentType) throws SQLException {
		IBatisManager.sqlMapper.update("updateDocumentType", documentType);
	}

	public static void logicallyDeleteDocumentType(DocumentType documentType)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteDocumentType", documentType);
	}


}
