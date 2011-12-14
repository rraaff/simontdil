package com.tdil.simon.test.smtp;

/**
 * 
 * @author chuichaqueo
 *
 */
public class Email {

	private String from;
	private String to;
	private String subject;
	private String message;
	
	public Email(){
		
	}
	
	public Email(String from, String to, String subject, String message){
		this.setFrom(from);
		this.setTo(to);
		this.setSubject(subject);
		this.setMessage(message);
	}
	
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
}
