package com.tdil.simon.actions.response;

import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.StringUtils;

public class Warning {

	private String warning;
	private Map<String, String> fieldWarnings = new HashMap<String, String>();
	
	public Warning() {
		super();
	}
	
	public Warning(String warning) {
		super();
		setWarning(warning);
	}
	
	public boolean hasWarning() {
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getWarning())) {
			return true;
		}
		return fieldWarnings.size() > 0;
	}
	
	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}
	
	public void setFieldWarning(String field, String error) {
		getFieldWarnings().put(field, error);
	}

	public Map<String, String> getFieldWarnings() {
		return fieldWarnings;
	}

	public void setFieldWarnings(Map<String, String> fieldWarnings) {
		this.fieldWarnings = fieldWarnings;
	}

}
