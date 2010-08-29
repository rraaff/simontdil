package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.response.Warning;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class ResetPasswordsAction extends AbstractAction implements TransactionalAction {

	private static final Logger Log = LoggerProvider.getLogger(ResetPasswordsAction.class);
	
	private String[] ids;
	private List<Integer> oids = new ArrayList<Integer>();
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.ids = req.getParameterValues("ids");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		for (String st : ids) {
			if(!StringUtils.isEmptyOrWhitespaceOnly(st)) {
				oids.add(IdValidation.validate(st, "ids", validation));
			}
		}
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse(this.getResponseData());
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Warning warning = new Warning();
		for (Integer oid : oids) {
			SystemUser toClean = SystemUserDAO.getUser(oid);
			if (toClean == null || toClean.isDeleted()) {
				throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
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
				warning.setFieldWarning(String.valueOf(oid), ValidationErrors.EMAIL_FAILED);
			}
		}
		if (warning.hasWarning()) {
			setResponseData(warning);
		}
	}

}
