package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.SystemUser;

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
