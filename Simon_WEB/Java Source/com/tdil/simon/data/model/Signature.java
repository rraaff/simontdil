package com.tdil.simon.data.model;

import com.tdil.simon.web.SystemConfig;

public class Signature extends PersistentObject {

	private int versionId;
	private int userId;
	private byte[] image;
	private byte[] video;
	
	
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public byte[] getVideo() {
		return video;
	}
	public void setVideo(byte[] video) {
		this.video = video;
	}

	public String getSignatureFileName() {
		return SystemConfig.getServer() + "_" + this.getVersionId() + "_" + this.getUserId() + ".png";
	}
}
