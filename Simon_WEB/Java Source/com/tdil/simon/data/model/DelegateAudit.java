package com.tdil.simon.data.model;

import java.util.Date;

public class DelegateAudit extends PersistentObject {
	
	public static int LOGIN = 0;
	public static int VIEW_VERSION = 1;
	public static int VIEW_OBSERVATION = 2;
	public static int DOWNLOAD_VERSION = 3;
	public static int DOWNLOAD_OBSERVATION = 4;
	public static int LOGOUT = 5; // TODO volarlo si no hace falta 
	
	private int delegateId;
	private int countryId;
	private int objectId;
	private Date creationDate = new Date();
	private int actionId;
	
	public int getDelegateId() {
		return delegateId;
	}
	public void setDelegateId(int delegateId) {
		this.delegateId = delegateId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date date) {
		this.creationDate = date;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public int getObjectId() {
		return objectId;
	}
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

}
