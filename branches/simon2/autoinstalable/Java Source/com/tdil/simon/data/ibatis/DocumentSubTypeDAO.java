package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.DocumentSubType;

public class DocumentSubTypeDAO {

	public static List selectAllDocumentSubTypeByDocumentTypeId(int documentTypeId) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentSubTypeByDocumentTypeId", documentTypeId);
	}
	
	// List de DocumentSubTypeVO
	public static List selectAllDocumentSubTypeNotDeleted() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllDocumentSubTypeNotDeleted");
	}
	
	public static DocumentSubType getDocumentSubType(int id) throws SQLException {
		return (DocumentSubType) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentSubTypeById", id);
	}

	public static DocumentSubType getDocumentSubType(String name, int documentTypeId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("documentTypeId", documentTypeId);
		return (DocumentSubType) IBatisManager.sqlMapper.queryForObject(
				"selectDocumentSubTypeByNameAndDocumentTypeId", params);
	}
	
	public static void insertDocumentSubType(DocumentSubType subDocumentType) throws SQLException {
		IBatisManager.sqlMapper.insert("insertDocumentSubType", subDocumentType);
	}

	public static void updateDocumentSubType(DocumentSubType subDocumentType) throws SQLException {
		IBatisManager.sqlMapper.update("updateDocumentSubType", subDocumentType);
	}

	public static void logicallyDeleteDocumentSubType(DocumentSubType subDocumentType)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteDocumentSubType", subDocumentType);
	}


}
