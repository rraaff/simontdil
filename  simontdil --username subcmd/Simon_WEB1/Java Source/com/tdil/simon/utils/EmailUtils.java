package com.tdil.simon.utils;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.struts.forms.ObservationForm;
import com.tdil.simon.web.SystemConfig;

public class EmailUtils {

	public static void sendPasswordEmail(String email, String fullName, String username, String password) throws MessagingException {
		String body = SystemConfig.getMailBodyForNewPassword();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);
		body = body.replace("{PASSWORD}", password);
		body = body.replace("{SERVER}", SystemConfig.getServerUrl());
		new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(SystemConfig.getMailFromForNewPassword(), email, SystemConfig.getMailSubjectForNewPassword(), body);
	}
	
	public static void sendAdminEmailUserRequestPasswordReset(String fullName, String username) throws MessagingException {
		String body = SystemConfig.getMailBodyForPasswordReset();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);
		body = body.replace("{SERVER}", SystemConfig.getServerUrl());
		new SendMail(SystemConfig.getMailServer()).sendCustomizedHtmlMail(SystemConfig.getMailFromForPasswordReset(), SystemConfig.getMailToForPasswordReset(), SystemConfig.getMailSubjectForPasswordReset(), body);
	}

	public static void sendNewObservationEmail(final ObservationForm observationForm) {
		new Thread() {
			@Override
			public void run() {
				final NewObservationNotification notification = new NewObservationNotification();
				notification.setUser(observationForm.getUser());
				notification.setVersionId(observationForm.getVersionId());
				notification.setParagraphNumber(observationForm.getParagraphNumber());
				try {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							notification.init();
							notification.setCreationDate(observationForm.getCreationDate());
						}
					});
					notification.notifyDelegates();
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}.start();	
	}
	
	public static void sendNewConsolidatedVersionEmail(final SystemUser user, final CreateDocumentForm documentForm) {
		new Thread() {
			@Override
			public void run() {
				final NewVersionNotification notification = new NewVersionNotification();
				notification.setUser(user);
				notification.setVersionId(String.valueOf(documentForm.getVersionId()));
				try {
					TransactionProvider.executeInTransaction(new TransactionalAction() {
						public void executeInTransaction() throws SQLException, ValidationException {
							notification.init();
						}
					});
					notification.notifyDelegates();
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}.start();	
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(EmailUtils.class);
	}
}
