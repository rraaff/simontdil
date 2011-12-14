package com.tdil.simon.data.valueobjects;

import java.util.List;

import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.ReferenceDocument;

public class CategoryVO extends Category {

	private List<ReferenceDocument> documents;

	public List<ReferenceDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ReferenceDocument> documents) {
		this.documents = documents;
	}
}
