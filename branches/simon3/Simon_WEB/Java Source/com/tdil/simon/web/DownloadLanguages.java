package com.tdil.simon.web;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.struts.forms.ExportResourceBundleForm;
import com.tdil.simon.utils.ImportExportResourceBundles;
import com.tdil.simon.utils.LoggerProvider;

public class DownloadLanguages extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;


	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadLanguages.class);
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
		final ExportResourceBundleForm exportForm = (ExportResourceBundleForm)session.getAttribute("exportRBConf");
		res.setContentType("application/xls");
		final OutputStream outputStream = res.getOutputStream();
		DateFormat simpleDateFormat = SystemConfig.getDateFormatWithMinutes();
		String date = simpleDateFormat.format(new Date());
		res.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("export-" + exportForm.getSelectedLanguagesNames() + date + ".xls", "UTF-8"));
		
		try {
			ImportExportResourceBundles.exportResourceBundlesAsExcel(outputStream, exportForm.getSelectedLanguagesAsList());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		}
		outputStream.flush();
		outputStream.close();
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
