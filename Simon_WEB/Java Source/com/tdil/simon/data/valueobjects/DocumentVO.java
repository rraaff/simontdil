package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Document;

public class DocumentVO extends Document {

	private int documentTypeId;
	private String documentTypeName;
	private String documentSubTypeName;
	
	private int lastVersionNumber;
	private String lastVersionName;
	
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentTypeName() {
		return documentTypeName;
	}
	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	public String getDocumentSubTypeName() {
		return documentSubTypeName;
	}
	public void setDocumentSubTypeName(String documentSubTypeName) {
		this.documentSubTypeName = documentSubTypeName;
	}
	public int getLastVersionNumber() {
		return lastVersionNumber;
	}
	public void setLastVersionNumber(int lastVersionNumber) {
		this.lastVersionNumber = lastVersionNumber;
	}
	public String getLastVersionName() {
		return lastVersionName;
	}
	public void setLastVersionName(String lastVersionName) {
		this.lastVersionName = lastVersionName;
	}
}
