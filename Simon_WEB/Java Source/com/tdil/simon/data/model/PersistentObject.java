package com.tdil.simon.data.model;

import com.tdil.simon.web.XMLUtils;

public class PersistentObject {

	private int id;
	private boolean deleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return XMLUtils.asAXML(this);
	}
}
