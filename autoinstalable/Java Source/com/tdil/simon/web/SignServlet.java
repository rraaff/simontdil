package com.tdil.simon.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class SignServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3584879932577117741L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InputStream input = req.getInputStream();
		FileOutputStream fout = new FileOutputStream("c:/sign.png");
		try {
			IOUtils.copy(input, fout);
		} catch (Exception e) {
			fout.close();
			e.printStackTrace();
		}
		System.out.println(req.getParameterMap());
		System.out.println("do post");
	}
}
