package com.tdil.simon.struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class ListForm extends ActionForm {

	private static final long serialVersionUID = -5321349803437449615L;

	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
}
