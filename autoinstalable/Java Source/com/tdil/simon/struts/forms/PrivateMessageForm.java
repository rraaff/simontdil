package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class PrivateMessageForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5643314040628424340L;
	
	private String versionID;
	private int userId;
	private String paragraphNumber;
	private String message;
	
	public PrivateMessageForm(String versionID, String paragraphNumber, String message, int userId) {
		super();
		this.versionID = versionID;
		this.paragraphNumber = paragraphNumber;
		this.message = message;
		this.userId = userId;
	}
	
	public String getVersionID() {
		return versionID;
	}
	public void setVersionID(String versionID) {
		this.versionID = versionID;
	}
	public String getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	

}
