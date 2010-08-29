package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class RequestPasswordForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7880259578780441517L;

	private String username;
	
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
