package com.tdil.simon.data.model;

public class GroupPermission extends PersistentObject {

	public static String DOCUMENT_TYPE = "DOCUMENT_TYPE";
	public static String SUBCATEGORY = "SUBCATEGORY";
	
	private int groupId;
	private int objectId;
	private String objectType;
	
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}


}
