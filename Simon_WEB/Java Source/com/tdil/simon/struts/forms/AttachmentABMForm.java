package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.AttachmentValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ReferenceDocumentValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.AttachmentDAO;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.Attachment;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.valueobjects.CategorySelectionVO;
import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class AttachmentABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private int ownerId;
	private String ownerType;
	private String title;
	private FormFile data;
	private byte [] dataBytes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void init() throws SQLException {

	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initWith(int id) throws SQLException {
		Attachment attachment = AttachmentDAO.get(id);
		if (attachment != null) {
			this.id = attachment.getId();
			this.title = attachment.getTitle();
			this.ownerId = attachment.getOwnerId();
			this.ownerType = attachment.getOwnerType();
		}
	}
	
	public void save() throws SQLException, ValidationException{
		try {
			if (id == 0) {
				this.add();
			} else {
				this.modify();
			}
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage(), e);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			throw new ValidationException(exError);
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			throw new ValidationException(exError);
		}
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.title = null;
		this.ownerId = 0;
		this.ownerType = null;
	}
	
	private void modify() throws SQLException, FileNotFoundException, IOException {
		Attachment attachment = AttachmentDAO.get(this.getId());
		attachment.setTitle(this.title);
		if (this.dataBytes != null) {
			attachment.setData(this.dataBytes);
			String contentType = this.data.getContentType();
	        String fileName    = this.data.getFileName();
	        int fileSize       = this.data.getFileSize();
	        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length()) {
	        	attachment.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
	        }
	        if (fileSize != 0) {
	        	attachment.setFileName(fileName);
	        	attachment.setContentType(contentType);
	        }
		}
        AttachmentDAO.update(attachment);
	}
	
	private void add() throws SQLException, FileNotFoundException, IOException {
		Attachment attachment = new Attachment();
		attachment.setTitle(this.title);
		attachment.setOwnerId(this.getOwnerId());
		attachment.setOwnerType(this.getOwnerType());
		attachment.setData(this.dataBytes);
		String contentType = this.data.getContentType();
        String fileName    = this.data.getFileName();
        int fileSize       = this.data.getFileSize();
        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length()) {
        	attachment.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
        }
        if (fileSize != 0) {
        	attachment.setFileName(fileName);
        	attachment.setContentType(contentType);
        }
        int id = AttachmentDAO.insert(attachment);
        UploadUtils.uploadFileTo(this.data, SystemConfig.getAttachmentStore() + "/" + id);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void delete(int position) throws SQLException {
		/* TODO
		ReferenceDocument document = this.getAllReferenceDocuments().get(position);
		document.setDeleted(true);
		ReferenceDocumentDAO.logicallyDeleteReferenceDocument(document);*/
	}
	public void reactivate(int position) throws SQLException {
		/*ReferenceDocument document = this.getAllReferenceDocuments().get(position);
		document.setDeleted(false);
		ReferenceDocumentDAO.updateReferenceDocument(document);	*/
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(AttachmentABMForm.class);
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		AttachmentValidation.validateTitle(this.title, "attachment.title", validation);
		this.dataBytes = AttachmentValidation.validateData(this.data, "attachment.data", this.id == 0, validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	public FormFile getData() {
		return data;
	}
	public void setData(FormFile data) {
		this.data = data;
	}
	public byte[] getDataBytes() {
		return dataBytes;
	}
	public void setDataBytes(byte[] dataBytes) {
		this.dataBytes = dataBytes;
	}
}
