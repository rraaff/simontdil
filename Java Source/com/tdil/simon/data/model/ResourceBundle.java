package com.tdil.simon.data.model;

public class ResourceBundle extends PersistentObject {

	private String rbContext;
	private String rbKey;
	private String rbValue;
	
	public String getRbContext() {
		return rbContext;
	}
	public void setRbContext(String context) {
		this.rbContext = context;
	}
	public String getRbKey() {
		return rbKey;
	}
	public void setRbKey(String key) {
		this.rbKey = key;
	}
	public String getRbValue() {
		return rbValue;
	}
	public void setRbValue(String value) {
		this.rbValue = value;
	}

}
