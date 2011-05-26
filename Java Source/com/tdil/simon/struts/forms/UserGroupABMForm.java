package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.UserGroupValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.UserGroup;

public class UserGroupABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String name;
	
	private List<UserGroup> allUserGroup;
	
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
	public List<UserGroup> getAllUserGroup() {
		return allUserGroup;
	}
	public void setAllUserGroup(List<UserGroup> allUserGroup) {
		this.allUserGroup = allUserGroup;
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
		this.setAllUserGroup(UserGroupDAO.selectAllUserGroup());
	}
	
	public void initWith(int userId) throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(userId);
		if (userGroup != null) {
			this.id = userId;
			this.name = userGroup.getName();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		if (id == 0) {
			this.addUserGroup();
		} else {
			this.modifyUserGroup();
		}
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		UserGroupValidation.validateName(this.name, "userGroup.name", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		UserGroup exists = UserGroupDAO.getUserGroup(this.name);
		if (exists != null && exists.getId() != this.getId()) {
			// TODO agregar rb
			validationError.setFieldError("userGroup.name", "userGroup.name." + ValidationErrors.USER_GROUP_ALREADY_EXISTS);
		}
	}
	
	private void modifyUserGroup() throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(this.getId());
		userGroup.setName(this.getName());
		UserGroupDAO.updateUserGroup(userGroup);
	}
	private void addUserGroup() throws SQLException {
		UserGroup userGroup = new UserGroup();
		userGroup.setName(this.getName());
		UserGroupDAO.insertUserGroup(userGroup);
	}
	public void delete(int position) throws SQLException {
		// TODO se puede borrar uno usado?? borrar es que no se puede asociar mas, pero si ya esta fue
		UserGroup userGroup = this.getAllUserGroup().get(position);
		userGroup.setDeleted(true);
		UserGroupDAO.logicallyDeleteUserGroup(userGroup);
	}
	public void reactivate(int position) throws SQLException {
		UserGroup userGroup = this.getAllUserGroup().get(position);
		userGroup.setDeleted(false);
		UserGroupDAO.updateUserGroup(userGroup);
	}
}
