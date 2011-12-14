package com.tdil.simon.test.factory;

import java.sql.SQLException;
import java.util.Iterator;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Version;

public class VersionFactory {

	public static Version getLastVersionForDocument(Document doc) throws SQLException {
		Version result = null;
		IBatisManager.beginTransaction();
		result = VersionDAO.getLastVersionForDocument(doc.getId());
		IBatisManager.commitTransaction();
		return result;
	}
}
