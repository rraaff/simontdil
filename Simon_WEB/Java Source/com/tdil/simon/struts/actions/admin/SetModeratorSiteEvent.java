package com.tdil.simon.struts.actions.admin;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.forms.ModeratorSiteForm;

public class SetModeratorSiteEvent extends Action implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModeratorSiteForm moderatorSiteForm = (ModeratorSiteForm)form;
		moderatorSiteForm.setStatus(Site.EVENT);
		Site site = (Site)TransactionProvider.executeInTransaction(this, moderatorSiteForm);
		Site.setMODERATOR_SITE(site);
		return mapping.findForward("home");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ModeratorSiteForm moderatorSiteForm = (ModeratorSiteForm)form;
		Site site = SiteDAO.getSite(Site.MODERATOR);
		site.setStatus(moderatorSiteForm.getStatus());
		SiteDAO.updateSite(site);
		return site;
	}
}
