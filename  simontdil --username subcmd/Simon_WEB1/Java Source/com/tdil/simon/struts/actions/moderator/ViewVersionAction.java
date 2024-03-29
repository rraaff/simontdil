package com.tdil.simon.struts.actions.moderator;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.struts.forms.ViewVersionForm;
import com.tdil.simon.utils.DelegateSiteCache;
import com.tdil.simon.utils.LoggerProvider;

public class ViewVersionAction extends SimonAction implements TransactionalActionWithValue {

	private static final UserTypeValidation[] permissions = new UserTypeValidation[] { UserTypeValidation.MODERATOR,
			UserTypeValidation.DELEGATE };

	@Override
	protected UserTypeValidation[] getPermissions() {
		return permissions;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SystemUser user = getLoggedUser(request);
		if (user == null) {
			return mapping.findForward("notLogged");
		}
		if (!UserTypeValidation.isValid(user, this.getPermissions())) {
			getLog().fatal("Invalid action for " + this.getClass().getName() + " user " + user.getName());
			return mapping.findForward("invalidAction");
		}
		final ViewVersionForm viewForm = (ViewVersionForm) form;
		if (!viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadPdf"))) {
			if (!viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadRtf"))) {
				if (this.getLoggedUser(request).isDelegate()) {
					if (Site.IN_NEGOTIATION.equals(Site.getDELEGATE_SITE().getStatus())) {
						return mapping.findForward("goToDelegateNegotiation");
					} 
				} 
			}
		}
		return this.basicExecute(mapping, form, request, response);
	}

	@Override
	public ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		final ViewVersionForm viewForm = (ViewVersionForm) form;
		viewForm.setUser(this.getLoggedUser(request));
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.finishSign"))) {
			// Custom check for shared page and action
			if (UserTypeValidation.isValid(this.getLoggedUser(request), new UserTypeValidation[] { UserTypeValidation.MODERATOR})) {
				TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						viewForm.finishSign(viewForm.getVersion().getVersion().getId());
						Site site = Site.getPUBLIC_SITE();
						if (Site.EVENT.equals(site.getStatus())) {
							site.setStatus(Site.SIGN_SHOW);
							SiteDAO.updateSite(site);
						}
					}
				});
				Site.setPUBLIC_SITE(null);
				Site.getPUBLIC_SITE();
				viewForm.init(viewForm.getVersion().getVersion().getId());
				DelegateSiteCache.refresh();
				return mapping.findForward("continue");
			} else {
				return mapping.findForward("invalidAction");
			}
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.initNegotiation"))) {
			// Custom check for shared page and action
			if (UserTypeValidation.isValid(this.getLoggedUser(request), new UserTypeValidation[] { UserTypeValidation.MODERATOR})) {
				Integer newVersionId = (Integer)TransactionProvider.executeInTransaction(this, form);
				DelegateSiteCache.refresh();
				viewForm.init(newVersionId);
				request.setAttribute("id", String.valueOf(newVersionId.intValue()));
				return mapping.findForward("editNegotiation");
			} else {
				return mapping.findForward("invalidAction");
			}
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.searchObservations"))) {
			request.setAttribute("versionId", String.valueOf(viewForm.getVersion().getVersion().getId()));
			return mapping.findForward("goToSearchObservations");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadPdf"))) {
			request.getSession().setAttribute("downloadPDF", viewForm);
			return mapping.findForward("downloadPdf");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.viewSpanishOnly"))) {
			viewForm.setShowSpanish(true);
			return mapping.findForward("viewSingleVersion");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.viewPortuguesOnly"))) {
			viewForm.setShowSpanish(false);
			return mapping.findForward("viewSingleVersion");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadRtf"))) {
			request.getSession().setAttribute("downloadRTF", viewForm);
			return mapping.findForward("downloadRtf");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.listObservations"))) {
			TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
					ViewVersionForm viewVersionForm = (ViewVersionForm) form;
					viewVersionForm.setObservations(ObservationDAO.selectNotDeletedObservationsForVersion(viewVersionForm.getVersion()
							.getVersion().getId()));
					for (Object o : viewVersionForm.getObservations()) {
						Observation observation = (Observation)o;
						DelegateAuditDAO.registerViewObservation(viewVersionForm.getUser(), observation);
					}
					return null;
				}
			}, form);
			return mapping.findForward("listObservations");
		}
		return mapping.findForward("continue");
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ViewVersionAction.class);
	}

	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ViewVersionForm viewVersionForm = (ViewVersionForm) form;
		Version version = viewVersionForm.getVersion().getVersion();
		CreateDocumentForm createDocumentForm = new CreateDocumentForm();
		createDocumentForm.initWith(version.getId());
		createDocumentForm.setVersionStatus(Version.IN_NEGOTIATION);
		createDocumentForm.executeInTransaction(createDocumentForm);
		Site site = Site.getDELEGATE_SITE();
		site.setStatus(Site.IN_NEGOTIATION);
		site.setDataId(0);
		SiteDAO.updateSite(site);
		return createDocumentForm.getVersionId();
	}
}