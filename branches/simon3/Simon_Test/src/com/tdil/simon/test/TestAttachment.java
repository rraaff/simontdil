package com.tdil.simon.test;

import java.sql.SQLException;

import org.apache.struts.upload.DiskFile;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.AttachmentABMForm;

public class TestAttachment extends SimonTest {

	public void testCreateAttachments() throws SQLException, ValidationException {
		final AttachmentABMForm attachmentABMForm = new AttachmentABMForm();
		attachmentABMForm.setTitle("ATT"); // TODO Random
		attachmentABMForm.setOwnerId(1);
		attachmentABMForm.setOwnerType("XXX");
		DiskFile df = new DiskFile("/home/mgodoy/Documents/of_bsas.pdf");
		df.setContentType("PDF");
		df.setFileSize(100);
		df.setFileName("of_bsas.pdf");
		attachmentABMForm.setData(df);
		ValidationError error = attachmentABMForm.validate();
		assertFalse(error.hasError());
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				attachmentABMForm.save();
			}
		});
		// TODO asserts
	}
}
