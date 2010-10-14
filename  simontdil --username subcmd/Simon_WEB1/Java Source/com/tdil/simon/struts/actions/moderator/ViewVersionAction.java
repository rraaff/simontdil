package com.tdil.simon.struts.actions.moderator;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.pdf.ExportVersionAsPDF;
import com.tdil.simon.pdf.ExportVersionAsRTF;
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
					}
				});
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
			return mapping.findForward("downloadPdf");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadRtf"))) {
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