package com.tdil.simon.actions.impl.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;

public class ModifyDocumentAction extends AddDocumentAction {

	private String id;
	private int oid;
	
	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		super.takeValuesFrom(req);
		this.id = req.getParameter("id");
	}
	
	@Override
	protected void basicValidate(HttpServletRequest req, ValidationError validation) {
		super.basicValidate(req, validation);
		oid = IdValidation.validate(this.id, "id", validation);
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Document toModify = DocumentDAO.getDocument(this.oid);
		if (toModify == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.DOCUMENT_DOES_NOT_EXISTS));
		}
		int maxVersionForDocument = VersionDAO.getMaxConsolidatedVersionFor(toModify.getId());
		super.generateVersion(toModify.getId(), maxVersionForDocument + 1);
	}
}
