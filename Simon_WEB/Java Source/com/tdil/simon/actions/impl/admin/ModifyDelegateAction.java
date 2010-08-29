package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;

public class ModifyDelegateAction extends DelegateAction implements TransactionalAction {

	private String id;
	private int oid;
	
	@Override
	protected boolean isAdd() {
		return false;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		super.takeValuesFrom(req);
		this.id = req.getParameter("id");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		super.basicValidate(req, validation);
		this.oid = IdValidation.validate(this.id, "id", validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		SystemUser toModify = SystemUserDAO.getUser(this.oid);
		if (toModify == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
		if (!toModify.isDelegate()) {
			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
		}
		Country country = CountryDAO.getCountry(this.countryIdInt);
		if (country == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
		}
		if (this.canSignBoolean && !toModify.isCanSign()) {
			int canSignCount = SystemUserDAO.selectCountCanSignFor(country.getId());
			if (canSignCount != 0) {
				throw new ValidationException(new ValidationError(ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN));
			}
		}
		toModify.setName(this.name);
		toModify.setEmail(this.email);
		toModify.setCountryId(this.countryIdInt);
		toModify.setTypeOne(this.typeOneBoolean);
		toModify.setTypeTwo(this.typeTwoBoolean);
		toModify.setCanSign(this.canSignBoolean);
		toModify.setJob(this.job);
		toModify.setCountryDesc(this.countryDesc);
		toModify.setDeleted(false);
		SystemUserDAO.updateUser(toModify);
	}
}
