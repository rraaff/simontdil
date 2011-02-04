package com.tdil.simon.actions.impl.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.database.TransactionProvider;

public class DeleteCountryAction extends AbstractAction implements TransactionalAction {

	private String id;
	private int oid;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.id = req.getParameter("id");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		oid = IdValidation.validate(this.id, "id", validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Country toDelete = CountryDAO.getCountry(oid);
		if (toDelete == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
		}
		if (toDelete.isHost()) {
			throw new ValidationException(new ValidationError(ValidationErrors.HOST_COUNTRY_CANNOT_BE_DELETED));
		}
//		int users = SystemUserDAO.selectCountFor(toDelete.getId());
//		if (users > 0) {
//			throw new ValidationException(new ValidationError("No puede borrarse el pais porque tiene usuarios activos"));
//		} TODO quitar
		CountryDAO.logicallyDeleteCountry(toDelete);
	}
}
