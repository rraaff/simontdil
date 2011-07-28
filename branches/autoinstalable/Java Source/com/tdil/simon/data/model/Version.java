package com.tdil.simon.data.model;

import java.util.Date;

import com.tdil.simon.utils.DateUtils;
import com.tdil.simon.web.ResourceBundleCache;
import com.tdil.simon.web.SystemConfig;

public class Version extends PersistentObject {

	private int documentId;
	private int number;
	private String name;
	private String description;
	private Date creationDate = new Date();
	private Date upToCommentDate;
	// Initial, Consolidated, Final
	public static final String DRAFT = "DRAFT";
	public static final String CONSOLIDATED = "CONSOLIDATED";
	public static final String IN_NEGOTIATION = "IN_NEGOTIATION";
	public static final String IN_SIGN = "IN_SIGN";
	public static final String FINAL = "FINAL";
	private String status;
	private boolean hasDraft = false;
	private boolean commentsEnabled = true;
	private boolean spanishVersion = true;
	
	private String designerText;
	
	public int getDocumentId() {
		return documentId;
	}
	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpToCommentDate() {
		return DateUtils.date2LastMomentOfDate(upToCommentDate);
	}
	public void setUpToCommentDate(Date upToCommentDate) {
		this.upToCommentDate = DateUtils.date2LastMomentOfDate(upToCommentDate);
	}
	
	public String getTranslatedStatus() {
		return ResourceBundleCache.get("version", this.getStatus());
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHasDraft() {
		return hasDraft;
	}
	public void setHasDraft(boolean hasDraft) {
		this.hasDraft = hasDraft;
	}
	
	public String getLimitObservationsString() {
		return SystemConfig.getDateFormat().format(this.getUpToCommentDate());
	}
	public boolean canBeEdited() {
		return !this.isHasDraft() && !this.isInFinalStatus();
	}
	public boolean isInFinalStatus() {
		if (IN_SIGN.equals(this.getStatus())) {
			return true;
		}
		if (FINAL.equals(this.getStatus())) {
			return true;
		}
		return false;
	}
	public boolean isFinal() {
		return FINAL.equals(this.getStatus());
	}
	public boolean isCommentsEnabled() {
		return commentsEnabled;
	}
	public void setCommentsEnabled(boolean commentsEnabled) {
		this.commentsEnabled = commentsEnabled;
	}
	public boolean isSpanishVersion() {
		return spanishVersion;
	}
	public void setSpanishVersion(boolean spanishVersion) {
		this.spanishVersion = spanishVersion;
	}
	public String getDesignerText() {
		return designerText;
	}
	public void setDesignerText(String designerText) {
		this.designerText = designerText;
	}

}
