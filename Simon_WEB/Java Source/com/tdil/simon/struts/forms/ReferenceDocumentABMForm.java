package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ReferenceDocumentValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.valueobjects.CategorySelectionVO;
import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class ReferenceDocumentABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private int categoryId;
	private String title;
	private FormFile document;
	private byte [] documentBytes;
	private String orderNumber;
	
	private List<CategorySelectionVO> allCategories;
	private List<ReferenceDocumentVO> allReferenceDocuments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void init() throws SQLException {
		this.setAllReferenceDocuments(ReferenceDocumentDAO.selectAllReferenceDocument());
		this.setAllCategories(CategoryDAO.selectAllCategoriesNotDeleted());
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initWith(int id) throws SQLException {
		ReferenceDocument reference = ReferenceDocumentDAO.getReferenceDocument(id);
		if (reference != null) {
			this.id = reference.getId();
			this.title = reference.getTitle();
			this.categoryId = reference.getSubCategoryId();
			this.orderNumber = String.valueOf(reference.getOrderNumber());
		}
	}
	
	public void save() throws SQLException, ValidationException{
		try {
			if (id == 0) {
				this.addReferenceDocument();
			} else {
				this.modifyReferenceDocument();
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
		this.categoryId = 0;
		this.orderNumber = null;
	}
	
	private void modifyReferenceDocument() throws SQLException, FileNotFoundException, IOException {
		ReferenceDocument reference = ReferenceDocumentDAO.getReferenceDocument(this.getId());
		reference.setTitle(this.title);
		reference.setSubCategoryId(this.getCategoryId());
		reference.setOrderNumber(Integer.parseInt(this.orderNumber));
		String contentType = this.document.getContentType();
        String fileName    = this.document.getFileName();
        int fileSize       = this.document.getFileSize();
        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length()) {
        	reference.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
        }
        if (fileSize != 0) {
        	reference.setDocument(this.documentBytes);
        	reference.setFileName(fileName);
        	reference.setContentType(contentType);
        	UploadUtils.uploadFileTo(this.document, SystemConfig.getReferenceDocumentStore() + "/" + this.getId());
        	ReferenceDocumentDAO.updateReferenceDocumentWithDocument(reference);
        } else {
        	ReferenceDocumentDAO.updateReferenceDocument(reference);
        }
	}
	
	private void addReferenceDocument() throws SQLException, FileNotFoundException, IOException {
		ReferenceDocument reference = new ReferenceDocument();
		reference.setTitle(this.title);
		reference.setSubCategoryId(this.getCategoryId());
		reference.setOrderNumber(Integer.parseInt(this.orderNumber));
		reference.setDocument(this.documentBytes);
		int docId = ReferenceDocumentDAO.insertReferenceDocument(reference);
		String contentType = this.document.getContentType();
        String fileName    = this.document.getFileName();
        int fileSize       = this.document.getFileSize();
        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length()) {
        	reference.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
        }
        if (fileSize != 0) {
        	reference.setFileName(fileName);
        	reference.setContentType(contentType);
        	UploadUtils.uploadFileTo(this.document, SystemConfig.getReferenceDocumentStore() + "/" + docId);
        }
        ReferenceDocumentDAO.updateReferenceDocument(reference);
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public FormFile getDocument() {
		return document;
	}
	public void setDocument(FormFile document) {
		this.document = document;
	}
	public List<ReferenceDocumentVO> getAllReferenceDocuments() {
		return allReferenceDocuments;
	}
	public void setAllReferenceDocuments(List<ReferenceDocumentVO> allReferenceDocuments) {
		this.allReferenceDocuments = allReferenceDocuments;
	}
	public List<CategorySelectionVO> getAllCategories() {
		for (CategorySelectionVO vo : allCategories) {
			vo.setSelectedId(this.getCategoryId());
		}
		return allCategories;
	}
	public void setAllCategories(List<CategorySelectionVO> allCategories) {
		this.allCategories = allCategories;
	}
	
	public void delete(int position) throws SQLException {
		ReferenceDocument document = this.getAllReferenceDocuments().get(position);
		document.setDeleted(true);
		ReferenceDocumentDAO.logicallyDeleteReferenceDocument(document);
	}
	public void reactivate(int position) throws SQLException {
		ReferenceDocument document = this.getAllReferenceDocuments().get(position);
		document.setDeleted(false);
		ReferenceDocumentDAO.updateReferenceDocument(document);
		
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ReferenceDocumentABMForm.class);
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		ReferenceDocumentValidation.validateTitle(this.title, "refDoc.title", validation);
		this.documentBytes = ReferenceDocumentValidation.validateDocument(this.document, "refDoc.document", this.id == 0, validation);
		if (this.categoryId == 0){
			validation.setFieldError("refDoc.category", ValidationErrors.CANNOT_BE_EMPTY);
		}
		IdValidation.validate(this.orderNumber, true, "refDoc.orderNumber", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
//	TODO se quita la validacion 
//		ReferenceDocument exists = ReferenceDocumentDAO.getReferenceDocument(this.title);
//		if (exists != null && exists.getId() != this.getId()) {
//			validationError.setFieldError("refDoc.title", "refDoc.title." + ValidationErrors.REFERENCE_DOCUMENT_ALREADY_EXISTS);
//		}
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
