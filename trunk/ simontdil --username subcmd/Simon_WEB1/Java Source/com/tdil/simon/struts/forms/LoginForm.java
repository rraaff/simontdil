package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;

public class LoginForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7670249948557986182L;

	private String username;
	private String password;
	private String operation;
	private boolean canForce = false;
	
	private boolean redirectToChangePassword = false;
	
	private boolean redirectToNegotiation = false;
	
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
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		username = null;
		password = null;
		redirectToNegotiation = false;
		redirectToChangePassword = false;
		canForce = false;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void init(SystemUser user) throws SQLException {
		if (user.isDelegate()) {
			if (Site.NORMAL.equals(Site.getDELEGATE_SITE().getStatus())) {
				return;
			}
			Document doc = DocumentDAO.getDocumentUnderWork();
			if (doc == null) {
				return;
			}
			if (doc.isTypeOne() && !user.isTypeOne()) {
				return;
			}
			if (doc.isTypeTwo() && !user.isTypeTwo()) {
				return;
			}
			Version version = VersionDAO.getVersionUnderWork();
			if (Version.IN_NEGOTIATION.equals(version.getStatus())) {
				this.redirectToNegotiation = true;
				return;
			}
			if (Version.IN_SIGN.equals(version.getStatus())) {
				this.redirectToNegotiation = true;
				return;
			}
		}
	}
	public boolean isRedirectToNegotiation() {
		return redirectToNegotiation;
	}
	public void setRedirectToNegotiation(boolean redirectToNegotiation) {
		this.redirectToNegotiation = redirectToNegotiation;
	}
	
	public boolean isRedirectToChangePassword() {
		return redirectToChangePassword;
	}
	public void setRedirectToChangePassword(boolean redirectToChangePassword) {
		this.redirectToChangePassword = redirectToChangePassword;
	}
	public boolean isCanForce() {
		return canForce;
	}
	public void setCanForce(boolean canForce) {
		this.canForce = canForce;
	}

}
