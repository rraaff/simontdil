package com.tdil.simon.data.model;

import java.io.Serializable;
import java.sql.SQLException;

import org.apache.commons.lang.RandomStringUtils;

import com.tdil.simon.utils.PermissionCache;

/**
 * 
 * @author mgodoy
 * 
 */
public class SystemUser extends PersistentObject implements Serializable{

	private static final long serialVersionUID = 5496644201761995044L;
	// Up to 20
	private String username;
	// Up to 20
	private String password;
	// up to 150
	private String name;
	
	private String email;
	
	private String job;
	
	private String countryDesc;
	
	private int countryId;

	private boolean administrator = false;
	private boolean moderator = false;
	private boolean delegate = false;
	
	private boolean canSign = false;
	private boolean designer = false;
	private boolean assistant = false;
	private boolean translator = false;
	
	private boolean passwordResetRequest = false;
	private boolean temporaryPassword = false;
	
	private boolean canProposeParagraph = false;
	
	public static String generateRandomPassword() {
		return RandomStringUtils.randomAlphanumeric(20);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
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

	public boolean isDelegate() {
		return delegate;
	}

	public void setDelegate(boolean delegate) {
		this.delegate = delegate;
	}

	public boolean isCanSign() {
		return canSign;
	}

	public void setCanSign(boolean canSign) {
		this.canSign = canSign;
	}

	public boolean isDesigner() {
		return designer;
	}

	public void setDesigner(boolean designer) {
		this.designer = designer;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCountryDesc() {
		return countryDesc;
	}

	public void setCountryDesc(String countryDesc) {
		this.countryDesc = countryDesc;
	}

	public boolean isPasswordResetRequest() {
		return passwordResetRequest;
	}

	public void setPasswordResetRequest(boolean passwordResetRequest) {
		this.passwordResetRequest = passwordResetRequest;
	}

	public boolean isTemporaryPassword() {
		return temporaryPassword;
	}

	public void setTemporaryPassword(boolean temporaryPassword) {
		this.temporaryPassword = temporaryPassword;
	}

	public boolean isCanProposeParagraph() {
		return canProposeParagraph;
	}

	public void setCanProposeParagraph(boolean canProposeParagraph) {
		this.canProposeParagraph = canProposeParagraph;
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

	public UserPermissionCache getPermissionCache() {
		return PermissionCache.getUserPermissionCache(this);
	}

	public boolean hasPermissionFor(Document document) {
		if (this.isDelegate()) {
			UserPermissionCache userPermissionCache = this.getPermissionCache();
			if (userPermissionCache == null) {
				return false;
			} else {
				return this.getPermissionCache().canAccess(document);
			}
		} else {
			return true;
		}
	}

}
