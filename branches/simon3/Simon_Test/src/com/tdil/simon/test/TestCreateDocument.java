package com.tdil.simon.test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.struts.forms.ViewVersionForm;
import com.tdil.simon.test.factory.DocumentTypeFactory;
import com.tdil.simon.test.utils.RandomUtils;

public class TestCreateDocument extends SimonTest {

	public void testCreateDocumentDates() throws SQLException, ValidationException {
		CreateDocumentForm createDocumentForm = new CreateDocumentForm();
		createDocumentForm.setTitle(RandomUtils.randomString("title"));
		createDocumentForm.setVersionName(RandomUtils.randomString("version"));
		createDocumentForm.setOrderNumber("1");
		createDocumentForm.setDocumentSubTypeId(DocumentTypeFactory.getDocumentType().getId());

		// ayer
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		createDocumentForm.setLimitObservations(dateFormat.format(cal.getTime()));
		ValidationError validationError = createDocumentForm.validateStep1(null, null);
		assertTrue(validationError.hasError()); // TODO mas especifico
		
		// mañana
		cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		createDocumentForm.setLimitObservations(dateFormat.format(cal.getTime()));
		validationError = createDocumentForm.validateStep1(null, null);
		assertFalse(validationError.hasError()); // TODO mas especifico

		// hoy
		cal = Calendar.getInstance();
		createDocumentForm.setLimitObservations(dateFormat.format(cal.getTime()));
		validationError = createDocumentForm.validateStep1(null, null);
		assertFalse(validationError.hasError()); // TODO mas especifico
		
		createDocumentForm.setParagraphText("Paragraph");
		createDocumentForm.setConsolidated(true);
		createDocumentForm.setConsolidateText("asasas");
		createDocumentForm.save();
		
		int documentId = createDocumentForm.getDocumentId();
		Version version = VersionDAO.getLastVersionForDocument(documentId);
		assertTrue(version.getUpToCommentDate().after(new Date()));
	}
}
