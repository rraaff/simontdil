package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.struts.ApplicationResources;

public class UserVO extends SystemUser {

	private static final long serialVersionUID = 1851828529484975098L;

	private String countryName;
	
	private int completed = 0;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	public String getPermissionsString() {
		if (this.isTypeOne() && this.isTypeTwo()) {
			return "A, B";
		} else {
			if (this.isTypeOne()) {
				return "A";
			} else {
				if (this.isTypeTwo()) {
					return "B";
				} else {
					return "";
				}
			}
		}
	}
	
	public String getSystemPermissionsString() {
		StringBuffer result = new StringBuffer();
		boolean insertSeparator = false;
		if (this.isAdministrator()) {
			result.append(ApplicationResources.getMessage("admnistrator"));
			insertSeparator = true;
		}
		if (this.isModerator()) {
			if (insertSeparator) {
				result.append(", ");
			}
			result.append(ApplicationResources.getMessage("moderator"));
			insertSeparator = true;
		}
		if (this.isDesigner()) {
			if (insertSeparator) {
				result.append(", ");
			}
			result.append(ApplicationResources.getMessage("designer"));
			insertSeparator = true;
		}
		return result.toString();
	}
	
	@Override
	public void setPassword(String password) {
		super.setPassword(password);
		completed = completed + 1;
		this.cleanPasswordIfNecesary();
	}
	
	private void cleanPasswordIfNecesary() {
		if (completed == 2) {
			if(!this.isTemporaryPassword()) {
				super.setPassword("");
			}
		}
	}

	@Override
	public void setTemporaryPassword(boolean temporaryPassword) {
		super.setTemporaryPassword(temporaryPassword);
		completed = completed + 1;
		this.cleanPasswordIfNecesary();
	}
}
