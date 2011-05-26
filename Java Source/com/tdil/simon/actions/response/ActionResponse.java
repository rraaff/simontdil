package com.tdil.simon.actions.response;

import java.util.Date;

public final class ActionResponse {

	private String date = new Date().toString(); // TODO ver con nico si necesita algun formato
	private ResponseType type;
	private Object data;

	public ResponseType getType() {
		return type;
	}

	public void setType(ResponseType type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ActionResponse newOKResponse() {
		return new ActionResponse(ResponseType.OK);
	}
	
	public static ActionResponse newOKResponse(Object data) {
		return new ActionResponse(ResponseType.OK, data);
	}

	public ActionResponse(ResponseType type) {
		super();
		this.type = type;
	}

	public ActionResponse(ResponseType type, Object data) {
		super();
		this.type = type;
		this.data = data;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
