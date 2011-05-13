package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.ibatis.GroupPermissionDAO;
import com.tdil.simon.data.ibatis.SubCategoryDAO;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.GroupPermission;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.data.valueobjects.SubCategoryPermissionVO;
import com.tdil.simon.data.valueobjects.SubCategoryVO;

public class SubCategoryPermissionABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String userGroupName;
	private int userGroupId;
	private int subCategoryId;
	
	private List<SubCategoryVO> allSubCategories;
	private List<SubCategoryPermissionVO> allPermission;
	
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
		this.subCategoryId = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(this.getUserGroupId());
		this.setUserGroupName(userGroup.getName());
		this.setAllSubCategories(SubCategoryDAO.selectAllSubCategoryNotDeleted());
		this.setAllPermission(GroupPermissionDAO.selectSubCategoryPermission(userGroup));
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
		GroupPermission permission = new GroupPermission();
		permission.setGroupId(this.getUserGroupId());
		permission.setObjectId(this.getSubCategoryId());
		permission.setObjectType(GroupPermission.SUBCATEGORY);
		GroupPermissionDAO.insertGroupPermission(permission);
	}
	
	public void delete(int position) throws SQLException {
		SubCategoryPermissionVO permissionVO = this.getAllPermission().get(position);
		GroupPermissionDAO.deleteGroupPermission(permissionVO);
	}
	
	public List<SubCategoryVO> getAllSubCategoriesFiltered() {
		Set<Integer> granted = new HashSet<Integer>();
		for (SubCategoryPermissionVO vo : getAllPermission()) {
			granted.add(vo.getObjectId());
		}
		List<SubCategoryVO> result = new ArrayList<SubCategoryVO>();
		for (SubCategoryVO vo : getAllSubCategories()) {
			if (!granted.contains(vo.getId())) {
				result.add(vo);
			}
		}
		return result;
	}
	
	public List<SubCategoryVO> getAllSubCategories() {
		return allSubCategories;
	}
	public void setAllSubCategories(List<SubCategoryVO> allSubCategories) {
		this.allSubCategories = allSubCategories;
	}
	public List<SubCategoryPermissionVO> getAllPermission() {
		return allPermission;
	}
	public void setAllPermission(List<SubCategoryPermissionVO> allPermission) {
		this.allPermission = allPermission;
	}
	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public int getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(int categoryId) {
		this.subCategoryId = categoryId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
}
