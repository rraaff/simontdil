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
import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.database.TransactionProvider;
import com.tdil.simon.utils.LoggerProvider;

public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;


	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadController.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			return;
		}
		SystemUser user = (SystemUser)session.getAttribute("user");
		if (user == null) {
			return;
		}
		String action = req.getParameter("action");
		if (action == null) {
			return;
		}
		if (action.equals("flag")) {
			downloadFlag(req, res);
		}
		if (action.equals("refdoc")) {
			downloadRefDoc(req, res);
		}
		if (action.equals("signature")) {
			// TODO seguir por aca
			downloadSignature(req, res);
		}
	}

	private void downloadFlag(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("fileId"));
		String fileName = getServerFlagFileNameFor("flag", id);
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		if (!StringUtils.isEmptyOrWhitespaceOnly(fileName)) {
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
	
	private void downloadRefDoc(HttpServletRequest req, HttpServletResponse res) throws IOException {
		Integer id = Integer.parseInt(req.getParameter("fileId"));
		String fileName = getServerRefDocFileNameFor("flag", id);
		GetDocumentAction getDocumentAction = new GetDocumentAction(id);
		try {
			TransactionProvider.executeInTransaction(getDocumentAction);
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
		if (getDocumentAction.getReferenceDocument() != null) {
			res.setHeader("Cache-Control", "no-store");
			res.setHeader("Pragma", "no-cache");
			res.setDateHeader("Expires", 0);
			if (!StringUtils.isEmptyOrWhitespaceOnly(fileName)) {
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
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		if (!StringUtils.isEmptyOrWhitespaceOnly(fileName)) {
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

	private String getServerFlagFileNameFor(String action, Integer id) {
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
