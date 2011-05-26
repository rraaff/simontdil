package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.DocumentSubType;

public class DocumentSubTypeVO extends DocumentSubType {

	private int selectedId = 0;
	private String documentTypeName;

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	
	public boolean getSelected() {
		return this.getId() == this.getSelectedId();
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}
	
}
