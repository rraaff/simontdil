package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.VersionForListVO;
import com.tdil.simon.database.TransactionProvider;

public class DelegatePopupBean {

	private SystemUser loggedUser;
	
	private List<VersionForListVO> principalVersions;
	
	private List<Document> otherDocumentsList;
	
	private List<ReferenceDocument> referenceList;
	
	public DelegatePopupBean() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				setPrincipalVersions(VersionDAO.selectPrincipalVersions(DelegatePopupBean.this.getLoggedUser()));
				setOtherDocumentsList(DocumentDAO.selectNotDeletedNotPrincipalDocumentsForModeratorHomeNoLimit(DelegatePopupBean.this.getLoggedUser()));
				setReferenceList(ReferenceDocumentDAO.selectNotDeletedReferenceDocumentForModeratorHomeNoLimit(DelegatePopupBean.this.getLoggedUser()));
			}
		});
	}

	public SystemUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(SystemUser loggedUser) {
		this.loggedUser = loggedUser;
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
	
	public List<VersionForListVO> getPrincipalVersions() {
		return principalVersions;
	}

	public void setPrincipalVersions(List<VersionForListVO> principalVersions) {
		this.principalVersions = principalVersions;
	}

}
