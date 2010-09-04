package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;

public class InitSignAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			TransactionProvider.executeInTransaction(this, form);
			return mapping.findForward("success");
		} catch (ValidationException e) {
			ActionErrors errors = new ActionErrors();
			errors.add(e.asMessages());
			addErrors(request, errors);
			return mapping.findForward("failure");
		}
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		Version version = VersionDAO.getVersionUnderNegotiation();
		if (version == null) {
			throw new ValidationException(new ValidationError(ValidationErrors.NO_VERSION_FOR_SIGN));
		}
		version.setStatus(Version.IN_SIGN);
		VersionDAO.updateVersionStatus(version);
		Site site = Site.getDELEGATE_SITE();
		site.setStatus(Site.IN_SIGN);
		site.setDataId(version.getId());
		SiteDAO.updateSite(site);
		return version.getId();
	}

}
