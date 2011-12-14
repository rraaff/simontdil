package com.tdil.simon.web;

import org.apache.log4j.Logger;

import com.tdil.simon.utils.LoggerProvider;

public class JspErrorLog {

	private static Logger getLog() {
		return LoggerProvider.getLogger(JspErrorLog.class);
	}
	
	public static void logJspError(Throwable e) {
		getLog().error(e.getMessage(), e);
	}
}
