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
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.database.TransactionProvider;

public class DelegateSiteCache {

	public static String delegateSiteStatus;
	public static Document documentUnderWork;
	public static Paragraph negotiatedParagraph;
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
					}
					if (Site.IN_SIGN.endsWith(delegateSiteStatus)) {
						documentUnderWork = DocumentDAO.getDocumentUnderWork();
						version = VersionDAO.getVersionUnderWork();
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
}
