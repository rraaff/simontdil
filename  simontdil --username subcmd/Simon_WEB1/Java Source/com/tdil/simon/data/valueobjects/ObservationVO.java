package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;

public class ObservationVO extends Observation {

	private String username;
	private String name;
	private int countryId;
	private String countryName;
	private int paragraphNumber;
	
	private Paragraph paragraph;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getParagraphNumberForDisplay() {
		return Paragraph.GetParagraphNumberForDisplay(this.getParagraphNumber());
	}
	
	public int getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(int paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public Paragraph getParagraph() {
		return paragraph;
	}
	public void setParagraph(Paragraph paragraph) {
		this.paragraph = paragraph;
	}
	
	public String getParagraphText() {
		if (this.getParagraph() == null) {
			return "";
		} else {
			return this.getParagraph().getParagraphText();
		}
	}
}
