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

/**
 * @deprecated
 * @author mgodoy
 *
 */
public class AddDelegateAction extends DelegateAction implements TransactionalAction {

	private static Logger getLog() {
		return LoggerProvider.getLogger(AddDelegateAction.class);
	}

	@Override
	protected boolean isAdd() {
		return true;
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
		Country country = CountryDAO.getCountry(this.countryIdInt);
		if (country == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
		}
		if (this.canSignBoolean) {
			int canSignCount = SystemUserDAO.selectCountCanSignFor(country.getId());
			if (canSignCount != 0) {
				throw new ValidationException(new ValidationError(ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN));
			}
		}
		String generatedPassword = SystemUser.generateRandomPassword();
		SystemUser user = new SystemUser();
		user.setUsername(this.username);
		user.setPassword(generatedPassword);
		user.setName(this.name);
		user.setEmail(this.email);
		user.setCountryId(this.countryIdInt);
		user.setDelegate(true);
//		user.setTypeOne(this.typeOneBoolean);
//		user.setTypeTwo(this.typeTwoBoolean);
		user.setAdministrator(false);
		user.setModerator(false);
		user.setDesigner(false);
		user.setCanSign(this.canSignBoolean);
		user.setJob(this.job);
		user.setCountryDesc(this.countryDesc);
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
