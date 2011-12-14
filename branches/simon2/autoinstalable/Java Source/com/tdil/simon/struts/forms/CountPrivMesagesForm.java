package com.tdil.simon.struts.forms;

import org.apache.struts.action.ActionForm;

public class CountPrivMesagesForm extends ActionForm {

	private static final long serialVersionUID = 7923255315624401259L;
	
	private String full;
	private String docNegotiation;
	private String paragraphNegotiation;

	public String getFull() {
		return full;
	}

	public void setFull(String full) {
		this.full = full;
	}

	public String getDocNegotiation() {
		return docNegotiation;
	}

	public void setDocNegotiation(String docNegotiation) {
		this.docNegotiation = docNegotiation;
	}

	public String getParagraphNegotiation() {
		return paragraphNegotiation;
	}

	public void setParagraphNegotiation(String paragraphNegotiation) {
		this.paragraphNegotiation = paragraphNegotiation;
	}

	public boolean mustAnswer() {
		return "true".equals(docNegotiation);
	}

}
