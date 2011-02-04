package com.tdil.simon.struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.model.Document;

public class SelectPrincipalDocumentForm extends ActionForm {

	private static final long serialVersionUID = 3318556085201444957L;
	private int selectedDocument;
	
	private List<? extends Document> options;
	
	public int getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(int selected) {
		this.selectedDocument = selected;
	}

	public List<? extends Document> getOptions() {
		return options;
	}

	public void setOptions(List<? extends Document> options) {
		this.options = options;
	}
	
}
