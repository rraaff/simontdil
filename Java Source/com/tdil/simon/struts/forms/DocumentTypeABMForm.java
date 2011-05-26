package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.DocumentTypeValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DocumentTypeDAO;
import com.tdil.simon.data.model.DocumentType;

public class DocumentTypeABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String name;
	
	private List<DocumentType> allDocumentType;
	
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
	public List<DocumentType> getAllDocumentType() {
		return allDocumentType;
	}
	public void setAllDocumentType(List<DocumentType> allDocumentType) {
		this.allDocumentType = allDocumentType;
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
		this.setAllDocumentType(DocumentTypeDAO.selectAllDocumentType());
	}
	
	public void initWith(int userId) throws SQLException {
		DocumentType documentType = DocumentTypeDAO.getDocumentType(userId);
		if (documentType != null) {
			this.id = userId;
			this.name = documentType.getName();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		if (id == 0) {
			this.addDocumentType();
		} else {
			this.modifyDocumentType();
		}
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		DocumentTypeValidation.validateName(this.name, "documentType.name", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		DocumentType exists = DocumentTypeDAO.getDocumentType(this.name);
		if (exists != null && exists.getId() != this.getId()) {
			// TODO agregar rb
			validationError.setFieldError("documentType.name", "documentType.name." + ValidationErrors.DOCUMENT_TYPE_ALREADY_EXISTS);
		}
	}
	
	private void modifyDocumentType() throws SQLException {
		DocumentType documentType = DocumentTypeDAO.getDocumentType(this.getId());
		documentType.setName(this.getName());
		DocumentTypeDAO.updateDocumentType(documentType);
	}
	private void addDocumentType() throws SQLException {
		DocumentType documentType = new DocumentType();
		documentType.setName(this.getName());
		DocumentTypeDAO.insertDocumentType(documentType);
	}
	public void delete(int position) throws SQLException {
		// TODO se puede borrar uno usado?? borrar es que no se puede asociar mas, pero si ya esta fue
		DocumentType documentType = this.getAllDocumentType().get(position);
		documentType.setDeleted(true);
		DocumentTypeDAO.logicallyDeleteDocumentType(documentType);
	}
	public void reactivate(int position) throws SQLException {
		DocumentType documentType = this.getAllDocumentType().get(position);
		documentType.setDeleted(false);
		DocumentTypeDAO.updateDocumentType(documentType);
	}
}
