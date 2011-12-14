package com.tdil.simon.utils;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.NotificationEmail;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.web.SystemConfig;

public class NewVersionNotification {

	private String versionId;
	
	private Document document;
	private Version version;
	private SystemUser user;
	private List<SystemUser> toNotify;
	
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
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
		this.toNotify = SystemUserDAO.selectDelegateUsersThatCanAccess(this.document);
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
			NotificationEmail notificationEmail = SystemConfig.getMailForNewVersion();
			String body = notificationEmail.getEmailText();
			body = body.replace("{DOCUMENT_TITLE}", document.getTitle());
			body = body.replace("{VERSION_NUMBER}", String.valueOf(version.getNumber()));
			body = body.replace("{VERSION_NAME}", version.getName());
			body = body.replace("{SERVER}", SystemConfig.getServerUrl());
			body = body.replace("{FULLNAME}", user2.getName());
			new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(notificationEmail.getEmailFrom(), user2.getEmail(), notificationEmail.getEmailSubject(), body);
		} catch (Exception e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(NewVersionNotification.class);
	}
	
	
}
