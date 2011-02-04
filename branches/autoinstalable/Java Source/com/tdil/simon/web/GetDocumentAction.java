package com.tdil.simon.web;

import java.sql.SQLException;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.ReferenceDocument;

public class GetDocumentAction implements TransactionalAction {

	private int id;
	private ReferenceDocument referenceDocument;
	
	public GetDocumentAction(int id) {
		super();
		this.id = id;
	}

	public void executeInTransaction() throws SQLException, ValidationException {
		setReferenceDocument(ReferenceDocumentDAO.getReferenceDocument(this.id));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReferenceDocument getReferenceDocument() {
		return referenceDocument;
	}

	public void setReferenceDocument(ReferenceDocument referenceDocument) {
		this.referenceDocument = referenceDocument;
	}

}
