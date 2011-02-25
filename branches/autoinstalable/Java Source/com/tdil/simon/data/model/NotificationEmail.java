package com.tdil.simon.data.model;

public class NotificationEmail extends PersistentObject {
	
	// Constants
	public static String newpassword = "newpassword";
	public static String passworreset = "passworreset";
	public static String newversion = "newversion";
	public static String newobservation = "newobservation";

	private String emailKey;
	private String emailFrom;
	private String emailTo;
	private String emailSubject;
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
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	
}
