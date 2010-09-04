package com.tdil.simon.data.valueobjects;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.xhtmlrenderer.css.style.CalculatedStyle;

import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.model.Country;

public class VersionStatistics {

	private int versionId;
	private Date creationDate;
	private String docName;
	private String versionName;
	private List<ReadDownloadVersionStat> stats = new ArrayList<ReadDownloadVersionStat>();
	private List<ObservationStatistics> observationsStats;

	public int getVersionId() {
		return versionId;
	}
	
	public String getFormattedDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM");
		return simpleDateFormat.format(this.getCreationDate());
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public List<ReadDownloadVersionStat> getStats() {
		return stats;
	}

	public void setStats(List<ReadDownloadVersionStat> stats) {
		this.stats = stats;
	}

	public List<ObservationStatistics> getObservationsStats() {
		return observationsStats;
	}

	public void setObservationsStats(List<ObservationStatistics> observationsStats) {
		this.observationsStats = observationsStats;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public void init(List<Country> allCountries) throws SQLException {
		List<ReadDownloadVersionStat> dbStats = DelegateAuditDAO.selectVersionStats(this.getVersionId());
		List<ReadDownloadVersionStat> fullStats = new ArrayList<ReadDownloadVersionStat>();
		for (Country country : allCountries) {
			fullStats.add(getStatFor(country, dbStats));
		}
		setStats(fullStats);
		List<ObservationStatisticsRaw> obList = DelegateAuditDAO.selectObservationStats(this.getVersionId());
		HashMap<Integer, ObservationStatistics> tmp = new HashMap<Integer, ObservationStatistics>();
		int observationNumber = 1;
		for (ObservationStatisticsRaw observationStatisticsRaw : obList) {
			ObservationStatistics observationStatistics = tmp.get(observationStatisticsRaw.getObservationId());
			if (observationStatistics == null) {
				observationStatistics = new ObservationStatistics();
				observationStatistics.setObservationId(observationStatisticsRaw.getObservationId());
				observationStatistics.setCreationDate(observationStatisticsRaw.getCreationDate());
				observationStatistics.setObservationNumber(observationNumber);
				observationNumber = observationNumber + 1;
				tmp.put(observationStatisticsRaw.getObservationId(), observationStatistics);
			}
			observationStatistics.addStat(observationStatisticsRaw.asReadDownloadObservationStat());
		}
		List<ObservationStatistics> obList2 = new ArrayList<ObservationStatistics>();
		obList2.addAll(tmp.values());
		Collections.sort(obList2);
		setObservationsStats(obList2);
		for (ObservationStatistics observationStatistics : obList2) {
			observationStatistics.completeStatsWith(allCountries);
		}
		int index = 1;
		Date lastdate = this.getCreationDate();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM");
		for (ObservationStatistics observationStatistics : this.getObservationsStats()) {
			observationStatistics.setObservationNumber(index);
			if (dayEquals(lastdate, observationStatistics.getCreationDate())) {
				observationStatistics.setFormattedDate("");
			} else {
				observationStatistics.setFormattedDate(simpleDateFormat.format(observationStatistics.getCreationDate()));
				lastdate = observationStatistics.getCreationDate();
			}
			index = index + 1;
		}
	}

	private boolean dayEquals(Date lastdate, Date creationDate2) {
		Calendar first = Calendar.getInstance();
		first.setTime(lastdate);
		Calendar second = Calendar.getInstance();
		second.setTime(creationDate2);
		if (first.get(Calendar.YEAR) != second.get(Calendar.YEAR)) {
			return false;
		}
		if (first.get(Calendar.MONTH) != second.get(Calendar.MONTH)) {
			return false;
		}
		return (first.get(Calendar.DATE) == second.get(Calendar.DATE));
	} 

	private ReadDownloadVersionStat getStatFor(Country country, List<ReadDownloadVersionStat> dbStats) {
		for (ReadDownloadVersionStat readDownloadVersionStat : dbStats) {
			if (readDownloadVersionStat.getCountryId() == country.getId()) {
				return readDownloadVersionStat;
			}
		}
		ReadDownloadVersionStat readDownloadVersionStat = new ReadDownloadVersionStat();
		readDownloadVersionStat.setCountryId(country.getId());
		readDownloadVersionStat.setCountryName(country.getName());
		readDownloadVersionStat.setRead(false);
		readDownloadVersionStat.setDownload(false);
		return readDownloadVersionStat;
	}
	
	
}
