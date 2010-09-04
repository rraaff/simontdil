package com.tdil.simon.data.model;


public class Document extends PersistentObject {

	private String title;
	private String introduction;
	private boolean principal;
	private boolean typeOne;
	private boolean typeTwo;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String description) {
		this.introduction = description;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
	public boolean isTypeOne() {
		return typeOne;
	}
	public void setTypeOne(boolean typeOne) {
		this.typeOne = typeOne;
	}
	public boolean isTypeTwo() {
		return typeTwo;
	}
	public void setTypeTwo(boolean typeTwo) {
		this.typeTwo = typeTwo;
	}

	public boolean canAccess(SystemUser systemUser) {
		if (systemUser.isAdministrator()) {
			return true;
		}
		if (systemUser.isModerator()) {
			return true;
		}
		if (this.isTypeOne()) {
			if(systemUser.isTypeOne()) {
				return true;
			} 
		}
		if (this.isTypeTwo() && !systemUser.isTypeTwo()) {
			return false;
		} else {
			return true;
		}
	}
}
