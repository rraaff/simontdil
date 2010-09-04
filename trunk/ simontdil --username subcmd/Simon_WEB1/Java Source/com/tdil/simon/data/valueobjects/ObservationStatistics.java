package com.tdil.simon.data.valueobjects;

import java.util.Date;
import java.util.List;

public class ObservationStatistics {

	private int observationId;
	private int observationNumber;
	private Date creationDate;
	
	private List<ReadDownloadVersionStat> stats;

	public int getObservationId() {
		return observationId;
	}

	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}

	public int getObservationNumber() {
		return observationNumber;
	}

	public void setObservationNumber(int observationNumber) {
		this.observationNumber = observationNumber;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<ReadDownloadVersionStat> getStats() {
		return stats;
	}

	public void setStats(List<ReadDownloadVersionStat> stats) {
		this.stats = stats;
	}
	
}
	
	
