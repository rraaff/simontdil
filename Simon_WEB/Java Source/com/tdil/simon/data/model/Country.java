package com.tdil.simon.data.model;


public class Country extends PersistentObject {

	private String name;
	private boolean host = false;
	private byte[] flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getFlag() {
		return flag;
	}

	public void setFlag(byte[] flag) {
		this.flag = flag;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}
	
}
