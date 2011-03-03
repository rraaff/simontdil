package com.tdil.simon.web;

import javax.servlet.ServletRequest;

public class ErrorFormatter {

	public static String getErrorsFrom(ServletRequest request) {
		StringBuffer result = new StringBuffer();
		Object o = request.getAttribute(org.apache.struts.Globals.MESSAGE_KEY);
		if (o != null) {
			org.apache.struts.action.ActionMessages ae = (org.apache.struts.action.ActionMessages) o;

			// Get the locale and message resources bundle
			// Loop thru all the labels in the ActionMessage's
			for (java.util.Iterator i = ae.properties(); i.hasNext();) {
				String property = (String) i.next();
				for (java.util.Iterator it = ae.get(property); it.hasNext();) {
					org.apache.struts.action.ActionMessage a = (org.apache.struts.action.ActionMessage) it.next();
					String key = a.getKey();
					result.append(key).append("<br/>");
				}
			}
		}
		return result.toString();
	}
}
