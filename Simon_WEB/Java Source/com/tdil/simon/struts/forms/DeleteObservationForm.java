package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class DeleteObservationForm extends ActionForm {


	private static final long serialVersionUID = 321482406595332000L;

	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
