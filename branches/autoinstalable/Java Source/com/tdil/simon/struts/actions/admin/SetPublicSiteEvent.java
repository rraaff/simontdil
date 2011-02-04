package com.tdil.simon.struts.actions.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.ModeratorSiteForm;

public class SetPublicSiteEvent extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.ADMINISTRATOR };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModeratorSiteForm moderatorSiteForm = (ModeratorSiteForm) form;
		moderatorSiteForm.setStatus(Site.EVENT);
		Site site = (Site) TransactionProvider.executeInTransaction(this, moderatorSiteForm);
		Site.setPUBLIC_SITE(site);
		return mapping.findForward("home");
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ModeratorSiteForm moderatorSiteForm = (ModeratorSiteForm) form;
		Site site = SiteDAO.getSite(Site.PUBLIC);
		site.setStatus(moderatorSiteForm.getStatus());
		SiteDAO.updateSite(site);
		return site;
	}
}
