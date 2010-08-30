package com.tdil.simon.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ValidationError;

public class DownloadController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action == null) {
			return;
		}
		if (action.equals("flag")) {
			downloadFlag(req, res);
		}
	}

	private void downloadFlag(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if (!UserTypeValidation.validateLogged(req, new ValidationError())) {
			return;
		}
		Integer id = Integer.parseInt(req.getParameter("fileId"));
		String fileName = getServerFileNameFor("flag", id);
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

	private String getServerFileNameFor(String action, Integer id) {
		return SystemConfig.getFlagStore() + "country" + String.valueOf(id);
	}


	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
