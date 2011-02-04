package com.tdil.simon.struts;

import org.apache.struts.util.MessageResources;

public class ApplicationResources {
	
	private static MessageResources mResources = MessageResources.getMessageResources( "com/tdil.simon.struts/ApplicationResource" );
	
	public static String getMessage(String key) {
		return mResources.getMessage(key);
	}
}
