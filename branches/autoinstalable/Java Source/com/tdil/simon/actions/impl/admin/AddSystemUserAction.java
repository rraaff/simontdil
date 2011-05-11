package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.response.Warning;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class AddSystemUserAction extends SystemUserAction implements TransactionalAction {

	@Override
	protected boolean isAdd() {
		return true;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(AddSystemUserAction.class);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse(this.getResponseData());
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser exists = SystemUserDAO.getUser(this.username);
		if (exists != null) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_ALREADY_EXISTS));
		}
		Country country = CountryDAO.getCountryHost();
		if (country == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.HOST_COUNTRY_DOES_NOT_EXISTS));
		}
		String generatedPassword = SystemUser.generateRandomPassword();
		SystemUser user = new SystemUser();
		user.setUsername(this.username);
		user.setPassword(generatedPassword);
		user.setName(this.name);
		user.setEmail(this.email);
		user.setCountryId(country.getId());
		user.setDelegate(false);
//		user.setTypeOne(false);
//		user.setTypeTwo(false);
		user.setAdministrator(this.administratorBoolean);
		user.setModerator(this.moderatorBoolean);
		user.setDesigner(this.designerBoolean);
		user.setCanSign(false);
		user.setJob("");
		user.setCountryDesc("");
		user.setPasswordResetRequest(false);
		user.setTemporaryPassword(true);
		user.setDeleted(false);
		SystemUserDAO.insertUser(user);
		try {
			EmailUtils.sendPasswordEmail(this.email, this.name, this.username, generatedPassword);
		} catch (MessagingException e) {
			getLog().error(e.getMessage(), e);
			setResponseData(new Warning(ValidationErrors.EMAIL_FAILED));
		}
	}
}
