package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class ModeratorSiteForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6447199045143285345L;
	
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
