package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.DocumentTypeValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DocumentTypeDAO;
import com.tdil.simon.data.model.DocumentType;

public class DocumentSubTypeABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String documentTypeName;
	private int documentTypeId;
	private int id;
	private String name;
	private String orderNumber;
	
	private List<DocumentType> allDocumentSubType;
	
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
	public List<DocumentType> getAllDocumentSubType() {
		return allDocumentSubType;
	}
	public void setAllDocumentSubType(List<DocumentType> allSubCategory) {
		this.allDocumentSubType = allSubCategory;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.orderNumber = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		DocumentType documentType = DocumentTypeDAO.getDocumentType(this.getDocumentTypeId());
		setDocumentTypeName(documentType.getName());
		this.setAllDocumentSubType(DocumentTypeDAO.selectAllDocumentTypeByParentId(this.getDocumentTypeId()));
	}
	
	public void initWith(int documentSubTypeId) throws SQLException {
		DocumentType documentSubType = DocumentTypeDAO.getDocumentType(documentSubTypeId);
		if (documentSubType != null) {
			this.id = documentSubTypeId;
			this.name = documentSubType.getName();
			this.orderNumber = String.valueOf(documentSubType.getOrderNumber());
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
		IdValidation.validate(this.orderNumber, true, "documentType.orderNumber", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		DocumentType exists = DocumentTypeDAO.getDocumentType(this.name, this.getDocumentTypeId());
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("documentSubType.name", "documentSubType.name." + ValidationErrors.DOCUMENT_SUB_TYPE_ALREADY_EXISTS);
		}
	}
	
	private void modifyDocumentSubType() throws SQLException {
		DocumentType subType = DocumentTypeDAO.getDocumentType(this.getId());
		subType.setName(this.getName());
		subType.setOrderNumber(Integer.parseInt(this.orderNumber));
		DocumentTypeDAO.updateDocumentType(subType);
	}
	private void addDocumentSubType() throws SQLException {
		DocumentType subType = new DocumentType();
		subType.setParentId(this.getDocumentTypeId());
		subType.setName(this.getName());
		subType.setOrderNumber(Integer.parseInt(this.orderNumber));
		DocumentTypeDAO.insertDocumentType(subType);
	}
	public void delete(int position) throws SQLException {
		// TODO se puede borrar uno usado?? borrar es que no se puede asociar mas, pero si ya esta fue
		DocumentType documentSubType = this.getAllDocumentSubType().get(position);
		documentSubType.setDeleted(true);
		DocumentTypeDAO.logicallyDeleteDocumentType(documentSubType);
	}
	public void reactivate(int position) throws SQLException {
		DocumentType documentSubType = this.getAllDocumentSubType().get(position);
		documentSubType.setDeleted(false);
		DocumentTypeDAO.updateDocumentType(documentSubType);
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
