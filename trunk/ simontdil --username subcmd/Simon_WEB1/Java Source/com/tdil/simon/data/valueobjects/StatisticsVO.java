package com.tdil.simon.data.valueobjects;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Country;

public class StatisticsVO {

	private List<Country> allCountries;
	private List<LastLoginVO> lastLogins;
	private List<VersionStatistics> versionStats;

	public List<LastLoginVO> getLastLogins() {
		return lastLogins;
	}

	public void setLastLogins(List<LastLoginVO> lastLogins) {
		this.lastLogins = lastLogins;
	}
	
	public void init() throws SQLException {
		List<Country> allCountries = CountryDAO.selectNotDeletedCountries();
		setAllCountries(allCountries);
		setLastLogins(DelegateAuditDAO.getLastLogins());
		List<VersionForListVO> versions = VersionDAO.selectVersionsVOForList();
		
		List<VersionStatistics> versionStats = new ArrayList<VersionStatistics>();
		for (VersionForListVO versionForListVO : versions) {
			VersionStatistics versionStatistics = new VersionStatistics();
			versionStatistics.setCreationDate(versionForListVO.getCreationDate());
			versionStatistics.setVersionId(versionForListVO.getId());
			versionStatistics.setVersionName(versionForListVO.getName());
			versionStatistics.setDocName(versionForListVO.getDocumentTitle());
			versionStatistics.init(allCountries);
			versionStats.add(versionStatistics);
		}
		setVersionStats(versionStats);
	}

	public List<Country> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}

	public List<VersionStatistics> getVersionStats() {
		return versionStats;
	}

	public void setVersionStats(List<VersionStatistics> versionStats) {
		this.versionStats = versionStats;
	}
}
