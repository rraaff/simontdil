package com.tdil.simon.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.ActionRegistry;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.Error;
import com.tdil.simon.actions.response.ResponseType;
import com.tdil.simon.utils.LoggerProvider;

public class Controller extends HttpServlet {

	private static final Logger Log = LoggerProvider.getLogger(Controller.class);
	
	private static final long serialVersionUID = -8356531321540585903L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			SystemConfig.init();
		} catch (IOException e) {
			Log.error(e.getMessage(), e);
			throw new ServletException(e);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String act = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		List<FileItem> fileItems = null;
		if (isMultipart) {
			fileItems = extractFiles(req, res);
			if (fileItems == null) {
				return;
			}
			act = getParameter(fileItems, "action");
		} else {
			act = req.getParameter("action");
		}
		AbstractAction action = null;
		if (act != null) {
			action = ActionRegistry.getActionFor(act);
		}
		if (action != null) {
			if (isMultipart) {
				action.takeValuesFrom(fileItems);
			} else {
				action.takeValuesFrom(req);
			}
			ActionResponse response = action.execute(req);
			writeResponse(res, response);
		} else {
			writeResponse(res, new ActionResponse(ResponseType.ERROR, new Error("Accion invalida")));
		}
	}

	public static String getParameter(List<FileItem> fileItems, String string) {
		for (FileItem fi : fileItems) {
			if (string.equals(fi.getFieldName())) {
				return fi.getString();
			}
		}
		return null;
	}
	
	public static FileItem getFileItem(List<FileItem> fileItems, String string) {
		for (FileItem fi : fileItems) {
			if (string.equals(fi.getFieldName())) {
				return fi;
			}
		}
		return null;
	}

	private List<FileItem> extractFiles(HttpServletRequest req, HttpServletResponse res) throws IOException {
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4000000);
			factory.setRepository(new File("c:/uploaded/"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(5000000);
			return upload.parseRequest(req);
		} catch (FileUploadException e) {
			Log.error(e.getMessage(), e);
			writeResponse(res, new ActionResponse(ResponseType.ERROR, new Error(e.getMessage())));
			return null;
		}
		
	}

	private void writeResponse(HttpServletResponse res, ActionResponse response) throws IOException {
		res.setHeader("Cache-Control", "no-store");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		res.setContentType("text/xml");
		ServletOutputStream out = res.getOutputStream();
		out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>".getBytes());
		out.write(XMLUtils.asAXML(response).getBytes());	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	
}
