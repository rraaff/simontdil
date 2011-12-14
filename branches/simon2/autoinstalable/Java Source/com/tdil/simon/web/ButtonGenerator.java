package com.tdil.simon.web;

public class ButtonGenerator {

	public static String getIndexedButton(String form, String context, String key, int index) {
		return getIndexedButton(form, context, key, String.valueOf(index));
	}
	
	public static String getIndexedButton(String form, String context, String key, String index) {
		return getIndexedButtonByKey(form, context, key, index);
	}
	
	public static String getIndexedButtonByKey(String form, String context, String key, int index) {
		return getIndexedButtonByKey(form, context, key, String.valueOf(index));
	}
	
	public static String getIndexedButtonByKey(String form, String context, String key, String index) {
		String rb = ResourceBundleCache.get(context, key);
		StringBuffer onclick= new StringBuffer();
		onclick.append("doIndexedOperationSubmit('");
		onclick.append(form);
		onclick.append("','");
		onclick.append(context + "-" + key);
		onclick.append("','");
		onclick.append(index);
		onclick.append("')");
		
		StringBuffer button = new StringBuffer();
		button.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" onclick=\"");
		button.append(onclick.toString());
		button.append("\">");
		button.append("<tr>");
		button.append("<td width=\"13\" height=\"24\">");
		button.append("<a href=\"javascript:").append(onclick).append("\">");
		button.append("<img src=\"images/buttons/buttonLeft.png\" width=\"13\" height=\"24\" border=\"0\"></a></td>");
		button.append("<td background=\"images/buttons/buttonCenter.gif\" align=\"center\" valign=\"middle\" nowrap><a href=\"javascript:");
		button.append(onclick.toString());
		button.append("\" class=\"newButton\">");
		button.append(rb);
		button.append("</a></td>");
		button.append("<td width=\"13\" height=\"24\">");
		button.append("<a href=\"javascript:").append(onclick).append("\">");
		button.append("<img src=\"images/buttons/buttonRight.png\" width=\"13\" height=\"24\" border=\"0\"></a></td>");
		button.append("</tr>");
		button.append("</table>");
		return button.toString();
	}
	
	// TODO arreglar
	public static String getNoOPButton(String context, String key) {
		String rb = ResourceBundleCache.get(context, key);
		StringBuffer button = new StringBuffer();
		button.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		button.append("<tr>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonLeft.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("<td background=\"images/buttons/buttonCenter.gif\" align=\"center\" valign=\"middle\" nowrap><span class=\"newButton\">");
		button.append(rb);
		button.append("</span></td>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonRight.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("</tr>");
		button.append("</table>");
		return button.toString();
	}
	
	// TODO arreglar
	public static String getNoOPButtonStart() {
		StringBuffer button = new StringBuffer();
		button.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		button.append("<tr>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonLeft.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("<td background=\"images/buttons/buttonCenter.gif\" align=\"center\" valign=\"middle\" nowrap><span class=\"newButton\">");
		return button.toString();
	}
	
	public static String getNoOPButtonMiddle(String context, String key) {
		return ResourceBundleCache.get(context, key);
	}

	public static String getNoOPButtonEnd() {
		StringBuffer button = new StringBuffer();
		button.append("</span></td>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonRight.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("</tr>");
		button.append("</table>");
		return button.toString();
	}
	

}
