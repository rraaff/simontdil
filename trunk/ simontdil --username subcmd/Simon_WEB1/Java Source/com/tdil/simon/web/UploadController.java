package com.tdil.simon.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.Error;
import com.tdil.simon.actions.response.ResponseType;
import com.tdil.simon.utils.LoggerProvider;

public class UploadController extends HttpServlet {

	private static final long serialVersionUID = -8356531321540585903L;
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(UploadController.class);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		writeResponse(res, new ActionResponse(ResponseType.ERROR, new Error("Solo se admite POST")));
	}

	private void writeResponse(HttpServletResponse res, ActionResponse response) throws IOException {
		res.setContentType("text/xml");
		ServletOutputStream out = res.getOutputStream();
		out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>".getBytes());
		out.write(XMLUtils.asAXML(response).getBytes());	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO validar boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(4000000);
			factory.setRepository(new File("c:/uploaded/"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(5000000);
			List items = upload.parseRequest(req);
			for (Object o : items) {
				FileItem fileItem = (FileItem)o;
				FileOutputStream fout = new FileOutputStream("c:/uploaded/" + System.currentTimeMillis());
				try {
					IOUtils.copy(fileItem.getInputStream(), fout);
				} finally {
					fout.close();
				}
				fileItem.delete();
			}
			writeResponse(res, ActionResponse.newOKResponse());
		} catch (FileUploadException e) {
			getLog().error(e.getMessage(), e);
		}

	}
	
}
