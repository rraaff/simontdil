package com.tdil.simon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.ReferenceDocumentDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.ReferenceDocument;
import com.tdil.simon.data.model.Signature;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;
import com.tdil.simon.utils.UploadUtils;

public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;
	
	private static Object extractFlagSemaphore = new Object();
	private static Object extractRefDocSemaphore = new Object();
	private static Object extractSignatureSemaphore = new Object();

	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadController.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			return;
		}
		if (action.equals("signature")) {
			downloadSignature(req, res);
			return;
		} 
		if (action.equals("flag")) {
			downloadFlag(req, res);
			return;
		}
		HttpSession session = req.getSession(false);
		if (session == null) {
			return;
		}
		SystemUser user = (SystemUser)session.getAttribute("user");
		if (user == null) {
			return;
		}
		if (action.equals("refdoc")) {
			downloadRefDoc(req, res);
		}
	}

	private void downloadFlag(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("fileId"));
		String fileName = getServerFlagFileNameFor("flag", id);
//		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		File flag = new File(fileName);
		if (!flag.exists()) {
			synchronized (extractFlagSemaphore) {				
				try {
					extractFlagFromDB(id);
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}
		if (!StringUtils.isEmpty(fileName)) {
			res.setContentType("image/png");
			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(new File(fileName));
				IOUtils.copy(inputStream, res.getOutputStream());
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}
	
	private void extractFlagFromDB(final Integer id) throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				Country country = CountryDAO.getCountryWithFlag(id);
				UploadUtils.createFile(country.getFlag(), getServerFlagFileNameFor("flag", id));
			}
		});
	}
	
	private void extractReferenceDocFromDB(final Integer id) throws SQLException, ValidationException {
		// TODO
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				ReferenceDocument doc = ReferenceDocumentDAO.getReferenceDocumentWithDocument(id);
				UploadUtils.createFile(doc.getDocument(), getServerRefDocFileNameFor("flag", id));
			}
		});
	}

	private void downloadRefDoc(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("fileId"));
		String fileName = getServerRefDocFileNameFor("flag", id);
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		File flag = new File(fileName);
		if (!flag.exists()) {
			synchronized (extractRefDocSemaphore) {				
				try {
					extractReferenceDocFromDB(id);
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}
		GetDocumentAction getDocumentAction = new GetDocumentAction(id);
		try {
			TransactionProvider.executeInTransaction(getDocumentAction);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
		if (getDocumentAction.getReferenceDocument() != null) {
//			res.setHeader("Cache-Control", "no-store");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			if (!StringUtils.isEmpty(fileName)) {
				res.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(getDocumentAction.getReferenceDocument().getFileName(), "UTF-8"));
				FileInputStream inputStream = null;
				try {
					inputStream = new FileInputStream(new File(fileName));
					IOUtils.copy(inputStream, res.getOutputStream());
				} finally {
					if (inputStream != null) {
						inputStream.close();
					}
				}
			}
		}
	}
	
	private void downloadSignature(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String fileName = getServerSignatureFileNameFor(req.getParameter("signature"));
		File signature = new File(fileName);
		if (!signature.exists()) {
			synchronized (extractSignatureSemaphore) {				
				try {
					
					String file = fileName.substring(fileName.indexOf(SystemConfig.getServer() + '_'), fileName.length() - 4);
					file = file.substring(file.indexOf('_') + 1);
					extractSignatureFromDB(file);
				} catch (SQLException e) {
					getLog().error(e.getMessage(), e);
				} catch (ValidationException e) {
					getLog().error(e.getMessage(), e);
				}
			}
		}
//		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		if (!StringUtils.isEmpty(fileName)) {
			res.setContentType("image/png");
			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(new File(fileName));
				IOUtils.copy(inputStream, res.getOutputStream());
			} finally {
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}
	
	private void extractSignatureFromDB(final String fileName) throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				Signature sign = SignatureDAO.getSignatureWithImage(fileName);
				UploadUtils.createFile(sign.getImage(), getServerSignatureFileNameFor(SystemConfig.getServer() + '_') + fileName + ".png");
			}
		});
	}

	private static String getServerFlagFileNameFor(String action, Integer id) {
		return SystemConfig.getFlagStore() + "/" + String.valueOf(id) + ".png";
	}

	private String getServerRefDocFileNameFor(String action, Integer id) {
		return SystemConfig.getReferenceDocumentStore() + "/" + String.valueOf(id);
	}
	
	private String getServerSignatureFileNameFor(String fileName) {
		return SystemConfig.getSignatureStore() + "/" + fileName;
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
