package com.tdil.simon.struts.forms;

public class MonthOption {
	
	private String monthNumber;
	private String monthText;
	
	public MonthOption(String monthNumber, String monthText) {
		super();
		this.monthNumber = monthNumber;
		this.monthText = monthText;
	}
	
	public String getMonthNumber() {
		return monthNumber;
	}
	public void setMonthNumber(String monthNumber) {
		this.monthNumber = monthNumber;
	}
	public String getMonthText() {
		return monthText;
	}
	public void setMonthText(String monthText) {
		this.monthText = monthText;
	}

}
