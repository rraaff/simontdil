package com.tdil.simon.struts.actions;

import com.tdil.simon.data.model.SystemUser;

public class UnAuthorizedAccessException extends RuntimeException {

	private static final long serialVersionUID = -3711192109876620191L;

	private SystemUser user;
	private Class action;

	public UnAuthorizedAccessException(SystemUser user) {
		super();
		this.user = user;
	}
	
	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public Class getAction() {
		return action;
	}

	public void setAction(Class anAction) {
		this.action = anAction;
	}

}
