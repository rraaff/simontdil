package com.tdil.simon.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.web.SystemConfig;

public class NewObservationNotification {

	private String versionId;
	private String paragraphNumber;
	
	private Document document;
	private Version version;
	private SystemUser user;
	private Country country;
	private Date creationDate;
	private List<SystemUser> toNotify;
	
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	public void init() throws NumberFormatException, SQLException {
		this.version = VersionDAO.getVersion(Integer.valueOf(this.getVersionId()));
		this.document = DocumentDAO.getDocument(this.version.getDocumentId());
		this.toNotify = SystemUserDAO.selectDelegateUsers(this.document.isTypeOne(), this.document.isTypeTwo());
		this.toNotify.addAll(SystemUserDAO.selectModerators());
		this.country = CountryDAO.getCountry(this.user.getCountryId());
	}
	
	public void notifyDelegates() {
		for (SystemUser user : toNotify) {
			if (user.getId() != this.user.getId()) {
				sendEmail(user);
			}
		}
	}
	private void sendEmail(SystemUser user2) {
		try {
			String body = SystemConfig.getMailBodyForNewObservation();
			body = body.replace("{DOCUMENT_TITLE}", document.getTitle());
			body = body.replace("{VERSION_NUMBER}", String.valueOf(version.getNumber()));
			body = body.replace("{VERSION_NAME}", version.getName() == null ? "" : version.getName());
			body = body.replace("{PARAGRAPH}", getParagraphNumber());
			body = body.replace("{DELEGATE}", getUser().getName());
			body = body.replace("{DELEGATION}", this.country.getName());
			body = body.replace("{SERVER}", SystemConfig.getServerUrl());
			body = body.replace("{FULLNAME}", user2.getName());
			
			String subject = SystemConfig.getMailSubjectForNewObservation();
			subject = subject.replace("{DELEGATION}", this.country.getName());
			subject = subject.replace("{DATE}", SystemConfig.getDateFormatWithMinutes().format(this.getCreationDate()));
			new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(SystemConfig.getMailFromForNewObservation(), user2.getEmail(), subject, body);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(NewObservationNotification.class);
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
