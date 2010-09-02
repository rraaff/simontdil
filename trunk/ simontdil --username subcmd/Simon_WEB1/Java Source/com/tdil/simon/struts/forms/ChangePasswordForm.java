package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;

public class ChangePasswordForm extends ActionForm {

	
	private static final long serialVersionUID = -1708979250076611667L;

	private String username;
	private String email;
	private String password;
	private String newPassword;
	private String retypeNewPassword;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRetypeNewPassword() {
		return retypeNewPassword;
	}
	public void setRetypeNewPassword(String retypeNewPassword) {
		this.retypeNewPassword = retypeNewPassword;
	}
	
	public boolean changePassword() throws SQLException {
		SystemUser exists = SystemUserDAO.getUserForLogin(this.username);
		if (exists == null) {
			return false;
//			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (!exists.getEmail().equals(this.email)) {
			return false;
//			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		if (!exists.getPassword().equals(this.password)) {
			return false;
//			throw new ValidationException(new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN));
		}
		exists.setPassword(this.newPassword);
		exists.setTemporaryPassword(false);
		SystemUserDAO.updateUser(exists);
		return true;
		
	}
}
