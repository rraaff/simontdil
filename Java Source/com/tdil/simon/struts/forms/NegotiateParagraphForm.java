package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class NegotiateParagraphForm extends ActionForm {

	private static final long serialVersionUID = -2368496500882380630L;
	
	private String operation;
	private int paragraphId;
	private String paragraphNumber;
	private String paragraphText;
	
	public int getParagraphId() {
		return paragraphId;
	}
	public void setParagraphId(int paragraphId) {
		this.paragraphId = paragraphId;
	}
	public String getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getParagraphText() {
		return paragraphText;
	}
	public void setParagraphText(String paragraphText) {
		this.paragraphText = paragraphText;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
}
