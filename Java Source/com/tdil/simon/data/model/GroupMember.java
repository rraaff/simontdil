package com.tdil.simon.data.model;

public class GroupMember extends PersistentObject {

	private int groupId;
	private int systemUserId;
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getSystemUserId() {
		return systemUserId;
	}
	public void setSystemUserId(int systemUserId) {
		this.systemUserId = systemUserId;
	}
	

}
