package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.ReferenceDocument;

public class ReferenceDocumentVO extends ReferenceDocument {

	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
