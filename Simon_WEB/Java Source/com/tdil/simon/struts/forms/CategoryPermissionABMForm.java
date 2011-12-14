package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.GroupPermissionDAO;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.GroupPermission;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.data.valueobjects.CategoryPermissionVO;

public class CategoryPermissionABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String userGroupName;
	private int userGroupId;
	private int categoryId;
	
	private List<Category> allCategories;
	private List<CategoryPermissionVO> allPermission;
	
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
		this.categoryId = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(this.getUserGroupId());
		this.setUserGroupName(userGroup.getName());
		this.setAllCategories(CategoryDAO.selectAllCategoriesNotDeleted());
		this.setAllPermission(GroupPermissionDAO.selectCategoryPermission(userGroup));
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
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
//		}
	}
	
	private void addPermission() throws SQLException {
		GroupPermission exists = GroupPermissionDAO.selectCategoryPermission(this.getUserGroupId(), this.getCategoryId());
		if (exists == null) {
			GroupPermission permission = new GroupPermission();
			permission.setGroupId(this.getUserGroupId());
			permission.setObjectId(this.getCategoryId());
			permission.setObjectType(GroupPermission.CATEGORY);
			GroupPermissionDAO.insertGroupPermission(permission);
		}
	}
	
	public void delete(int position) throws SQLException {
		CategoryPermissionVO permissionVO = this.getAllPermission().get(position);
		GroupPermissionDAO.deleteGroupPermission(permissionVO);
	}
	
	public List<Category> getAllCategories() {
		return allCategories;
	}
	public void setAllCategories(List<Category> allSubCategories) {
		this.allCategories = allSubCategories;
	}
	public List<CategoryPermissionVO> getAllPermission() {
		return allPermission;
	}
	public void setAllPermission(List<CategoryPermissionVO> allPermission) {
		this.allPermission = allPermission;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
}
