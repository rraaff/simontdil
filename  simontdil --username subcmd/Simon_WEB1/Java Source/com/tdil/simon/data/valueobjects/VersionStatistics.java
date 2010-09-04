package com.tdil.simon.data.valueobjects;

import java.util.Date;
import java.util.List;

public class VersionStatistics {

	private int versionId;
	private Date creationDate;
	private String docName;
	private String versionName;
	private List<ReadDownloadVersionStat> stats;
	private List<ObservationStatistics> observationsStats;

	public int getVersionId() {
		return versionId;
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

	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	
}
