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
		StringBuffer button = new StringBuffer();
		button.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" onclick=\"doIndexedOperationSubmit('");
		button.append(form);
		button.append("','");
		button.append(context + "-" + key);
		button.append("','");
		button.append(index);
		button.append("')\">");
		button.append("<tr>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonLeft.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("<td background=\"images/buttons/buttonCenter.gif\" align=\"center\" valign=\"middle\" nowrap><a href=\"#\" class=\"newButton\">");
		button.append(rb);
		button.append("</a></td>");
		button.append("<td width=\"13\" height=\"24\"><img src=\"images/buttons/buttonRight.png\" width=\"13\" height=\"24\" border=\"0\"></td>");
		button.append("</tr>");
		button.append("</table>");
		return button.toString();
	}
	
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
}
