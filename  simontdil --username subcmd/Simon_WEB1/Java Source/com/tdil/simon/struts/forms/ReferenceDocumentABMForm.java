package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class ReferenceDocumentABMForm extends ActionForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private int categoryId;
	private String title;
	private FormFile document;
	
	private List<Category> allCategories;
	private List<ReferenceDocumentVO> allReferenceDocuments;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void init() throws SQLException {
		this.setAllReferenceDocuments(ReferenceDocumentDAO.selectAllReferenceDocument());
		this.setAllCategories(CategoryDAO.selectAllCategories());
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
			this.categoryId = reference.getCategoryId();
		}
	}
	
	public void save() throws SQLException, FileNotFoundException, IOException {
		if (id == 0) {
			this.addReferenceDocument();
		} else {
			this.modifyReferenceDocument();
		}
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.title = null;
		this.categoryId = 0;
	}
	
	private void modifyReferenceDocument() throws SQLException, FileNotFoundException, IOException {
		ReferenceDocument reference = ReferenceDocumentDAO.getReferenceDocument(this.getId());
		reference.setTitle(this.title);
		reference.setCategoryId(this.getCategoryId());
		String contentType = this.document.getContentType();
        String fileName    = this.document.getFileName();
        int fileSize       = this.document.getFileSize();
        if (fileName.indexOf('.') != -1 && fileName.lastIndexOf('.') != fileName.length()) {
        	reference.setExtension(fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase());
        }
        if (fileSize != 0) {
        	reference.setFileName(fileName);
        	reference.setContentType(contentType);
        	UploadUtils.uploadFileTo(this.document, SystemConfig.getReferenceDocumentStore() + "/" + this.getId());
        }
        ReferenceDocumentDAO.updateReferenceDocument(reference);
	}
	
	private void addReferenceDocument() throws SQLException, FileNotFoundException, IOException {
		ReferenceDocument reference = new ReferenceDocument();
		reference.setTitle(this.title);
		reference.setCategoryId(this.getCategoryId());
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
	public List<Category> getAllCategories() {
		return allCategories;
	}
	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}
}
