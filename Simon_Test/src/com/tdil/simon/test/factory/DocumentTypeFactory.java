package com.tdil.simon.test.factory;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.ibatis.DocumentTypeDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.DocumentType;

public class DocumentTypeFactory {

	public static DocumentType getDocumentType() throws SQLException {
		IBatisManager.beginTransaction();
		DocumentType result;
		// Create
		List list = DocumentTypeDAO.selectAllDocumentTypeNotDeleted();
		if (!list.isEmpty()) {
			result = (DocumentType)list.get(0);
		} else {
			// TODO
			result = null;
		}
		IBatisManager.commitTransaction();
		return result;
	}
}
