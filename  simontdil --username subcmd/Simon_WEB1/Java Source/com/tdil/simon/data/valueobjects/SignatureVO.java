package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Signature;

public class SignatureVO extends Signature {

	private String delegateName;
	private String countryDescription;
	private int countryId;
	private String job;
	
	public String getDelegateName() {
		return delegateName;
	}
	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
	}
	public String getCountryDescription() {
		return countryDescription;
	}
	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	
}
