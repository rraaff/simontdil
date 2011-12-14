package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.ibatis.GroupMemberDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.ibatis.UserGroupDAO;
import com.tdil.simon.data.model.GroupMember;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.data.valueobjects.GroupMemberVO;
import com.tdil.simon.data.valueobjects.UserVO;

public class ManageUsersOfGroupABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int userGroupId;
	private String userGroupName;

	private int systemUserId;
	
	private List<UserVO> allUsers;
	private List<GroupMemberVO> allGroupMembers;
	
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
		this.systemUserId = 0;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		UserGroup userGroup = UserGroupDAO.getUserGroup(this.getUserGroupId());
		this.setUserGroupName(userGroup.getName());
		this.setAllUsers(SystemUserDAO.selectDelegateUsers());
		this.setAllGroupMembers(GroupMemberDAO.selectGroupMemberByGroup(userGroup));
	}
	
	public void initWith(int userId) throws SQLException {
		
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		this.addSystemUserToGroup();
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	
	private void addSystemUserToGroup() throws SQLException {
		GroupMember groupMember = new GroupMember();
		groupMember.setGroupId(this.getUserGroupId());
		groupMember.setSystemUserId(this.getSystemUserId());
		GroupMemberDAO.insertGroupMember(groupMember);
	}
	
	public void delete(int position) throws SQLException {
		GroupMemberVO groupMemberVO = this.getAllGroupMembers().get(position);
		GroupMemberDAO.deleteGroupMember(groupMemberVO);
	}
	
	public List<UserVO> getAllUsersFiltered() {
		Set<Integer> granted = new HashSet<Integer>();
		for (GroupMemberVO vo : getAllGroupMembers()) {
			granted.add(vo.getSystemUserId());
		}
		List<UserVO> result = new ArrayList<UserVO>();
		for (UserVO vo : getAllUsers()) {
			if (!granted.contains(vo.getId())) {
				result.add(vo);
			}
		}
		return result;
	}
	

	public int getUserGroupId() {
		return userGroupId;
	}
	public void setUserGroupId(int userGroupId) {
		this.userGroupId = userGroupId;
	}
	public String getUserGroupName() {
		return userGroupName;
	}
	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}
	public int getSystemUserId() {
		return systemUserId;
	}
	public void setSystemUserId(int systemUserId) {
		this.systemUserId = systemUserId;
	}
	public List<UserVO> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserVO> allUsers) {
		this.allUsers = allUsers;
	}
	public List<GroupMemberVO> getAllGroupMembers() {
		return allGroupMembers;
	}
	public void setAllGroupMembers(List<GroupMemberVO> allGroupMembers) {
		this.allGroupMembers = allGroupMembers;
	}
}
