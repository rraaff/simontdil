 package com.tdil.simon.data.model;

import java.util.Date;

import com.tdil.simon.web.SystemConfig;

public class Observation extends PersistentObject {

	private int paragraphId;
	private int userId;
	private boolean addNewParagraph;
	private boolean privateObservation;
	private Date creationDate = new Date();
	private String observationText;
	private boolean portuguesTranslation = false;
	
	public boolean isPortuguesTranslation() {
		return portuguesTranslation;
	}
	public void setPortuguesTranslation(boolean portuguesTranslation) {
		this.portuguesTranslation = portuguesTranslation;
	}
	public int getParagraphId() {
		return paragraphId;
	}
	public void setParagraphId(int paragraphId) {
		this.paragraphId = paragraphId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getObservationText() {
		return observationText;
	}
	public void setObservationText(String observationText) {
		this.observationText = observationText;
	}
	public boolean isAddNewParagraph() {
		return addNewParagraph;
	}
	public void setAddNewParagraph(boolean addNewParagraph) {
		this.addNewParagraph = addNewParagraph;
	}
	public boolean isPrivateObservation() {
		return privateObservation;
	}
	public void setPrivateObservation(boolean privateObservation) {
		this.privateObservation = privateObservation;
	}
	
	public String getCreationDateFormatted() {
		return SystemConfig.getDateFormatWithMinutes().format(this.getCreationDate());
	}
	
}
