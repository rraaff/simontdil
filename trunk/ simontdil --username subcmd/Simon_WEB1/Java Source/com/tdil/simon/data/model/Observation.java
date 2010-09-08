 package com.tdil.simon.data.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Observation extends PersistentObject {

	private int paragraphId;
	private int userId;
	private boolean addNewParagraph;
	private boolean privateObservation;
	private Date creationDate = new Date();
	private String observationText;
	
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
		DateFormat lastLoginFormat = new SimpleDateFormat("dd/MM HH:mm");
		return lastLoginFormat.format(this.getCreationDate());
	}
	
}
