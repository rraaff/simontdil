package com.tdil.simon.data.model;

import java.sql.SQLException;

import com.tdil.simon.data.ibatis.SiteDAO;

/**
 * 
 * Public: Status: ... TODO
 * Delegate: Status: Normal, Negotiation, Sign
 * @author mgodoy
 *
 */
public class Site extends PersistentObject {

	public static final String PUBLIC = "Public";
	public static final String DELEGATE = "Delegate";
	public static final String MODERATOR = "Moderator";
	
	public static final String IN_NEGOTIATION = "IN_NEGOTIATION";
	public static final String IN_SIGN = "IN_SIGN";
	public static final String NORMAL = "NORMAL";
	public static final String EVENT = "EVENT";
	
	private static Site PUBLIC_SITE;
	private static Site DELEGATE_SITE;
	private static Site MODERATOR_SITE;
	
	private String name;
	private int dataId;
	private String status;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public synchronized static Site getPUBLIC_SITE() throws SQLException {
		if (PUBLIC_SITE == null) {
			PUBLIC_SITE = SiteDAO.getSite(PUBLIC);
		}
		return PUBLIC_SITE;
	}
	
	public static void setPUBLIC_SITE(Site public_site) {
		PUBLIC_SITE = public_site;
	}
	
	public synchronized static Site getDELEGATE_SITE() throws SQLException {
		if (DELEGATE_SITE == null) {
			DELEGATE_SITE = SiteDAO.getSite(DELEGATE);
		}
		return DELEGATE_SITE;
	}
	
	public synchronized static Site getMODERATOR_SITE() throws SQLException {
		if (MODERATOR_SITE == null) {
			MODERATOR_SITE = SiteDAO.getSite(MODERATOR);
		}
		return MODERATOR_SITE;
	}
	public static void setMODERATOR_SITE(Site site) {
		MODERATOR_SITE = site;
	}
	
	public static void setDELEGATE_SITE(Site delegate_site) {
		DELEGATE_SITE = delegate_site;
	}
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	public static void flushCache() {
		setDELEGATE_SITE(null);
		setMODERATOR_SITE(null);
		setPUBLIC_SITE(null);
	}
}
