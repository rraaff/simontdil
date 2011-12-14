package com.tdil.simon.web.beans;

import java.util.List;

import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.SystemUser;

public class DelegateHome {
	
	private SystemUser user;

	private List<ReferenceDocument> referenceDocuments;
	
	public List<ReferenceDocument> getReferenceDocuments() {
		return referenceDocuments;
	}

	public void setReferenceDocuments(List<ReferenceDocument> referenceDocuments) {
		this.referenceDocuments = referenceDocuments;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
}
