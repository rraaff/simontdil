package com.tdil.simon.data.model;

public class Attachment extends PersistentObject {
	
	private int ownerId;
	private String ownerType;
	private String title;
	private String fileName;
	private String extension;
	private String contentType;
	
	private byte[] data;

	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerID) {
		this.ownerId = ownerID;
	}
	public String getOwnerType() {
		return ownerType;
	}
	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
}
