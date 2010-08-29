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
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.CountryUtils;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.web.Controller;

public class ModifyCountryAction extends AbstractAction implements TransactionalAction {

	private static final Logger Log = LoggerProvider.getLogger(ModifyCountryAction.class);
	
	private String id;
	private int oid;
	private String name;
	private FileItem fileItem;
	private byte[] flag;
//	protected String deleted;
//	protected boolean deletedBoolean;
	
	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.ADMINISTRATOR;
	}
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
	}
	
	@Override
	public void takeValuesFrom(List<FileItem> fileItems) {
		this.id = Controller.getParameter(fileItems, "id");
		this.name = Controller.getParameter(fileItems, "name");
		this.fileItem = Controller.getFileItem(fileItems, "flag");
//		this.deleted = Controller.getParameter(fileItems, "deleted");
	}

	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		this.name = CountryValidation.validateName(this.name, "name", validation);
		this.flag = CountryValidation.validateFlag(this.fileItem, "flag", false, validation);
		this.oid = IdValidation.validate(this.id, "id", validation);
//		this.deletedBoolean = FieldValidation.validateBoolean(this.deleted, validation);
	}
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Country toModify = CountryDAO.getCountry(oid);
		if (toModify == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
		}
		Country exists = CountryDAO.getCountry(name);
		if (exists != null && exists.getId() != this.oid) {
			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_ALREADY_EXISTS));
		}
		toModify.setName(name);
		if (flag != null && flag.length > 0) {
			toModify.setFlag(this.flag);
			try {
				CountryUtils.writeFlagToDisk(toModify.getId(), flag);
			} catch (IOException e) {
				Log.error(e.getMessage(), e);
				throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_FLAG_CANNOT_BE_WRITTEN));
			}
		}
		toModify.setDeleted(false); // Edicion funciona como reactivacion???
		CountryDAO.updateCountry(toModify);
	}
}
