package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.NotificationEmailValidation;
import com.tdil.simon.data.ibatis.NotificationEmailDAO;
import com.tdil.simon.data.model.NotificationEmail;

public class NotificationEmailABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String emailKey;
	private String emailFrom;
	private String emailTo;
	private String emailSubject;
	private String emailText;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.emailKey = "";
		this.emailFrom = "";
		this.emailTo = "";
		this.emailSubject = "";
		this.emailText = "";
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
	}
	
	public void initWith(String key) throws SQLException {
		NotificationEmail notificationEmail = NotificationEmailDAO.getEmail(key);
		if (notificationEmail != null) {
			this.id = notificationEmail.getId();
			this.emailKey = notificationEmail.getEmailKey();
			this.emailFrom = notificationEmail.getEmailFrom();
			this.emailTo = notificationEmail.getEmailTo();
			this.emailSubject = notificationEmail.getEmailSubject();
			this.emailText = notificationEmail.getEmailText();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		this.modifyEmail();
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		NotificationEmailValidation.validateFrom(this.emailFrom, "email.from", validation);
		NotificationEmailValidation.validateTo(this.emailTo, "email.to", validation);
		NotificationEmailValidation.validateSubject(this.emailSubject, "email.subject", validation);
		NotificationEmailValidation.validateText(this.emailText, "email.text", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	
	private void modifyEmail() throws SQLException {
		NotificationEmail email = NotificationEmailDAO.getEmail(this.emailKey);
		email.setEmailFrom(this.getEmailFrom());
		email.setEmailSubject(this.getEmailSubject());
		email.setEmailText(this.getEmailText());
		email.setEmailTo(this.getEmailTo());
		NotificationEmailDAO.updateEmail(email);
	}
	public String getEmailKey() {
		return emailKey;
	}
	public void setEmailKey(String emailKey) {
		this.emailKey = emailKey;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailText() {
		return emailText;
	}
	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
}
