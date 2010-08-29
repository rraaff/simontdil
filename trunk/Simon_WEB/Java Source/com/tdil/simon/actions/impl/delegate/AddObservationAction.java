package com.tdil.simon.actions.impl.delegate;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ParagraphValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.actions.validations.VersionValidation;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;

public class AddObservationAction extends AbstractAction implements TransactionalAction {

	private String versionId;
	private int versionIdInt;
	private String paragraph;
	private int paragraphInt;
	private String observation;
	private String addNewParagraph;
	private boolean addNewParagraphBoolean;
	
	private SystemUser user;
	
	
	public ActionResponse basicExecute(HttpServletRequest req) throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse();
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Version version = VersionDAO.getVersion(versionIdInt);
		if (version == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.VERSION_DOES_NOT_EXISTS));
		}
		VersionValidation.validateDocumentAndUserType(version, this.user);
		Date today = new Date();
		if (today.after(version.getUpToCommentDate())) {
			throw new ValidationException(new ValidationError(ValidationErrors.VERSION_NOT_COMMENTABLE));
		}
		if (version.getStatus().equals(Version.FINAL)) {
			throw new ValidationException(new ValidationError(ValidationErrors.VERSION_NOT_COMMENTABLE));
		}
		// TODO algn otre estado hace que o sea comentable
		Paragraph paragraph = ParagraphDAO.getParagraph(version, paragraphInt);
		if (paragraph == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.PARAGRAPH_DOES_NOT_EXISTS));
		}
		Observation observation = new Observation();
		observation.setCreationDate(new Date());
		observation.setDeleted(false);
		observation.setObservationText(this.observation);
		observation.setParagraphId(paragraph.getId());
		observation.setUserId(this.user.getId());
		observation.setAddNewParagraph(this.addNewParagraphBoolean);
		int observationId = ObservationDAO.insertObservation(observation);
		if (this.user.isDelegate()) {
			DelegateAuditDAO.registerAction(this.user.getId(), this.user.getCountryId(), observationId, DelegateAudit.VIEW_OBSERVATION);
		}
	}

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
		this.versionIdInt = IdValidation.validate(this.versionId, "versionId", validation);
		this.paragraphInt = ParagraphValidation.validateParagraphNumber(this.paragraph, validation);
		this.addNewParagraphBoolean = FieldValidation.validateBoolean(this.addNewParagraph, validation);
		this.observation = ParagraphValidation.validateObservation(this.observation, "observation", validation);
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.DELEGATE;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.versionId = req.getParameter("versionId");
		this.paragraph = req.getParameter("paragraph");
		this.observation = req.getParameter("observation");
		this.addNewParagraph = req.getParameter("addNewParagraph");
		this.user = (SystemUser)req.getSession().getAttribute("user");
	}

}
