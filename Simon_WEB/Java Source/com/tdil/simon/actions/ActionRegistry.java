package com.tdil.simon.actions;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tdil.simon.utils.LoggerProvider;

public class ActionRegistry {

	private static Map<String, AbstractAction> actions;
	
	static {
		actions = new ConcurrentHashMap<String, AbstractAction>();
		
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(ActionRegistry.class);
	}
	
	public static AbstractAction getActionFor(String key) {
		AbstractAction result = actions.get(key);
		if (result != null) {
			try {
				return (AbstractAction)result.clone();
			} catch (CloneNotSupportedException e) {
				getLog().error(e.getMessage(), e);
				return null;
			}
		}
		return null;
	}
}
