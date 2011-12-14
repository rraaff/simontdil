package com.tdil.simon.struts.forms;

public class LanguageBean {

	private String language;
	
	public LanguageBean(String context) {
		super();
		this.language = context;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String context) {
		this.language = context;
	}
}
