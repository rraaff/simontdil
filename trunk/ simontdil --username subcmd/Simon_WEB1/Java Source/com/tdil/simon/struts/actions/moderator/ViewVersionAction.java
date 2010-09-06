package com.tdil.simon.struts.actions.moderator;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

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
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.struts.forms.ViewVersionForm;

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
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.initNegotiation"))) {
			// Custom check for shared page and action
			if (UserTypeValidation.isValid(this.getLoggedUser(request), new UserTypeValidation[] { UserTypeValidation.MODERATOR})) {
				TransactionProvider.executeInTransaction(this, form);
				viewForm.init(viewForm.getVersion().getVersion().getId());
				return mapping.findForward("continue");
			} else {
				return mapping.findForward("invalidAction");
			}
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.searchObservations"))) {
			request.setAttribute("versionId", String.valueOf(viewForm.getVersion().getVersion().getId()));
			return mapping.findForward("goToSearchObservations");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadPdf"))) {
			final OutputStream outputStream = response.getOutputStream();
//			response.setContentType("application/pdf");
			VersionVO version = viewForm.getVersion();
			response.setHeader("Content-disposition", "attachment; filename=" + version.getDocument().getTitle() + "_" + version.getVersion().getNumber() + ".pdf");
			TransactionProvider.executeInTransaction(new TransactionalAction() {
					public void executeInTransaction() throws SQLException, ValidationException {
						try {
							ExportVersionAsPDF.exportDocument(viewForm.getUser(), viewForm.getVersion(), outputStream);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (DocumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (SAXException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}	
			);
			return null;
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
		return null;
	}
}