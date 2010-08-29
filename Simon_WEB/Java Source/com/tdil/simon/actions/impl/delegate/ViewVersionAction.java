package com.tdil.simon.actions.impl.delegate;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.database.TransactionProvider;

public class ViewVersionAction extends AbstractAction implements TransactionalAction{

	private String id;
	private int oid;
	
	private SystemUser user;
	
	@Override
	protected ActionResponse basicExecute(HttpServletRequest req)
			throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse(this.getResponseData());
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		Version version = VersionDAO.getVersion(this.oid);
		if (version == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.VERSION_DOES_NOT_EXISTS));
		}
		VersionVO versionVO = new VersionVO();
		versionVO.setVersion(version);
		versionVO.setAllVersions(VersionDAO.getAllVersionNumbersFor(version.getDocumentId()));
		versionVO.setParagraphs(ParagraphDAO.selectAllParagraphsFor(this.oid));
		if (this.user.isDelegate()) {
			DelegateAuditDAO.registerAction(this.user.getId(), this.user.getCountryId(), version.getId(), DelegateAudit.VIEW_OBSERVATION);
		}
		this.setResponseData(versionVO);
	}

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
		oid = IdValidation.validate(this.id, "id", validation);
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.DELEGATE;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.id = req.getParameter("id");
		this.user = (SystemUser)req.getSession().getAttribute("user");
	}

}
