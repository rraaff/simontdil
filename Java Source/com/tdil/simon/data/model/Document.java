package com.tdil.simon.data.model;


public class Document extends PersistentObject {

	private String title;
	private String introduction;
	private boolean principal;
	private int documentSubTypeId;
	private int relevance;
	
	private String documentDate;
	private String topic;
	private String tag1;
	private String tag2;
	
	public static final int DEFAULT_RELEVANT = 10;
	public static final int NO_RELEVANT = 0;
	
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
	public int getRelevance() {
		return relevance;
	}
	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}
	public String getDocumentDate() {
		return documentDate;
	}
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTag1() {
		return tag1;
	}
	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}
	public String getTag2() {
		return tag2;
	}
	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}
}
