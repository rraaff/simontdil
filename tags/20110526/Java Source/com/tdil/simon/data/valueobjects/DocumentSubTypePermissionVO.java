package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.GroupPermission;

public class DocumentSubTypePermissionVO extends GroupPermission {

	private String documentTypeName;
	private String documentSubTypeName;

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
