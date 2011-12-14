package com.tdil.simon.web;

public class UserAndCountry {
	
	private String username;
	private int country;
	
	public UserAndCountry(int country, String username) {
		super();
		this.country = country;
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}

}
