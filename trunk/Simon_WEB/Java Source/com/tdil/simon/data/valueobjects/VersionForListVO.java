package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Version;
import com.tdil.simon.struts.ApplicationResources;

public class VersionForListVO extends Version {

	private String documentTitle;
	private int observationCount;
	
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public int getObservationCount() {
		return observationCount;
	}
	public void setObservationCount(int observationCount) {
		this.observationCount = observationCount;
	}
	
	public String getVersionWithSubversion() {
		return this.getNumber() + "." + this.getObservationCount();
	}
	
	public String getHasObservationText() {
		if (this.getObservationCount() == 0) {
			return ApplicationResources.getMessage("NO");
		} else {
			return ApplicationResources.getMessage("YES");
		}
	}
	
	public String getObservationCountText() {
		if (this.getObservationCount() == 0) {
			return "-";
		} else {
			return String.valueOf(this.getObservationCount());
		}
	}
}
