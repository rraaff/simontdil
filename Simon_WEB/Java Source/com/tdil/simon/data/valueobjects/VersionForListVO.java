package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.web.ResourceBundleCache;

public class VersionForListVO extends Version {

	private String documentTitle;
	private String documentDate;
	private String documentTopic;
	private String documentTag1;
	private String documentTag2;
	private int documentRelevance;
	private int observationCount;
	private int newParagraphCount;
	private String documentTypeName;
	private String documentSubTypeName;
	private String orderNumber;
	
	public String getRelevanceStatus() {
		if (getDocumentRelevance() > Document.NO_RELEVANT) {
			return ResourceBundleCache.get("document", "relevante");
		} else {
			return ResourceBundleCache.get("document", "noRelevante");
		}
	}
	
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public int getObservationCount() {
		return observationCount;
	}
	public void setObservationCount(int observationCount) {
		this.observationCount = observationCount;
	}
	
	public String getVersionWithSubversion() {
		return this.getNumber() + "." + this.getObservationCount();
	}
	
	public String getHasObservationText() {
		if (this.getObservationCount() == 0) {
			return ResourceBundleCache.get("general", "no");
		} else {
			return ResourceBundleCache.get("general", "si");
		}
	}
	
	public String getObservationCountText() {
		if (this.getObservationCount() == 0) {
			return "-";
		} else {
			return String.valueOf(this.getObservationCount());
		}
	}
	public int getNewParagraphCount() {
		return newParagraphCount;
	}
	public void setNewParagraphCount(int newParagraphCount) {
		this.newParagraphCount = newParagraphCount;
	}
	
	public String getNewParagraphCountText() {
		if (this.getNewParagraphCount() == 0) {
			return "-";
		} else {
			return String.valueOf(this.getNewParagraphCount());
		}
	}
	public String getDocumentTypeName() {
		return documentTypeName;
	}
	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	public String getDocumentSubTypeName() {
		if (org.apache.commons.lang.StringUtils.isEmpty(documentSubTypeName)) {
			return "-";
		}
		return documentSubTypeName;
	}
	public void setDocumentSubTypeName(String documentSubTypeName) {
		this.documentSubTypeName = documentSubTypeName;
	}

	public int getDocumentRelevance() {
		return documentRelevance;
	}

	public void setDocumentRelevance(int documentRelevance) {
		this.documentRelevance = documentRelevance;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getDocumentTopic() {
		return documentTopic;
	}

	public void setDocumentTopic(String documentTopic) {
		this.documentTopic = documentTopic;
	}

	public String getDocumentTag1() {
		return documentTag1;
	}

	public void setDocumentTag1(String documentTag1) {
		this.documentTag1 = documentTag1;
	}

	public String getDocumentTag2() {
		return documentTag2;
	}

	public void setDocumentTag2(String documentTag2) {
		this.documentTag2 = documentTag2;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
