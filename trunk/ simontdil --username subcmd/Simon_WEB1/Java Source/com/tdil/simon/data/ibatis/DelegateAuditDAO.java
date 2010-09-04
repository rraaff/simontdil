package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;

public class DelegateAuditDAO {

	public static List selectAllAudit() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllAudit");
	}
	
	public static Integer insertAudit(DelegateAudit audit) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertAudit", audit);
	}
	
	public static List getLastLogins() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectLastLogins");
	}

	/**
	 * 
	 * @deprecated
	 */
	public static void registerAction(int delegateId, int countryId, int objectId, int action) throws SQLException {
		DelegateAudit audit = new DelegateAudit();
		audit.setDelegateId(delegateId);
		audit.setCountryId(countryId);
		audit.setObjectId(objectId);
		audit.setActionId(action);
		insertAudit(audit);
	}
	
	public static void registerLogin(SystemUser user) throws SQLException {
		if (user.isDelegate()) {
			DelegateAudit audit = new DelegateAudit();
			audit.setDelegateId(user.getId());
			audit.setCountryId(user.getCountryId());
			audit.setObjectId(Site.getDELEGATE_SITE().getId());
			audit.setActionId(DelegateAudit.LOGIN);
			insertAudit(audit);
		}
	}
	
	public static void registerDownloadVersion(SystemUser user, Version version) throws SQLException {
		if (user.isDelegate()) {
			DelegateAudit audit = new DelegateAudit();
			audit.setDelegateId(user.getId());
			audit.setCountryId(user.getCountryId());
			audit.setObjectId(version.getId());
			audit.setActionId(DelegateAudit.DOWNLOAD_VERSION);
			insertAudit(audit);
		}
	}
	
	public static void registerViewVersion(SystemUser user, Version version) throws SQLException {
		if (user.isDelegate()) {
			DelegateAudit audit = new DelegateAudit();
			audit.setDelegateId(user.getId());
			audit.setCountryId(user.getCountryId());
			audit.setObjectId(version.getId());
			audit.setActionId(DelegateAudit.VIEW_VERSION);
			insertAudit(audit);
		}
	}

	public static void registerDownloadObservation(SystemUser user, Observation observation) throws SQLException {
		if (user.isDelegate()) {
			DelegateAudit audit = new DelegateAudit();
			audit.setDelegateId(user.getId());
			audit.setCountryId(user.getCountryId());
			audit.setObjectId(observation.getId());
			audit.setActionId(DelegateAudit.DOWNLOAD_OBSERVATION);
			insertAudit(audit);
		}
	}
	
	public static void registerViewObservation(SystemUser user, Observation observation) throws SQLException {
		if (user.isDelegate()) {
			DelegateAudit audit = new DelegateAudit();
			audit.setDelegateId(user.getId());
			audit.setCountryId(user.getCountryId());
			audit.setObjectId(observation.getId());
			audit.setActionId(DelegateAudit.VIEW_OBSERVATION);
			insertAudit(audit);
		}
	}
}
