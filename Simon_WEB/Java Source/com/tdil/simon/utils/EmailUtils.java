package com.tdil.simon.utils;

import javax.mail.MessagingException;

import com.tdil.simon.web.SystemConfig;

public class EmailUtils {

	public static void sendPasswordEmail(String email, String fullName, String username, String password) throws MessagingException {
		String body = SystemConfig.getMailBodyForNewPassword();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);
		body = body.replace("{PASSWORD}", password);
		new SendMail(SystemConfig.getMailServer()).sendCustomizedPlainTextMail(SystemConfig.getMailFromForNewPassword(), email, SystemConfig.getMailSubjectForNewPassword(), body);
	}
	
	public static void sendAdminEmailUserRequestPasswordReset(String fullName, String username) throws MessagingException {
		String body = SystemConfig.getMailBodyForPasswordReset();
		body = body.replace("{FULLNAME}", fullName);
		body = body.replace("{USERNAME}", username);		
		new SendMail(SystemConfig.getMailServer()).sendCustomizedPlainTextMail(SystemConfig.getMailFromForPasswordReset(), SystemConfig.getMailToForPasswordReset(), SystemConfig.getMailSubjectForPasswordReset(), body);
	}
}
