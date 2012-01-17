package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.CountryValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.ResourceBundleDAO;

public class ExportResourceBundleForm extends TransactionalValidationForm {

	private static final long serialVersionUID = -8744930020582842123L;

	private String operation;
	
	private String[] selectedLanguages = new String[0];
	private String[] languages;;

	/* Getter for selectedLanguages */
	public String[] getSelectedLanguages() {
		return this.selectedLanguages;
	}

	/* Setter for selectedLanguages */
	public void setSelectedLanguages(String[] selectedLanguages) {
		this.selectedLanguages = selectedLanguages;
	}

	/* Getter for the languages */
	public String[] getLanguages() {
		return this.languages;
	}

	/* Setter for the languages */
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public void init() throws SQLException {
		List<String> availableLangs = ResourceBundleDAO.selectAvailabeLanguages();
		Collections.sort(availableLangs);
		this.setLanguages(availableLangs.toArray(new String[0]));
	}

	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		if (this.getSelectedLanguages() == null || this.getSelectedLanguages().length == 0) {
			validation.setFieldError("export.languages", "export.languages" + "." + ValidationErrors.CANNOT_BE_EMPTY);
		}
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	

	public List<String> getSelectedLanguagesAsList() {
		ArrayList<String> result = new ArrayList<String>();
		for (String sel : selectedLanguages) {
			result.add(sel);
		}
		return result;
	}

	public String getSelectedLanguagesNames() {
		StringBuffer result = new StringBuffer();
		for (String sel : selectedLanguages) {
			result.append(sel);
			result.append("-");
		}
		return result.toString();
	}
}
