package com.tdil.simon.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Signature;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.SignatureRow;

public class DelegateSiteCache {

	public static String delegateSiteStatus;
	public static Document documentUnderWork;
	public static Paragraph negotiatedParagraph;
	public static Observation portuguesParagrah;


	public static List<Paragraph> allParagraph;
	public static VersionVO previousVersion;
	public static Version version;
	
	private static List<SignatureVO> allSignatures;

	public static synchronized void refresh() {
		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					delegateSiteStatus = Site.getDELEGATE_SITE().getStatus();
					Document oldDocumentUnderWork = documentUnderWork;
					if (Site.IN_NEGOTIATION.equals(delegateSiteStatus)) {
						documentUnderWork = DocumentDAO.getDocumentUnderWork();
						negotiatedParagraph = ParagraphDAO.getParagraph(Site.getDELEGATE_SITE().getDataId());
						if (negotiatedParagraph != null) {
							allParagraph = ParagraphDAO.selectNotDeletedParagraphsFor(negotiatedParagraph.getVersionId());
							refreshPortuguesParagraph(negotiatedParagraph);
						} else {
							allParagraph = new ArrayList<Paragraph>();
						}
					}
					if (Site.IN_SIGN.equals(delegateSiteStatus)) {
						documentUnderWork = DocumentDAO.getDocumentUnderWork();
						version = VersionDAO.getVersionUnderWork();
						allSignatures = SignatureDAO.selectSignaturesFor(version.getId());
					}
					String publicSiteStatus = Site.getPUBLIC_SITE().getStatus();
					if (Site.NORMAL.equals(delegateSiteStatus) && Site.SIGN_SHOW.equals(publicSiteStatus)) {
						version = VersionDAO.getVersion(Site.getPUBLIC_SITE().getDataId());
						documentUnderWork = DocumentDAO.getDocument(version.getDocumentId());
						allSignatures = SignatureDAO.selectSignaturesFor(version.getId());
						
					}
					if (oldDocumentUnderWork != documentUnderWork) {
						previousVersion = new VersionVO();
						previousVersion.setVersion(VersionDAO.getVersionForNegotiation(documentUnderWork.getId()));
						previousVersion.setDocument(documentUnderWork);
						previousVersion.setParagraphs(ParagraphDAO.selectNotDeletedParagraphsFor(previousVersion.getVersion().getId()));
					}
				}
			});
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static boolean hasSigned(SystemUser user) {
		if(allSignatures == null) {
			return false;
		}
		for (Signature signature : getAllSignatures()) {
			if (signature.getUserId() == user.getId()) {
				return true;
			}
		}
		return false;
	}
	public static Signature getSignature(SystemUser user) {
		if(allSignatures == null) {
			return null;
		}
		for (Signature signature : getAllSignatures()) {
			if (signature.getUserId() == user.getId()) {
				return signature;
			}
		}
		return null;
	}
	
	public static List<SignatureRow> getSignaturesRows() {
		List<SignatureVO> allSignatures = getAllSignatures();
		List<SignatureRow> result = new ArrayList<SignatureRow>();
		for (int i = 0; i + 1 < allSignatures.size(); i = i + 2) {
			SignatureRow signatureRow = new SignatureRow();
			signatureRow.setLeft(allSignatures.get(i));
			signatureRow.setRight(allSignatures.get(i + 1));
			result.add(signatureRow);
		}
		if (allSignatures.size() % 2 == 1) {
			SignatureRow signatureRow = new SignatureRow();
			signatureRow.setLeft(allSignatures.get(allSignatures.size() - 1));
			result.add(signatureRow);
		}
		return result;
	}
	
	public static boolean shouldProceedToNegotiation(SystemUser user) {
		if (user.isDelegate()) {
			if (!Site.IN_NEGOTIATION.equals(delegateSiteStatus)) {
				return false;
			}
			Document doc = getDocumentUnderWork();
			if (doc == null) {
				return false;
			} else {
				if (doc.isTypeOne() && user.isTypeOne()) {
					return true;
				}
				if (doc.isTypeTwo() && user.isTypeTwo()) {
					return true;
				}
				return false;
			}
		} else {
			return false;
		}
	}
	
	private static void refreshPortuguesParagraph(Paragraph paragraph) throws SQLException {
		portuguesParagrah = ObservationDAO.getPortuguesFor(paragraph.getId());
	}
	
	public static synchronized void setPortuguesParagraphIfCurrent(Observation observation) throws SQLException {
		if (negotiatedParagraph != null && negotiatedParagraph.getId() == observation.getParagraphId()) {
			portuguesParagrah = observation;
		}
	}
	
	public static synchronized void livePreview(String pId, String pText) {
		if (negotiatedParagraph != null && String.valueOf(negotiatedParagraph.getId()).equals(pId)) {
			negotiatedParagraph.setVersionNumber(negotiatedParagraph.getVersionNumber() + 1);
			negotiatedParagraph.setParagraphText(pText);
		}
		
	}
	
	public static List<Paragraph> getFinalParagraphs() {
		try {
			return (List<Paragraph>) TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(ActionForm form)
						throws SQLException, ValidationException {
					return ParagraphDAO.selectNotDeletedParagraphsFor(version.getId());
				}
			}, null);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Paragraph>();
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<Paragraph>();
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(DelegateSiteCache.class);
	}

	public static synchronized String getDelegateSiteStatus() {
		return delegateSiteStatus;
	}

	public static synchronized Document getDocumentUnderWork() {
		return documentUnderWork;
	}

	public static synchronized Paragraph getNegotiatedParagraph() {
		return negotiatedParagraph;
	}
	
	public static synchronized Paragraph getOriginalParagraph() {
		for (Paragraph p : previousVersion.getParagraphs()) {
			if (p.getParagraphNumber() == negotiatedParagraph.getParagraphNumber()) {
				return p;
			}
		}
		return null;
	}

	public static synchronized Version getVersion() {
		return version;
	}

	public static synchronized List<SignatureVO> getAllSignatures() {
		return allSignatures;
	}
	
	public static synchronized List<Paragraph> getAllParagraphs() {
		return allParagraph;
	}
	
	public static synchronized Observation getPortuguesParagrah() {
		return portuguesParagrah;
	}
}
