package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.ibatis.DocumentTypeDAO;
import com.tdil.simon.data.ibatis.GroupPermissionDAO;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.DocumentType;
import com.tdil.simon.data.model.GroupPermission;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.data.valueobjects.DocumentTypePermissionVO;

public class DocumentTypePermissionABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String userGroupName;
	private int userGroupId;
	private int documentTypeId;
	
	private List<DocumentType> allDocumentType;
	private List<DocumentTypePermissionVO> allPermission;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.documentTypeId = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(this.getUserGroupId());
		this.setUserGroupName(userGroup.getName());
		this.setAllDocumentType(DocumentTypeDAO.selectAllDocumentTypeNotDeleted());
		this.setAllPermission(GroupPermissionDAO.selectDocumentTypePermission(userGroup));
	}
	
	public void initWith(int userId) throws SQLException {
		
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		this.addPermission();
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		// TODO chequeo de existencia
//		DocumentTypeValidation.validateName(this.name, "documentType.name", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO chequeo de existencia
//		DocumentType exists = DocumentTypeDAO.getDocumentType(this.name);
//		if (exists != null && exists.getId() != this.getId()) {
//			// TODO agregar rb
//			validationError.setFieldError("documentType.name", "documentType.name." + ValidationErrors.DOCUMENT_TYPE_ALREADY_EXISTS);
//		}
	}
	
	private void addPermission() throws SQLException {
		GroupPermission permission = new GroupPermission();
		permission.setGroupId(this.getUserGroupId());
		permission.setObjectId(this.getDocumentTypeId());
		permission.setObjectType(GroupPermission.DOCUMENT_TYPE);
		GroupPermissionDAO.insertGroupPermission(permission);
	}
	
	public void delete(int position) throws SQLException {
		DocumentTypePermissionVO permissionVO = this.getAllPermission().get(position);
		GroupPermissionDAO.deleteGroupPermission(permissionVO);
	}
	
	public List<DocumentType> getAllDocumentTypeFiltered() {
		Set<Integer> granted = new HashSet<Integer>();
		for (DocumentTypePermissionVO vo : getAllPermission()) {
			granted.add(vo.getObjectId());
		}
		List<DocumentType> result = new ArrayList<DocumentType>();
		for (DocumentType vo : getAllDocumentType()) {
			if (!granted.contains(vo.getId())) {
				result.add(vo);
			}
		}
		return result;
	}
	
	public List<DocumentType> getAllDocumentType() {
		return allDocumentType;
	}
	public void setAllDocumentType(List<DocumentType> allDocumentType) {
		this.allDocumentType = allDocumentType;
	}
	public List<DocumentTypePermissionVO> getAllPermission() {
		return allPermission;
	}
	public void setAllPermission(List<DocumentTypePermissionVO> allPermission) {
		this.allPermission = allPermission;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int categoryId) {
		this.documentTypeId = categoryId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
}
