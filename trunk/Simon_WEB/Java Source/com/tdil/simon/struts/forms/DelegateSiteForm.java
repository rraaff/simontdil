package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class DelegateSiteForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9044576910037537580L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
