package com.tdil.simon.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLanguageFilter implements Filter {

	public static final String USER_LANGUAGE = "USER_LANGUAGE";

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
			ServletException {
		if(arg0 instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest)arg0;
			HttpSession session = request.getSession(false);
			if (session != null) {
				ResourceBundleCache.setUserLanguage((String)session.getAttribute(USER_LANGUAGE));
			} else {
				ResourceBundleCache.clearUserLanguage();
			}
		} 
		chain.doFilter(arg0, arg1);
		ResourceBundleCache.clearUserLanguage();
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
