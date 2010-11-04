package com.tdil.simon.data.model;

import com.tdil.simon.struts.ApplicationResources;

public class Paragraph extends PersistentObject {

	public static final int INTRODUCTION_LIMIT = 500;
	public static final int PARAGRAPH_LIMIT = 1000;
	
	private int versionId;
	private int paragraphNumber;
	private String paragraphText;
	
	private int versionNumber = 1;
	
	private static final String[] introNumbers = new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};	
	
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	
	public static int extractParagraphNunmberFromDisplay(String destination) {
		try {
			// if number
			int dest = Integer.valueOf(destination);
			if (dest > INTRODUCTION_LIMIT * 2) {
				return -1;
			}
			return dest - 1;
		} catch (Exception e) {
			for (int i = 0; i < INTRODUCTION_LIMIT; i++) {
				if (GetParagraphNumberForDisplay(i).equals(destination)) {
					return i;
				}
			}
			return -1;
		}
	}
	
	public String getParagraphNumberForDiplayAndDiscriminator() {
		return GetParagraphNumberForDisplayAndDiscriminator(this.getParagraphNumber() - 1);
	}
	
	public String getParagraphNumberForDisplay() {
		return GetParagraphNumberForDisplay(this.getParagraphNumber() - 1);
	}
	
	public static String GetParagraphNumberForDisplayAndDiscriminator(int paragraphNumber) {
		if (paragraphNumber < INTRODUCTION_LIMIT) {
			return GetParagraphNumberForDisplay(paragraphNumber) + " - " + ApplicationResources.getMessage("Paragraph.introduction");
		} else {
			return GetParagraphNumberForDisplay(paragraphNumber) + " - " + ApplicationResources.getMessage("Paragraph.paragraph");
		}
	}
	
	public static String GetParagraphNumberForDisplay(int paragraphNumber) {
		if (paragraphNumber < INTRODUCTION_LIMIT) {
			if (paragraphNumber < introNumbers.length) {
				return introNumbers[paragraphNumber];
			} else {
				return introNumbers[introNumbers.length - 1] + (paragraphNumber - introNumbers.length);
			}
		} else {
			return String.valueOf(paragraphNumber - INTRODUCTION_LIMIT + 1);
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
