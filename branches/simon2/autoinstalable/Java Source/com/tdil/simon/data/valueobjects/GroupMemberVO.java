package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.GroupMember;

public class GroupMemberVO extends GroupMember {

	private String groupName;
	private String systemUserName;
	private String countryName;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getSystemUserName() {
		return systemUserName;
	}
	public void setSystemUserName(String systemUserName) {
		this.systemUserName = systemUserName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
}
