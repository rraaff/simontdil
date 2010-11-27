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

public class SystemUserABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = 4257776435738129693L;
	private String operation;

	private int id;
	private String name;
	private String username;
	private String email;
	private boolean administrator;
	private boolean moderator;
	private boolean designer;
	private boolean assistant;
	private boolean translator;
	
	private List<UserVO> allUsers;
	private Country host;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(SystemUserABMForm.class);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserVO> getAllUsers() {
		return allUsers;
	}
	public void setAllUsers(List<UserVO> allUsers) {
		this.allUsers = allUsers;
	}
	
	public void refreshUserList() throws SQLException {
		setAllUsers(SystemUserDAO.selectSystemUsers());
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
		toModify.setName(this.name);
		toModify.setEmail(this.email.toLowerCase());
		toModify.setAdministrator(this.isAdministrator());
		toModify.setModerator(this.isModerator());
		toModify.setDesigner(this.isDesigner());
		toModify.setAssistant(this.isAssistant());
		toModify.setTranslator(this.isTranslator());
		SystemUserDAO.updateUser(toModify);
	}
	private void addUser() throws SQLException {
		String generatedPassword = SystemUser.generateRandomPassword();
		SystemUser user = new SystemUser();
		user.setUsername(this.username);
		user.setPassword(generatedPassword);
		user.setName(this.name);
		user.setEmail(this.email.toLowerCase());
		user.setCountryId(this.getHost().getId());
		user.setDelegate(false);
		user.setTypeOne(false);
		user.setTypeTwo(false);
		user.setAdministrator(this.isAdministrator());
		user.setModerator(this.isModerator());
		user.setDesigner(this.isDesigner());
		user.setCanSign(false);
		user.setJob("");
//		user.setCountryDesc(this.); TODO hablar con pablo
		user.setPasswordResetRequest(false);
		user.setTemporaryPassword(true);
		user.setCanProposeParagraph(false);
		user.setAssistant(this.isAssistant());
		user.setTranslator(this.isTranslator());
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
		this.email = null;
		this.administrator = false;
		this.moderator = false;
		this.designer = false;
		this.assistant = false;
		this.translator = false;
		
	}
	public void init() throws SQLException {
		this.setHost(CountryDAO.getCountryHost());
		this.refreshUserList();
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
			this.email = systemUser.getEmail();
			this.administrator = systemUser.isAdministrator();
			this.moderator = systemUser.isModerator();
			this.designer = systemUser.isDesigner();
			this.assistant = systemUser.isAssistant();
			this.translator = systemUser.isTranslator();
		}
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.administrator = false;
		this.moderator = false;
		this.designer = false;
		this.assistant = false;
		this.translator = false;
	}
	public void delete(int position) throws SQLException {
		SystemUser systemUser = this.getAllUsers().get(position);
		systemUser.setDeleted(true);
		SystemUserDAO.logicallyDeleteUser(systemUser);
	}
	public void reactivate(int position) throws SQLException {
		SystemUser systemUser = this.getAllUsers().get(position);
		systemUser.setDeleted(false);
		SystemUserDAO.reactivateUser(systemUser);
		
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public boolean isModerator() {
		return moderator;
	}
	public void setModerator(boolean moderator) {
		this.moderator = moderator;
	}
	public boolean isDesigner() {
		return designer;
	}
	public void setDesigner(boolean designer) {
		this.designer = designer;
	}
	public Country getHost() {
		return host;
	}
	public void setHost(Country host) {
		this.host = host;
	}
	
	@Override
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		SystemUserValidation.validateUsername(this.username, "systemuser.username", validation);
		SystemUserValidation.validateName(this.name, "systemuser.name", validation);
		SystemUserValidation.validateEmail(this.email, "systemuser.email", validation);
		if(!this.isAdministrator() && !this.isModerator() && !this.isDesigner() && !this.isAssistant() && !this.isTranslator()) {
			validation.setFieldError("systemuser.administrator", "systemuser.administrator." + ValidationErrors.SELECT_USER_TYPE);

		}
		if (this.isModerator() && this.isAssistant()) {
			validation.setFieldError("systemuser.assistant", "systemuser.administrator." + ValidationErrors.MODERATOR_CANNOT_BE_ASSISTANT);
		}
		if (this.isModerator() && this.isTranslator()) {
			validation.setFieldError("systemuser.translator", "systemuser.administrator." + ValidationErrors.MODERATOR_CANNOT_BE_TRANSLATOR);
		}
		if (this.isAssistant() && this.isTranslator()) {
			validation.setFieldError("systemuser.assistant", "systemuser.administrator." + ValidationErrors.ASSISTANT_CANNOT_BE_TRANSLATOR);
		}
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		SystemUser exists = SystemUserDAO.getUser(this.username);
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("systemuser.username", "systemuser.username." + ValidationErrors.USER_ALREADY_EXISTS);
		}
		SystemUser existsEmail = SystemUserDAO.getUserByEmail(this.email.toLowerCase());
		if (existsEmail != null && existsEmail.getId() != this.getId()) {
			validationError.setFieldError("systemuser.email", "delegate.email." + ValidationErrors.USER_ALREADY_EXISTS);
		}
	}

	public boolean isAssistant() {
		return assistant;
	}

	public void setAssistant(boolean assistant) {
		this.assistant = assistant;
	}

	public boolean isTranslator() {
		return translator;
	}

	public void setTranslator(boolean translator) {
		this.translator = translator;
	}
}
