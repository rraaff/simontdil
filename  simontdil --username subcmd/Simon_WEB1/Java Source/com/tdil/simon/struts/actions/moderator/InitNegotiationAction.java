package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;

public class InitNegotiationAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	public ActionForward basicExecute(ActionMapping mapping, final ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			final Integer id = (Integer) TransactionProvider.executeInTransaction(this, form);
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					CreateDocumentForm createDocumentForm = (CreateDocumentForm) form;
					createDocumentForm.initWith(id);
				}
			});
			return mapping.findForward("success");
		} catch (ValidationException e) {
			ActionErrors errors = new ActionErrors();
			errors.add(e.asMessages());
			addErrors(request, errors);
			return mapping.findForward("failure");
		}
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		Document principal = DocumentDAO.getDocumentForNegotiation();
		if (principal == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.NO_DOCUMENT_FOR_NEGOTIATION));
		}
		Version version = VersionDAO.getVersionForNegotiation(principal.getId());
		if (version == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.NO_VERSION_FOR_NEGOTIATION));
		}
		version.setStatus(Version.IN_NEGOTIATION);
		VersionDAO.updateVersionStatus(version);
		Site site = Site.getDELEGATE_SITE();
		site.setStatus(Site.IN_NEGOTIATION);
		site.setDataId(0);
		SiteDAO.updateSite(site);
		return version.getId();
	}

}
