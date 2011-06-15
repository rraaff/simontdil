package com.tdil.simon.data.model;


public class SysProperties extends PersistentObject {

	public static final String MAIL_SERVER = "simon.mailserver";
	
	public static final String SERVER_NAME = "simon.server.name";
	public static final String SERVER_URL = "simon.server.url";
	public static final String SERVER_PATH = "simon.tmp.subpath";
	
	public static final String EXTERNAL_LINK1 = "simon.externalLink1";
	
	private String propKey;
	private String propValue;
	
	public String getPropKey() {
		return propKey;
	}
	public void setPropKey(String propKey) {
		this.propKey = propKey;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	

}
