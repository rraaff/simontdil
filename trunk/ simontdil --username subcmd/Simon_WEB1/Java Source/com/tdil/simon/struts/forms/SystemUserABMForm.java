package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.UserVO;
import com.tdil.simon.utils.EmailUtils;
import com.tdil.simon.utils.LoggerProvider;

public class SystemUserABMForm extends ActionForm {

	private static final long serialVersionUID = 4257776435738129693L;
	private static final Logger Log = LoggerProvider.getLogger(SystemUserABMForm.class);
	private String operation;

	private int id;
	private String name;
	private String username;
	private String email;
	private boolean administrator;
	private boolean moderator;
	private boolean designer;
	
	private List<UserVO> allUsers;
	private Country host;
	
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
//		if (toModify == null) {
//			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
//		}
//		if (!toModify.isDelegate()) {
//			throw new ValidationException(new ValidationError(ValidationErrors.USER_DOES_NOT_EXISTS));
//		}
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
		toModify.setEmail(this.email);
		toModify.setAdministrator(this.isAdministrator());
		toModify.setModerator(this.isModerator());
		toModify.setDesigner(this.isDesigner());
		SystemUserDAO.updateUser(toModify);
	}
	private void addUser() throws SQLException {
		SystemUser exists = SystemUserDAO.getUser(this.username);
//		if (exists != null) {
//			throw new ValidationException(new ValidationError(ValidationErrors.USER_ALREADY_EXISTS));
//		}
//		if (country == null) {
//			throw new ValidationException(new ValidationError(ValidationErrors.COUNTRY_DOES_NOT_EXISTS));
//		}
//		if (this.isCanSign()) {
//			int canSignCount = SystemUserDAO.selectCountCanSignFor(country.getId());
//			if (canSignCount != 0) {
//				throw new ValidationException(new ValidationError(ValidationErrors.ONLY_ONE_DELEGATE_CAN_SIGN));
//			}
//		}
		String generatedPassword = SystemUser.generateRandomPassword();
		SystemUser user = new SystemUser();
		user.setUsername(this.username);
		user.setPassword(generatedPassword);
		user.setName(this.name);
		user.setEmail(this.email);
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
		user.setDeleted(false);
		SystemUserDAO.insertUser(user);
		try {
			EmailUtils.sendPasswordEmail(this.email, this.name, this.username, generatedPassword);
		} catch (MessagingException e) {
			Log.error(e.getMessage(), e);
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
		}
		
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
//		super.reset(mapping, request);
		this.administrator = false;
		this.moderator = false;
		this.designer = false;
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
}
