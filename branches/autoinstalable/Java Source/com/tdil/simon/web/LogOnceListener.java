package com.tdil.simon.web;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.struts.forms.LoginForm;
import com.tdil.simon.utils.LoggerProvider;

public class LogOnceListener implements HttpSessionListener {
	
	private static Map<String, HttpSession> allSessions = new ConcurrentHashMap<String, HttpSession>();
	private static Map<String, UserAndCountry> sessionToUsernames = new ConcurrentHashMap<String, UserAndCountry>();
	private static HttpSession moderatorSession;

	
	public void sessionCreated(HttpSessionEvent event) {
	    System.out.println("session created");
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		synchronized (allSessions) {
			UserAndCountry user = sessionToUsernames.get(event.getSession().getId());
			String username = user != null ? user.getUsername() : null;
			if (event.getSession() == moderatorSession) {
				if (username != null) {
					getLog().fatal("User " + username + " has log out, moderator = true");
				}
				moderatorSession = null;
			} else {
				if (username != null) {
					getLog().fatal("User " + username + " has log out, moderator = false");
				}
			}
			if (event.getSession() != null) {
				sessionToUsernames.remove(event.getSession().getId());
			}
			if (username != null) {
				allSessions.remove(username);
			}
	    }
	}
	
	public static void logout(HttpSession session) {
		synchronized (allSessions) {
			UserAndCountry user = sessionToUsernames.get(session.getId());
			String username = user != null ? user.getUsername() : null;
			if (session == moderatorSession) {
				if (username != null) {
					getLog().fatal("User " + username + " has log out, moderator = true, session = " + session.getId());
				}
				moderatorSession = null;
			} else {
				if (username != null) {
					getLog().fatal("User " + username + " has log out, moderator = false, session = " + session.getId());
				}
			}
			if (session != null) {
				sessionToUsernames.remove(session.getId());
			}
			if (username != null) {
				allSessions.remove(username);
			}
		}
	}
	
	public static void userHasLogged(LoginForm form, String username, int countryId, boolean moderator, HttpSession session, boolean forceLogout) throws ValidationException {
		synchronized (allSessions) {
	    	HttpSession previousSession  = allSessions.get(username);
	    	if (previousSession != null) {
	    		if (forceLogout) {
		    		try {
		    			getLog().fatal("Invalidating previous session for user " + username);
		    			logout(previousSession);
		    			previousSession.invalidate();
					} catch (Exception e) {}
	    		} else {
	    			form.setCanForce(true);
	    			getLog().fatal("Login denied for " + username + " because is logged in");
	    			throw new ValidationException(new ValidationError(ValidationErrors.ALREADY_LOGGED));
	    		}
	    	}
	    	if (moderator) {
	    		if (moderatorSession != null) {
	    			String other = sessionToUsernames.get(moderatorSession.getId()).getUsername();
	    			if (forceLogout) {
		    			try {
		    				getLog().fatal("Invalidating previous session for moderator " + other + " because moderator " + username + " is loging in");
		    				logout(moderatorSession);
		    				moderatorSession.invalidate();
		    			} catch (Exception e) {}
	    			} else {
	    				form.setCanForce(true);
	    				getLog().fatal("Login denied for " + username + " because another moderator ("+ (other == null ? "null" : other)+") is logged in");
	    				throw new ValidationException(new ValidationError(ValidationErrors.MODERATOR_LOGGED));
	    			}
	    		}
	    		moderatorSession = session;
	    	}
	    	allSessions.put(username, session);
	    	sessionToUsernames.put(session.getId(), new UserAndCountry(countryId, username));
	    	getLog().fatal("User " + username + " has log in, moderator = " + String.valueOf(moderator) + " session = " + session.getId());
	    }
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(LogOnceListener.class);
	}

	public static void logoutDelegatesFor(Set<Integer> deletedCountries) {
		synchronized (allSessions) {
			for (Map.Entry<String, UserAndCountry> session : sessionToUsernames.entrySet()) {
				if (deletedCountries.contains(session.getValue().getCountry())) {
					try {
						getLog().fatal("Invalidating previous session for user because country deleted " + session.getValue().getUsername());
						HttpSession ses = allSessions.get(session.getValue().getUsername());
						ses.invalidate(); // TODO falta algo
					} catch (Exception e) {}
				}
			}
	    }
	}
}
