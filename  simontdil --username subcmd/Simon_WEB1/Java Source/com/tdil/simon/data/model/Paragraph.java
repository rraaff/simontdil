package com.tdil.simon.data.model;

import com.tdil.simon.struts.ApplicationResources;

public class Paragraph extends PersistentObject {

	public static final int INTRODUCTION_LIMIT = 500;
	
	private int versionId;
	private int paragraphNumber;
	private String paragraphText;
	
	private int versionNumber = 1;
	
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	
	public String getParagraphNumberForDiplayAndDiscriminator() {
		return GetParagraphNumberForDisplayAndDiscriminator(this.getParagraphNumber());
	}
	
	public int getParagraphNumberForDisplay() {
		return GetParagraphNumberForDisplay(this.getParagraphNumber());
	}
	
	public static String GetParagraphNumberForDisplayAndDiscriminator(int paragraphNumber) {
		if (paragraphNumber < INTRODUCTION_LIMIT) {
			return GetParagraphNumberForDisplay(paragraphNumber) + " - " + ApplicationResources.getMessage("Paragraph.introduction");
		} else {
			return GetParagraphNumberForDisplay(paragraphNumber) + " - " + ApplicationResources.getMessage("Paragraph.paragraph");
		}
	}
	
	public static int GetParagraphNumberForDisplay(int paragraphNumber) {
		if (paragraphNumber < INTRODUCTION_LIMIT) {
			return paragraphNumber;
		} else {
			return paragraphNumber - INTRODUCTION_LIMIT;
		}
	}
	
	public int getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(int paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getParagraphText() {
		return paragraphText;
	}
	public void setParagraphText(String paragraphText) {
		this.paragraphText = paragraphText;
	}
	public int getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
	public boolean belongsToIntroduction() {
		return this.getParagraphNumber() < INTRODUCTION_LIMIT;
	}
	
}
