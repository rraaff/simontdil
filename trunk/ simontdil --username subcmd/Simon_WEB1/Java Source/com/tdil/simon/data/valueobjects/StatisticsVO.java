package com.tdil.simon.data.valueobjects;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.VersionDAO;

public class StatisticsVO {

	private List<LastLoginVO> lastLogins;
	private List<VersionStatistics> versionStats;

	public List<LastLoginVO> getLastLogins() {
		return lastLogins;
	}

	public void setLastLogins(List<LastLoginVO> lastLogins) {
		this.lastLogins = lastLogins;
	}
	
	public void init() throws SQLException {
		setLastLogins(DelegateAuditDAO.getLastLogins());
		List<VersionForListVO> versions = VersionDAO.selectVersionsVOForList();
		
		List<VersionStatistics> versionStats = new ArrayList<VersionStatistics>();
		for (VersionForListVO versionForListVO : versions) {
			VersionStatistics versionStatistics = new VersionStatistics();
			versionStatistics.setCreationDate(versionForListVO.getCreationDate());
			versionStatistics.setVersionId(versionForListVO.getId());
			versionStatistics.setVersionName(versionForListVO.getName());
			versionStatistics.setDocName(versionForListVO.getDocumentTitle());
			versionStatistics.init();
		}
	}
}
