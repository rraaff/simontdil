package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.VersionForListVO;

public class ModeratorHome extends ActionForm {

	private static final long serialVersionUID = 742145918256896811L;
	
	private SystemUser loggedUser;
	private VersionForListVO typeOne;
	private VersionForListVO typeTwo;
	
	private List<Document> otherDocumentsList;
	
	private List<ReferenceDocument> referenceList;

	public VersionForListVO getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(VersionForListVO typeOne) {
		this.typeOne = typeOne;
	}

	public VersionForListVO getTypeTwo() {
		return typeTwo;
	}

	public void setTypeTwo(VersionForListVO typeTwo) {
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
		if (this.getLoggedUser().isModerator() || this.getLoggedUser().isAdministrator()) {
			setTypeOne(VersionDAO.selectPrincipalVersion(true, false));
			setTypeTwo(VersionDAO.selectPrincipalVersion(false, true));
			setOtherDocumentsList(DocumentDAO.selectNotDeletedNotPrincipalDocumentsForModeratorHome());
		} else {
			if (this.getLoggedUser().isDelegate()) {
				if (this.getLoggedUser().isTypeOne()) {
					setTypeOne(VersionDAO.selectPrincipalVersion(true, false));
				} else {
					setTypeOne(null);
				}
				if (this.getLoggedUser().isTypeTwo()) {
					setTypeTwo(VersionDAO.selectPrincipalVersion(false, true));
				} else {
					setTypeTwo(null);
				}
				if (this.getLoggedUser().isTypeOne() && this.getLoggedUser().isTypeTwo()) {
					setOtherDocumentsList(DocumentDAO.selectNotDeletedNotPrincipalDocumentsForModeratorHome());
				} else {
					setOtherDocumentsList(DocumentDAO.selectNotDeletedNotPrincipalDocumentsForModeratorHome(this.getLoggedUser().isTypeOne(), this.getLoggedUser().isTypeTwo()));
				}
			} 
		}
		setReferenceList(ReferenceDocumentDAO.selectNotDeletedReferenceDocumentForModeratorHome());
	}
	
	public boolean getHasTypeOne() {
		return this.getTypeOne() != null;
	}

	public boolean getHasTypeTwo() {
		return this.getTypeTwo() != null;
	}

	public SystemUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(SystemUser loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
