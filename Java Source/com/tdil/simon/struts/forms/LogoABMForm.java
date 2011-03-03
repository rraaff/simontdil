package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.LogoValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.LogoDAO;
import com.tdil.simon.data.model.Logo;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class LogoABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private String logoKey;
	private FormFile logoData;
	private byte [] logoDataBytes;
	
	private List<Logo> allLogos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.logoKey = null;
		this.logoData = null;
		this.logoDataBytes = null;
	}
	public void init() throws SQLException {
		this.setAllLogos(LogoDAO.selectAllLogo());
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initWith(String key) throws SQLException {
		Logo logo = LogoDAO.getLogo(key);
		if (logo!= null) {
			this.id = logo.getId();
			this.logoKey = key;
		}
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		this.logoDataBytes = LogoValidation.validateLogo(this.logoData, "logo.logo", false, validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	
	public void save() throws SQLException, ValidationException{
		try {
			this.modifyLogo();
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
	private void modifyLogo() throws SQLException, FileNotFoundException, IOException {
		Logo logo = LogoDAO.getLogo(this.getLogoKey());
		logo.setLogoData(this.getLogoDataBytes());
		LogoDAO.updateLogo(logo);
		UploadUtils.uploadFileTo(this.logoData, SystemConfig.getLogoStore() + "/" + this.getLogoKey() + ".png");
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LogoABMForm.class);
	}
	public String getLogoKey() {
		return logoKey;
	}
	public void setLogoKey(String logoKey) {
		this.logoKey = logoKey;
	}
	public FormFile getLogoData() {
		return logoData;
	}
	public void setLogoData(FormFile logoData) {
		this.logoData = logoData;
	}
	public byte[] getLogoDataBytes() {
		return logoDataBytes;
	}
	public void setLogoDataBytes(byte[] logoDataBytes) {
		this.logoDataBytes = logoDataBytes;
	}
	public List<Logo> getAllLogos() {
		return allLogos;
	}
	public void setAllLogos(List<Logo> allLogos) {
		this.allLogos = allLogos;
	}

}
