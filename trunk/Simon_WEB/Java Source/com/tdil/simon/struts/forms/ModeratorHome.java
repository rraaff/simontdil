package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.ReferenceDocument;

public class ModeratorHome extends ActionForm {

	private static final long serialVersionUID = 742145918256896811L;
	
	private Document typeOne;
	private Document typeTwo;
	
	private List<Document> otherDocumentsList;
	
	private List<ReferenceDocument> referenceList;

	public Document getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(Document typeOne) {
		this.typeOne = typeOne;
	}

	public Document getTypeTwo() {
		return typeTwo;
	}

	public void setTypeTwo(Document typeTwo) {
		this.typeTwo = typeTwo;
	}

	public List<Document> getOtherDocumentsList() {
		return otherDocumentsList;
	}

	public void setOtherDocumentsList(List<Document> otherDocumentsList) {
		this.otherDocumentsList = otherDocumentsList;
	}

	public List<ReferenceDocument> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(List<ReferenceDocument> referenceList) {
		this.referenceList = referenceList;
	}

	public void init() throws SQLException {
		setTypeOne(DocumentDAO.selectPrincipalDocument(true, false));
		setTypeTwo(DocumentDAO.selectPrincipalDocument(false, true));
//		setOtherDocumentsList(DocumentDAO.selectNotDeletedNotPrincipalDocuments());
//		setReferenceList(ReferenceDocumentDAO.selectAllReferenceDocumentNotDeleted());
	}

}
