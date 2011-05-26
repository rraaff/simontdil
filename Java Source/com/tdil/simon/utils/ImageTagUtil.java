package com.tdil.simon.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class ImageTagUtil {
	
	  public static String getName(HttpServletRequest request) {
	    String command = null;
	    String buttonValue = null;
	    Enumeration enume = request.getParameterNames();

	    while(enume.hasMoreElements()) {
	      buttonValue = (String)enume.nextElement();
	      if(buttonValue.endsWith(".x")) {
	        command = buttonValue.substring(0,buttonValue.indexOf('.'));
	      }
	    }
	    return command;
	  }
	}