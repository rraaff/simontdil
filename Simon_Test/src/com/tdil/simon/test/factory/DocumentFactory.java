package com.tdil.simon.test.factory;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import junit.framework.TestCase;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.test.utils.RandomUtils;

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
	
	public static Document getDocumentById(int id) throws SQLException {
		Document result = null;
		IBatisManager.beginTransaction();
		result = DocumentDAO.getDocument(id);
		IBatisManager.commitTransaction();
		return result;
	}
	
	public static Document defaultBuild() throws SQLException, ValidationException {
		CreateDocumentForm createDocumentForm = new CreateDocumentForm();
		createDocumentForm.setTitle(RandomUtils.randomString("title"));
		createDocumentForm.setVersionName(RandomUtils.randomString("version"));
		createDocumentForm.setOrderNumber("1");
		createDocumentForm.setDocumentSubTypeId(DocumentTypeFactory.getDocumentType().getId());
		// mañana
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		createDocumentForm.setLimitObservations(dateFormat.format(cal.getTime()));
		createDocumentForm.setParagraphText("Paragraph");
		createDocumentForm.setConsolidated(true);
		createDocumentForm.setConsolidateText("asasas");
		createDocumentForm.save();
		
		int documentId = createDocumentForm.getDocumentId();
		return getDocumentById(documentId);
		
	}
}
