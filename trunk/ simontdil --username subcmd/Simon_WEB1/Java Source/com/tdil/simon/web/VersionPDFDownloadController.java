package com.tdil.simon.web;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import com.lowagie.text.DocumentException;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.pdf.ExportVersionAsPDF;
import com.tdil.simon.struts.forms.ViewVersionForm;
import com.tdil.simon.utils.LoggerProvider;

public class VersionPDFDownloadController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;


	private static Logger getLog() {
		return LoggerProvider.getLogger(VersionPDFDownloadController.class);
	}
	
	public void doGet(HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return;
		}
		final SystemUser user = (SystemUser)session.getAttribute("user");
		if (user == null) {
			return;
		}
		ViewVersionForm viewForm = (ViewVersionForm)session.getAttribute("ViewVersion");
		final VersionVO version = viewForm.isShowSpanish() ? viewForm.getVersion() : viewForm.getPortugues();
		if (version == null) {
			return;
		}
		res.setContentType( "application/pdf" );  
		final OutputStream outputStream = res.getOutputStream();

		try {
			TransactionProvider.executeInTransaction(new TransactionalAction() {
				public void executeInTransaction() throws SQLException, ValidationException {
					Integer count = ObservationDAO.countNotDeletedObservationsForVersion(version.getVersion().getId());
					res.setHeader("Content-disposition", "attachment; filename=" + version.getDocument().getTitle() + "-" + version.getVersion().getName() + "-" + version.getVersion().getNumber() + "." + count + ".pdf");
					try {
						ExportVersionAsPDF.exportDocument(user, version, outputStream);
					} catch (IOException e) {
						getLog().error(e.getMessage(), e);
					} catch (ParserConfigurationException e) {
						getLog().error(e.getMessage(), e);
					} catch (DocumentException e) {
						getLog().error(e.getMessage(), e);
					} catch (SAXException e) {
						getLog().error(e.getMessage(), e);
					} catch (Exception e) {
						getLog().error(e.getMessage(), e);
					}
				}
			}	
		);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
