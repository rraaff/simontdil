package com.tdil.simon.struts.forms;

import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.model.SystemUser;

public class ObservationForm extends ActionForm {

	
	private static final long serialVersionUID = 44784135736887042L;

	private String paragraphNumber;
	private String paragraphNumberForDiplay;

	private String newParagraph;
	private String paragraphText;
	private String versionId;
	private Date creationDate = new Date();
	private SystemUser user;
	
	public String getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getNewParagraph() {
		return newParagraph;
	}
	public void setNewParagraph(String newParagraph) {
		this.newParagraph = newParagraph;
	}
	public String getParagraphText() {
		return paragraphText;
	}
	public void setParagraphText(String paragraphText) {
		this.paragraphText = paragraphText;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getParagraphNumberForDiplay() {
		return paragraphNumberForDiplay;
	}
	public void setParagraphNumberForDiplay(String paragraphNumberForDiplay) {
		this.paragraphNumberForDiplay = paragraphNumberForDiplay;
	}
}
