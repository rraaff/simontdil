package com.tdil.simon.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.apache.log4j.Logger;

import com.tdil.simon.utils.LoggerProvider;

public class LogOnceListener {
	
	private static Map<String, HttpSession> allSessions = new HashMap<String, HttpSession>();
	private static Map<HttpSession, String> sessionToUsernames = new HashMap<HttpSession, String>();
	private static HttpSession moderatorSession;

	public void sessionCreated(HttpSessionEvent event) {
	    
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (allSessions) {
			String username = sessionToUsernames.get(event.getSession());
			sessionToUsernames.remove(event.getSession());
			allSessions.remove(username);
	    }
	}
	
	public static void userHasLogged(String username, boolean moderator, HttpSession session) {
		synchronized (allSessions) {
	    	HttpSession previousSession  = allSessions.get(username);
	    	if (previousSession != null) {
	    		try {
	    			previousSession.invalidate();
				} catch (Exception e) {}
	    	}
	    	allSessions.put(username, session);
	    	sessionToUsernames.put(session, username);
	    	if (moderator) {
	    		if (moderatorSession != null) {
	    			try {
	    				moderatorSession.invalidate();
					} catch (Exception e) {}
	    		}
	    		moderatorSession = session;
	    	}
	    	
	    }
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LogOnceListener.class);
	}
}
