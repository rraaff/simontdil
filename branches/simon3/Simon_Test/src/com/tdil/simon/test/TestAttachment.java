package com.tdil.simon.test;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts.upload.DiskFile;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.model.Attachment;
import com.tdil.simon.data.model.AttachmentOwnerType;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.AttachmentABMForm;
import com.tdil.simon.test.factory.AttachmentFactory;
import com.tdil.simon.test.utils.RandomUtils;

public class TestAttachment extends SimonTest {

	public void testCreateAttachments() throws SQLException, ValidationException {
		final AttachmentABMForm attachmentABMForm = new AttachmentABMForm();
		attachmentABMForm.setTitle("ATT"); // TODO Random
		int ownerId = (int)RandomUtils.nextNumerator();
		attachmentABMForm.setOwnerId(ownerId);
		attachmentABMForm.setOwnerType(AttachmentOwnerType.FINAL_VERSION.name());
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
		
		List<Attachment> list = AttachmentFactory.get(ownerId, AttachmentOwnerType.FINAL_VERSION);
		assertEquals("Tiene que haber solo 1", 1, list.size());
		Attachment attachment = list.get(0);
		assertEquals("PDF", attachment.getContentType());
		assertEquals("PDF", attachment.getExtension());
		assertEquals("ATT", attachment.getTitle());
		assertEquals("of_bsas.pdf", attachment.getFileName());
		assertNull(attachment.getData());
		
		Attachment att1 = AttachmentFactory.get(attachment.getId());
		assertNull(att1.getData());
		att1 = AttachmentFactory.getWithData(attachment.getId());
		assertNotNull(att1.getData());
	}
}
