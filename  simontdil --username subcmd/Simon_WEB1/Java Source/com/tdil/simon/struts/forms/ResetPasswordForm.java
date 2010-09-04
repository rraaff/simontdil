package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.response.Warning;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class ResetPasswordForm extends ListForm {

	private static final Logger Log = LoggerProvider.getLogger(ResetPasswordForm.class);
	
	private static final long serialVersionUID = 2554139424680999558L;
	
	private String usersIds[];
	
	private String selectedIds[];

	public void init() throws SQLException {
		setList(SystemUserDAO.selectNotDeletedUsers());
		List ids = new ArrayList(this.getList().size());
		for (Object o : this.getList()) {
			ids.add(String.valueOf(((SystemUser)o).getId()));
		}
		usersIds = (String[])ids.toArray(new String[0]);
		selectedIds = new String[usersIds.length];
	}

	public String[] getUsersIds() {
		return usersIds;
	}

	public void setUsersIds(String[] usersIds) {
		this.usersIds = usersIds;
	}

	public String[] getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String[] selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void resetSelectedPasswords() throws NumberFormatException, SQLException {
		System.out.println(selectedIds);
		Warning warning = new Warning();
		for (String id  : selectedIds) {
			SystemUser toClean = SystemUserDAO.getUser(Integer.valueOf(id));
			if (toClean == null || toClean.isDeleted()) {
//				throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
			}
			String newPassword = SystemUser.generateRandomPassword();
			toClean.setPassword(newPassword);
			toClean.setTemporaryPassword(true);
			toClean.setPasswordResetRequest(false);
			toClean.setDeleted(false);
			SystemUserDAO.updateUser(toClean);
			try {
				EmailUtils.sendPasswordEmail(toClean.getEmail(), toClean.getName(), toClean.getUsername(), newPassword);
			} catch (MessagingException e) {
				Log.error(e.getMessage(), e);
				warning.setFieldWarning(String.valueOf(id), ValidationErrors.EMAIL_FAILED);
			}
		}
//		if (warning.hasWarning()) {
//			setResponseData(warning);
//		}
	}

}
