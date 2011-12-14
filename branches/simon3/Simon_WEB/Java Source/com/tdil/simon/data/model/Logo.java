package com.tdil.simon.data.model;

public class Logo extends PersistentObject {
	
	private String logoKey;
	private byte[] logoData;
	
	public String getLogoKey() {
		return logoKey;
	}
	public void setLogoKey(String key) {
		this.logoKey = key;
	}
	public byte[] getLogoData() {
		return logoData;
	}
	public void setLogoData(byte[] logo) {
		this.logoData = logo;
	}


}
