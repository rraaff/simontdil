package com.tdil.simon.data.valueobjects;

import java.util.Date;

import com.tdil.simon.web.XMLUtils;

public class LastLoginVO {

	private String country;
	private Date lastLogin;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	@Override
	public String toString() {
		return XMLUtils.asAXML(this);
	}
}
