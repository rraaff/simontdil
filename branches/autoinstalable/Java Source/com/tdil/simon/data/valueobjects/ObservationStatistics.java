package com.tdil.simon.data.valueobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tdil.simon.data.model.Country;

public class ObservationStatistics implements Comparable<ObservationStatistics> {

	private int observationId;
	private int observationNumber;
	private Date creationDate;
	private String formattedDate;
	
	private List<ReadDownloadObservationStat> stats = new ArrayList<ReadDownloadObservationStat>();

	
	public int compareTo(ObservationStatistics o) {
		return Integer.valueOf(observationId).compareTo(o.getObservationId());
	}
	
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

	public List<ReadDownloadObservationStat> getStats() {
		return stats;
	}

	public void setStats(List<ReadDownloadObservationStat> stats) {
		this.stats = stats;
	}

	public void addStat(ReadDownloadObservationStat readDownloadObservationStat) {
		this.getStats().add(readDownloadObservationStat);
	}

	public void completeStatsWith(List<Country> allCountries) {
		List<ReadDownloadObservationStat> result = new ArrayList<ReadDownloadObservationStat>();
		for (Country country : allCountries) {
			result.add(getStatFor(country));
		}
		setStats(result);
	}

	private ReadDownloadObservationStat getStatFor(Country country) {
		for (ReadDownloadObservationStat stat : getStats()) {
			if (stat.getCountryId() == country.getId()) {
				return stat;
			}
		}
		ReadDownloadObservationStat emptyStat = new ReadDownloadObservationStat();
		emptyStat.setCountryId(country.getId());
		emptyStat.setCountryName(country.getName());
		emptyStat.setRead(false);
		emptyStat.setDownload(false);
		return emptyStat;
	}

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	
}
	
	
