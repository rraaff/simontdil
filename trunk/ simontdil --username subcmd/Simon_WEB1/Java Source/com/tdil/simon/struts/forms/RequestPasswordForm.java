package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class RequestPasswordForm extends ActionForm {

	private static final Logger Log = LoggerProvider.getLogger(RequestPasswordForm.class);
	
	private static final long serialVersionUID = 7880259578780441517L;

	private String username;
	
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Object requestPassword() throws SQLException {
		SystemUser systemUser = SystemUserDAO.getUserForLogin(this.username);
		if (systemUser != null && systemUser.getEmail().equals(this.email)) {
			systemUser.setPasswordResetRequest(true);
			SystemUserDAO.updateUser(systemUser);
			try {
				EmailUtils.sendAdminEmailUserRequestPasswordReset(systemUser.getName(), systemUser.getPassword());
			} catch (MessagingException e) {
				Log.error(e.getMessage(), e);
			} 
			setUsername("");
			setEmail("");
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
}
