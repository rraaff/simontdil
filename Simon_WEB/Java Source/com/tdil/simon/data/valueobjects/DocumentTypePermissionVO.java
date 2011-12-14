package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.GroupPermission;

public class DocumentTypePermissionVO extends GroupPermission {

	private String documentTypeName;

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

}
