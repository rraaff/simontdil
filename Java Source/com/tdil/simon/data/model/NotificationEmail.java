package com.tdil.simon.data.model;

public class NotificationEmail extends PersistentObject {

	private String emailKey;
	private String emailText;
	
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	public String getEmailText() {
		return emailText;
	}
	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
	
}
