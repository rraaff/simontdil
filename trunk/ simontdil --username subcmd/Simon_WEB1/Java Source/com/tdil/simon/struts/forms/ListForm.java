package com.tdil.simon.struts.forms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

public class ListForm extends ActionForm {

	private static final long serialVersionUID = -5321349803437449615L;

	private Map<String, String> params = new HashMap<String, String>();
	private List list;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}
	
	public void addParam(String key, String o) {
		params.put(key, o);
	}
	
	public String getParam(String key) {
		return params.get(key);
	}
	
	public void setParam(String key, String o) {
		params.put(key, o);
	}
}
