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
import com.tdil.simon.data.valueobjects.DocumentVO;
import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;
import com.tdil.simon.data.valueobjects.VersionForListVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.tree.CategoryTree;
import com.tdil.simon.struts.forms.tree.CategoryTreeBuilder;
import com.tdil.simon.struts.forms.tree.DocumentTreeBuilder;
import com.tdil.simon.struts.forms.tree.DocumentTypeTree;

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
				setPrincipalVersions(VersionDAO.selectRelevantVersions(DelegatePopupBean.this.getLoggedUser()));
				setOtherDocumentsList(DocumentDAO.selectNotDeletedNotRelevantDocumentsForModeratorHome(DelegatePopupBean.this.getLoggedUser()));
				setReferenceList(ReferenceDocumentDAO.selectNotDeletedReferenceDocumentForModeratorHome(DelegatePopupBean.this.getLoggedUser()));
			}
		});
	}
	
	public boolean getHasPrincipalVersions() {
		return this.getPrincipalVersions() != null && !this.getPrincipalVersions().isEmpty(); 
	}
	
	public List<DocumentTypeTree> getOtherDocumentsTree() {
		DocumentTreeBuilder documentTreeBuilder = new DocumentTreeBuilder();
		for (Document doc : getOtherDocumentsList()) {
			documentTreeBuilder.insert((DocumentVO)doc);
		}
		return documentTreeBuilder.getTrees();
	}
	
	public List<CategoryTree> getReferenceDocumentTree() {
		CategoryTreeBuilder documentTreeBuilder = new CategoryTreeBuilder();
		for (ReferenceDocument doc : getReferenceList()) {
			documentTreeBuilder.insert((ReferenceDocumentVO)doc);
		}
		return documentTreeBuilder.getTrees();
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
