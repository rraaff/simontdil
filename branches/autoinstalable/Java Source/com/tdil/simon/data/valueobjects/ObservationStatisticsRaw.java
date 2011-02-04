package com.tdil.simon.data.valueobjects;

import java.util.Date;

import com.tdil.simon.data.model.DelegateAudit;

public class ObservationStatisticsRaw {

	private int observationId;
	private Date creationDate;
	private int countryId;
	private String countryName;
	private boolean read;
	private boolean download;
	
	public void setMinAction(int minAction) {
		if (minAction == DelegateAudit.VIEW_OBSERVATION) {
			setRead(true);
		}
	}
	
	public void setMaxAction(int maxAction) {
		if (maxAction == DelegateAudit.DOWNLOAD_OBSERVATION) {
			setDownload(true);
		}
	}
	
	public int getObservationId() {
		return observationId;
	}
	public void setObservationId(int observationId) {
		this.observationId = observationId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isDownload() {
		return download;
	}
	public void setDownload(boolean download) {
		this.download = download;
	}

	public ReadDownloadObservationStat asReadDownloadObservationStat() {
		ReadDownloadObservationStat result = new ReadDownloadObservationStat();
		result.setCountryId(this.getCountryId());
		result.setCountryName(this.getCountryName());
		result.setDownload(this.isDownload());
		result.setRead(this.isRead());
		return result;
	}
}
