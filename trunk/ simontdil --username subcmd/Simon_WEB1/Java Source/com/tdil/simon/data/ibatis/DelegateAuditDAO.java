package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.DelegateAudit;

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

	public static void registerAction(int delegateId, int countryId, int objectId, int action) throws SQLException {
		DelegateAudit audit = new DelegateAudit();
		audit.setDelegateId(delegateId);
		audit.setCountryId(countryId);
		audit.setObjectId(objectId);
		audit.setActionId(action);
		insertAudit(audit);
	}

}
