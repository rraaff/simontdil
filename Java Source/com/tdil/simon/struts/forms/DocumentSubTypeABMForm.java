package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.DocumentTypeValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.DocumentSubTypeDAO;
import com.tdil.simon.data.ibatis.DocumentTypeDAO;
import com.tdil.simon.data.ibatis.SubCategoryDAO;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.DocumentSubType;
import com.tdil.simon.data.model.DocumentType;
import com.tdil.simon.data.model.SubCategory;

public class DocumentSubTypeABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String documentTypeName;
	private int documentTypeId;
	private int id;
	private String name;
	
	private List<DocumentSubType> allDocumentSubType;
	
	public String getDocumentTypeName() {
		return documentTypeName;
	}
	public void setDocumentTypeName(String categoryName) {
		this.documentTypeName = categoryName;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int categoryId) {
		this.documentTypeId = categoryId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DocumentSubType> getAllDocumentSubType() {
		return allDocumentSubType;
	}
	public void setAllDocumentSubType(List<DocumentSubType> allSubCategory) {
		this.allDocumentSubType = allSubCategory;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		DocumentType documentType = DocumentTypeDAO.getDocumentType(this.getDocumentTypeId());
		setDocumentTypeName(documentType.getName());
		this.setAllDocumentSubType(DocumentSubTypeDAO.selectAllDocumentSubTypeByDocumentTypeId(this.getDocumentTypeId()));
	}
	
	public void initWith(int documentSubTypeId) throws SQLException {
		DocumentSubType documentSubType = DocumentSubTypeDAO.getDocumentSubType(documentSubTypeId);
		if (documentSubType != null) {
			this.id = documentSubTypeId;
			this.name = documentSubType.getName();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		if (id == 0) {
			this.addDocumentSubType();
		} else {
			this.modifyDocumentSubType();
		}
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		DocumentTypeValidation.validateName(this.name, "documentSubType.name", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		DocumentSubType exists = DocumentSubTypeDAO.getDocumentSubType(this.name, this.getDocumentTypeId());
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("documentSubType.name", "documentSubType.name." + ValidationErrors.DOCUMENT_SUB_TYPE_ALREADY_EXISTS);
		}
	}
	
	private void modifyDocumentSubType() throws SQLException {
		DocumentSubType subType = DocumentSubTypeDAO.getDocumentSubType(this.getId());
		subType.setName(this.getName());
		DocumentSubTypeDAO.updateDocumentSubType(subType);
	}
	private void addDocumentSubType() throws SQLException {
		DocumentSubType subType = new DocumentSubType();
		subType.setDocumentTypeId(this.getDocumentTypeId());
		subType.setName(this.getName());
		DocumentSubTypeDAO.insertDocumentSubType(subType);
	}
	public void delete(int position) throws SQLException {
		// TODO se puede borrar uno usado?? borrar es que no se puede asociar mas, pero si ya esta fue
		DocumentSubType documentSubType = this.getAllDocumentSubType().get(position);
		documentSubType.setDeleted(true);
		DocumentSubTypeDAO.logicallyDeleteDocumentSubType(documentSubType);
	}
	public void reactivate(int position) throws SQLException {
		DocumentSubType documentSubType = this.getAllDocumentSubType().get(position);
		documentSubType.setDeleted(false);
		DocumentSubTypeDAO.updateDocumentSubType(documentSubType);
	}
}
