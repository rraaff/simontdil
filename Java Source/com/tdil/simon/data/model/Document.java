package com.tdil.simon.data.model;


public class Document extends PersistentObject {

	private String title;
	private String introduction;
	private boolean principal;
	private int documentSubTypeId;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String description) {
		this.introduction = description;
	}
	public boolean isPrincipal() {
		return principal;
	}
	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public int getDocumentSubTypeId() {
		return documentSubTypeId;
	}
	public void setDocumentSubTypeId(int documentTypeId) {
		this.documentSubTypeId = documentTypeId;
	}
}
