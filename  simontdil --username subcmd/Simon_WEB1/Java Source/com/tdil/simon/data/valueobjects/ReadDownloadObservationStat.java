package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.DelegateAudit;

public class ReadDownloadObservationStat {

	private int countryId;
	private String countryName;
	private boolean read = false;
	private boolean download = false;
	
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
	
	public void setMinAction(int minAction) {
		if (minAction == DelegateAudit.VIEW_VERSION) {
			setRead(true);
		}
	}
	
	public void setMaxAction(int maxAction) {
		if (maxAction == DelegateAudit.DOWNLOAD_VERSION) {
			setDownload(true);
		}
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
	
}
