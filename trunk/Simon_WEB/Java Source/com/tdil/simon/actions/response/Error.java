package com.tdil.simon.actions.response;

public class Error {

	private String generalError;
	
	public Error(String error) {
		super();
		setGeneralError(error);
	}
	
	public String getGeneralError() {
		return generalError;
	}

	public void setGeneralError(String generalError) {
		this.generalError = generalError;
	}

}
