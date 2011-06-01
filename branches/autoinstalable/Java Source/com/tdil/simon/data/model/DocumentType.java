package com.tdil.simon.data.model;


public class DocumentType extends PersistentObject {
	
	private String name;
	private int parentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
}
