package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.CountryValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.valueobjects.CountryVO;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class CountryABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private String name;
	private FormFile flag;
	private byte [] flagBytes;
	
	private List<CountryVO> allCountries;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FormFile getFlag() {
		return flag;
	}
	public void setFlag(FormFile myFile) {
		this.flag = myFile;
	}
	public List<CountryVO> getAllCountries() {
		return allCountries;
	}
	public void setAllCountries(List<CountryVO> allCountries) {
		this.allCountries = allCountries;
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.flag = null;
	}
	public void init() throws SQLException {
		this.setAllCountries(CountryDAO.selectAllCountriesVO());
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initWith(int userId) throws SQLException {
		Country country = CountryDAO.getCountry(userId);
		if (country != null) {
			this.id = userId;
			this.name = country.getName();
		}
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		CountryValidation.validateName(this.name, "country.name", validation);
		this.flagBytes = CountryValidation.validateFlag(this.flag, "country.flag", this.id == 0, validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		Country exists = CountryDAO.getCountry(this.name);
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("country.name", "country.name." + ValidationErrors.COUNTRY_ALREADY_EXISTS);
		}
	}
	
	public void save() throws SQLException, ValidationException{
		try {
			if (id == 0) {
				this.addCountry();
			} else {
				this.modifyCountry();
			}
		} catch (FileNotFoundException e) {
			getLog().error(e.getMessage(), e);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			throw new ValidationException(exError);
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
			ValidationError exError = new ValidationError(ValidationErrors.GENERAL_ERROR_TRY_AGAIN);
			throw new ValidationException(exError);
		}
	}
	private void modifyCountry() throws SQLException, FileNotFoundException, IOException {
		Country country = CountryDAO.getCountryWithFlag(this.getId());
		country.setName(this.getName());
		if (this.flagBytes != null) {
			country.setFlag(this.flagBytes);
		}
		CountryDAO.updateCountry(country);
		UploadUtils.uploadFileTo(this.flag, SystemConfig.getFlagStore() + "/" + this.getId() + ".png");
	}
	private void addCountry() throws SQLException, FileNotFoundException, IOException {
		Country country = new Country();
		country.setName(this.getName());
		country.setFlag(this.flagBytes);
		int countryId = CountryDAO.insertCountry(country);
		UploadUtils.uploadFileTo(this.flag, SystemConfig.getFlagStore() + "/" + countryId + ".png");
	}
	public void reactivate(int position) throws SQLException {
		Country country = this.getAllCountries().get(position);
		country.setDeleted(false);
		CountryDAO.updateCountry(country);
	}
	public ValidationError delete(int position) throws SQLException {
		Country country = this.getAllCountries().get(position);
		if (country.isHost()) {
			ValidationError validationError = new ValidationError();
			validationError.setFieldError("country", "country." + ValidationErrors.HOST_COUNTRY_CANNOT_BE_DELETED);
			return validationError;
		}
		country.setDeleted(true);
		CountryDAO.logicallyDeleteCountry(country);
		return null;
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(CountryABMForm.class);
	}
	public Set<Integer> getDeletedCountries() {
		Set<Integer> result = new HashSet<Integer>();
		for (Country c : this.getAllCountries()) {
			if (c.isDeleted()) {
				result.add(c.getId());
			}
		}
		return result;
	}
}
