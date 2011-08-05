package com.tdil.simon.test.utils;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.lang.RandomStringUtils;

public class MockHttpServletSession implements HttpSession {

	public Object getAttribute(String arg0) {
		
		return null;
	}

	public Enumeration getAttributeNames() {
		
		return null;
	}

	public long getCreationTime() {
		
		return 0;
	}

	public String getId() {
		return RandomStringUtils.random(5);
	}

	public long getLastAccessedTime() {
		
		return 0;
	}

	public int getMaxInactiveInterval() {
		
		return 0;
	}

	public HttpSessionContext getSessionContext() {
		
		return null;
	}

	public Object getValue(String arg0) {
		
		return null;
	}

	public String[] getValueNames() {
		
		return null;
	}

	public void invalidate() {
		

	}

	public boolean isNew() {
		
		return false;
	}

	public void putValue(String arg0, Object arg1) {
		

	}

	public void removeAttribute(String arg0) {
		

	}

	public void removeValue(String arg0) {
		

	}

	public void setAttribute(String arg0, Object arg1) {
		

	}

	public void setMaxInactiveInterval(int arg0) {
		

	}

	public ServletContext getServletContext() {
		
		return null;
	}

}
