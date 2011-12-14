package com.tdil.simon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSubmitData {

	private boolean parsed;
	private String property;
	private int position;
	
	private static Pattern pattern = Pattern.compile("^([a-zA-Z]*)\\[(\\d+)\\]$");
	
	public ImageSubmitData(String name) {
		super();
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			parsed = true;
			setProperty(matcher.group(1));
			setPosition(Integer.valueOf(matcher.group(2)));
		} else {
			parsed = false;
		}
	}
	
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isParsed() {
		return parsed;
	}
	
	public static void main(String[] args) {
		ImageSubmitData imageSubmitData = new ImageSubmitData("deleteImages[123]");
		System.out.println(imageSubmitData.isParsed());
	}
	
}
