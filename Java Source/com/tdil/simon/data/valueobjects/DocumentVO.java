package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Document;

public class DocumentVO extends Document {

	private int documentTypeId;
	private String documentTypeName;
	private String documentSubTypeName;
	
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
}
