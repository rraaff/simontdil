package com.tdil.simon.data.model;

public class TrackChange extends PersistentObject {

	private int paragraphId;
	private String changeText;
	
	public int getParagraphId() {
		return paragraphId;
	}
	public void setParagraphId(int paragraphId) {
		this.paragraphId = paragraphId;
	}
	public String getChangeText() {
		return changeText;
	}
	public void setChangeText(String changeText) {
		this.changeText = changeText;
	}
	
}
