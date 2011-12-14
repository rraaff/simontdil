package com.tdil.simon.test.utils;

import com.tdil.simon.test.smtp.Email;
import com.tdil.simon.test.smtp.SMTPServer;

public class EmailUtils {

	public static Email getEmailTo(String to) {
		Email emailObj = SMTPServer.getEmailTo(to);
		int times = 0;
		while (emailObj == null) {
			times++;
			ThreadUtils.sleep(100);
			emailObj = SMTPServer.getEmailTo(to);
			if (emailObj == null && times > 10) {
				return null;
			}
		}
		return emailObj;
	}
}
