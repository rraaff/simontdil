package com.tdil.simon.actions.impl.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.CountryValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.CountryUtils;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.Controller;

public class AddCountryAction extends AbstractAction implements TransactionalAction {

	private static final Logger Log = LoggerProvider.getLogger(AddCountryAction.class);
	
	private String name;
	private FileItem fileItem;
	private byte[] flag;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		// Nothing todo
	}
	
	@Override
	public void takeValuesFrom(List<FileItem> fileItems) {
		this.name = Controller.getParameter(fileItems, "name");
		this.fileItem = Controller.getFileItem(fileItems, "flag");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.name = CountryValidation.validateName(this.name, "name", validation);
		this.flag = CountryValidation.validateFlag(this.fileItem, "flag", true, validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Country exists = CountryDAO.getCountry(this.name);
		if (exists != null) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_ALREADY_EXISTS));
		}
		Country country = new Country();
		country.setName(this.name);
		country.setFlag(this.flag);
		country.setDeleted(false);
		int countryOid = CountryDAO.insertCountry(country);
		try {
			CountryUtils.writeFlagToDisk(countryOid, this.flag);
		} catch (IOException e) {
			Log.error(e.getMessage(), e);
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_FLAG_CANNOT_BE_WRITTEN));
		}
	}
	
}
