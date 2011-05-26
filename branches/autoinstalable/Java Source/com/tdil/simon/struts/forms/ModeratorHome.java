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
	private List<VersionForListVO> principalVersions;
	private List<Document> otherDocumentsList;
	private List<ReferenceDocument> referenceList;
	
	public List<VersionForListVO> getPrincipalVersions() {
		return principalVersions;
	}

	public void setPrincipalVersions(List<VersionForListVO> principalVersions) {
		this.principalVersions = principalVersions;
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
			setPrincipalVersions(VersionDAO.selectRelevantVersions());
			setOtherDocumentsList(DocumentDAO.selectNotDeletedNotRelevantDocumentsForModeratorHome());
			setReferenceList(ReferenceDocumentDAO.selectNotDeletedReferenceDocumentForModeratorHome());
		} else {
			if (this.getLoggedUser().isDelegate()) {
				setPrincipalVersions(VersionDAO.selectRelevantVersions(this.getLoggedUser()));
				setOtherDocumentsList(DocumentDAO.selectNotDeletedNotRelevantDocumentsForModeratorHome(this.getLoggedUser()));
				setReferenceList(ReferenceDocumentDAO.selectNotDeletedReferenceDocumentForModeratorHome(this.getLoggedUser()));
			} 
		}
	}

	public SystemUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(SystemUser loggedUser) {
		this.loggedUser = loggedUser;
	}
	
}
