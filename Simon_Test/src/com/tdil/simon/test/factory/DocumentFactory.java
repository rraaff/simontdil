package com.tdil.simon.test.factory;

import java.sql.SQLException;
import java.util.Iterator;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.Document;

public class DocumentFactory {

	public static Document getDocumentByTitle(String title) throws SQLException {
		Document result = null;
		IBatisManager.beginTransaction();
		Iterator<Document> all = DocumentDAO.selectAllDocument().iterator();
		while (result == null && all.hasNext()) {
			Document doc = all.next();
			if (doc.getTitle().equals(title)) {
				result = doc;
			}
		}
		IBatisManager.commitTransaction();
		return result;
	}
}
