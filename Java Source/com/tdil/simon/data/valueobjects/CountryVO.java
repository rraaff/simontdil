package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Country;

public class CountryVO extends Country {

	private int userCount;

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}
