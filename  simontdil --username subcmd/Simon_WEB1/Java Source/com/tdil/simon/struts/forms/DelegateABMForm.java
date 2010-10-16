package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.SystemUserValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.UserVO;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class DelegateABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = 4257776435738129693L;
	private String operation;

	private int id;
	private String name;
	private String username;
	private String countryId;
	private String email;
	private boolean typeOne;
	private boolean typeTwo;
	private boolean canSign;
	private boolean canProposeParagraph;
	private String job;
	
	private List<UserVO> allUsers;
	private List<Country> allCountries;

	private static Logger getLog() {
		return LoggerProvider.getLogger(DelegateABMForm.class);
	}
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isTypeOne() {
		return typeOne;
	}
	public void setTypeOne(boolean typeOne) {
		this.typeOne = typeOne;
	}
	public boolean isTypeTwo() {
		return typeTwo;
	}
	public void setTypeTwo(boolean typeTwo) {
		this.typeTwo = typeTwo;
	}
	public boolean isCanSign() {
		return canSign;
	}
	public void setCanSign(boolean canSign) {
		this.canSign = canSign;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public List<UserVO> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserVO> allUsers) {
		this.allUsers = allUsers;
	}
	
	public void refreshUserList() throws SQLException {
		setAllUsers(SystemUserDAO.selectDelegateUsers());
	}
	
	public void save() throws SQLException {
		if (id == 0) {
			this.addUser();
		} else {
			this.modifyUser();
		}
	}
	
	private void modifyUser() throws SQLException {
		SystemUser toModify = SystemUserDAO.getUser(this.id);
//		if (toModify == null) {
//			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
//		}
//		if (!toModify.isDelegate()) {
//			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
//		}
		Country country = CountryDAO.getCountry(Integer.valueOf(this.countryId));
//		if (country == null) {
//			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
//		}
//		if (this.canSignBoolean && !toModify.isCanSign()) {
//			int canSignCount = SystemUserDAO.selectCountCanSignFor(country.getId());
//			if (canSignCount != 0) {
//				throw new ValidationException(new ValidationError(ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN));
//			}
//		}
		toModify.setName(this.name);
		toModify.setEmail(this.email.toLowerCase());
		toModify.setCountryId(country.getId());
		toModify.setTypeOne(this.isTypeOne());
		toModify.setTypeTwo(this.isTypeTwo());
		toModify.setCanSign(this.isCanSign());
		toModify.setJob(this.job);
		toModify.setCanProposeParagraph(this.isCanProposeParagraph());
//		toModify.setCountryDesc(this.countryDesc);
//		toModify.setDeleted(false);
		SystemUserDAO.updateUser(toModify);
	}
	private void addUser() throws SQLException {
		Country country = CountryDAO.getCountry(Integer.valueOf(this.countryId));
		String generatedPassword = SystemUser.generateRandomPassword();
		SystemUser user = new SystemUser();
		user.setUsername(this.username);
		user.setPassword(generatedPassword);
		user.setName(this.name);
		user.setEmail(this.email.toLowerCase());
		user.setCountryId(country.getId());
		user.setDelegate(true);
		user.setTypeOne(this.isTypeOne());
		user.setTypeTwo(this.isTypeTwo());
		user.setAdministrator(false);
		user.setModerator(false);
		user.setDesigner(false);
		user.setCanSign(this.isCanSign());
		user.setJob(this.job);
//		user.setCountryDesc(this.); TODO hablar con pablo
		user.setPasswordResetRequest(false);
		user.setTemporaryPassword(true);
		user.setCanProposeParagraph(this.isCanProposeParagraph());
		user.setDeleted(false);
		SystemUserDAO.insertUser(user);
		try {
			EmailUtils.sendPasswordEmail(this.email, this.name, this.username, generatedPassword);
		} catch (MessagingException e) {
			getLog().error(e.getMessage(), e);
			// setResponseData(new Warning(ValidationErrors.EMAIL_FAILED)); TODO
		}
	}
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.username = null;
		this.countryId = null;
		this.email = null;
		this.typeOne = false;
		this.typeTwo = false;
		this.canSign = false;
		this.canProposeParagraph = false;
		this.job = null;
	}
	public void init() throws SQLException {
		this.setAllCountries(CountryDAO.selectAllCountries());
		this.refreshUserList();
	}
	
	public List<Country> getAllCountries() {
		return allCountries;
	}
	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public void initWith(int userId) throws SQLException {
		SystemUser systemUser = SystemUserDAO.getUser(userId);
		if (systemUser != null) {
			this.id = userId;
			this.name = systemUser.getName();
			this.username = systemUser.getUsername();
			this.countryId = String.valueOf(systemUser.getCountryId());
			this.email = systemUser.getEmail();
			this.typeOne = systemUser.isTypeOne();
			this.typeTwo = systemUser.isTypeTwo();
			this.canSign = systemUser.isCanSign();
			this.job = systemUser.getJob();
			this.canProposeParagraph = systemUser.isCanProposeParagraph();
		}
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		SystemUserValidation.validateUsername(this.username, "delegate.username", validation);
		SystemUserValidation.validateName(this.name, "delegate.name", validation);
		SystemUserValidation.validateEmail(this.email, "delegate.email", validation);
		SystemUserValidation.validateJob(this.job, "delegate.job", validation);
		if (!this.isTypeOne() && !this.isTypeTwo()) {
			validation.setFieldError("delegate.typeOne", "delegate.typeOne." + ValidationErrors.SELECT_TYPE_ONE_OR_TWO);
		}
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		SystemUser exists = SystemUserDAO.getUser(this.username);
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("delegate.username", "delegate.username." + ValidationErrors.USER_ALREADY_EXISTS);
		}
		SystemUser existsEmail = SystemUserDAO.getUserByEmail(this.email.toLowerCase());
		if (existsEmail != null && existsEmail.getId() != this.getId()) {
			validationError.setFieldError("delegate.email", "delegate.email." + ValidationErrors.USER_ALREADY_EXISTS);
		}
		if (this.isCanSign()) {
			int canSignCount = SystemUserDAO.selectCountCanSignFor(Integer.valueOf(this.getCountryId()), this.isTypeOne(), this.isTypeTwo());
			if (canSignCount != 0 && (exists == null || exists.getId() != this.getId())) {
				validationError.setFieldError("delegate.canSign","delegate.canSign." + ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN);
			}
		}
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		super.reset(mapping, request);
		this.typeOne = false;
		this.typeTwo = false;
		this.canSign = false;
		this.canProposeParagraph = false;
	}
	public void delete(int position) throws SQLException {
		SystemUser systemUser = this.getAllUsers().get(position);
		systemUser.setDeleted(true);
		SystemUserDAO.logicallyDeleteUser(systemUser);
	}
	public ValidationError reactivate(int position) throws SQLException {
		SystemUser systemUser = this.getAllUsers().get(position);
		if (systemUser.isCanSign()) {
			int canSignCount = SystemUserDAO.selectCountCanSignFor(Integer.valueOf(systemUser.getCountryId()), systemUser.isTypeOne(), systemUser.isTypeTwo());
			if (canSignCount != 0) {
				ValidationError validationError = new ValidationError();
				validationError.setFieldError("delegate.canSign","delegate.canSign." + ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN);
				return validationError;
			} else {
				systemUser.setDeleted(false);
				SystemUserDAO.reactivateUser(systemUser);
			}
		} else {
			systemUser.setDeleted(false);
			SystemUserDAO.reactivateUser(systemUser);
		}
		return null;
	}

	public boolean isCanProposeParagraph() {
		return canProposeParagraph;
	}

	public void setCanProposeParagraph(boolean canProposeParagraph) {
		this.canProposeParagraph = canProposeParagraph;
	}
}
