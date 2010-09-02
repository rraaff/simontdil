package com.tdil.simon.struts.actions.moderator;

import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.simon.actions.TransactionalActionWithValue;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.pdf.ExportVersionAsPDF;
import com.tdil.simon.struts.ApplicationResources;
import com.tdil.simon.struts.actions.SimonAction;
import com.tdil.simon.struts.forms.CreateDocumentForm;
import com.tdil.simon.struts.forms.ViewVersionForm;

public class ViewVersionAction extends SimonAction implements TransactionalActionWithValue {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		ViewVersionForm viewForm = (ViewVersionForm)form;
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.initNegotiation"))) {
			TransactionProvider.executeInTransaction(this, form);
			viewForm.init(viewForm.getVersion().getVersion().getId());
			return mapping.findForward("continue");
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.downloadPdf"))) {
			OutputStream outputStream = response.getOutputStream();
			response.setHeader("Content-disposition","attachment; filename=myPDF.pdf");
			ExportVersionAsPDF.exportDocument(viewForm.getVersion(), outputStream);
			return null;
		}
		if (viewForm.getOperation().equals(ApplicationResources.getMessage("viewVersion.listObservations"))) {
			TransactionProvider.executeInTransaction(new TransactionalActionWithValue() {
				public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
					ViewVersionForm viewVersionForm = (ViewVersionForm)form;
					viewVersionForm.setObservations(ObservationDAO.selectNotDeletedObservationsForVersion(viewVersionForm.getVersion().getVersion().getId()));
					return null;
				}
			}, form);
			return mapping.findForward("listObservations");
		}
		return mapping.findForward("continue");
	}
	
	public Object executeInTransaction(ActionForm form) throws SQLException, ValidationException {
		ViewVersionForm viewVersionForm = (ViewVersionForm)form;
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
