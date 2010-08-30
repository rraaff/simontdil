package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.model.SystemUser;

public class LoggedUserForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5069891012106871934L;
	
	private SystemUser user;

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

}
