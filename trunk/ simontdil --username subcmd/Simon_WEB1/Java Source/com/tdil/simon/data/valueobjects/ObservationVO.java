package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Observation;

public class ObservationVO extends Observation {

	private String username;
	private String name;
	private String countryName;
	private int paragraphNumber;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(int paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
}
