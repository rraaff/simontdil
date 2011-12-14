package com.tdil.simon.test.utils;


public interface Browser {

	public void waitUntilPage(String string);
	
	public void open(String url) throws Exception;
	
	public void setInput(String inputName, String value);
	
	public void setTextArea(String inputName, String value);

	public void setSelect(String select, String value);

	public void setSetChecked(String checkboxName, boolean checked);

	public void clickButton(String inputName, String value);
	
	public void clickButtonById(String string);
	
	public void setFile(String fieldName, String fileName);

	public void execute(String script);
	public void executeNoMove(String script);

	public void close();

	public String getDivHtmlContentById(String id);

	public String getPageName();
}
