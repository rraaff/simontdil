package com.tdil.simon.test.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlFileInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;

public class BrowserUtilsHtmlUnit implements Browser {

	private WebClient webClient;
	private HtmlPage currPage;
	
	public void waitUntilPage(String string) {
		HtmlElement element = getCurrPage().getBody();
		Assert.assertEquals(string, element.getId());
	}
	
	public void open(String url) throws Exception {
		if (webClient == null) {
			webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
		}
		setCurrPage((HtmlPage)webClient.getPage(url));
		//spec.pauseUntilReady(true);
	}

	public void setInput(String inputName, String value) {
		HtmlElement element = getCurrPage().getElementByName(inputName);
		((HtmlInput)element).setValueAttribute(value);
	}
	
	public void setTextArea(String inputName, String value) {
		HtmlElement element = getCurrPage().getElementByName(inputName);
		((HtmlTextArea)element).setText(value);
	}

	public void setSelect(String select, String value) {
		HtmlElement element = getCurrPage().getElementByName(select);
		((HtmlSelect)element).setSelectedAttribute(value, true);
	}

	public void setSetChecked(String checkboxName, boolean checked) {
		HtmlElement element = getCurrPage().getElementByName(checkboxName);
		((HtmlCheckBoxInput)element).setChecked(checked);
	}

	public void clickButton(String inputName, String value) {
		try {
			List<HtmlElement> elements = getCurrPage().getElementsByName(inputName);
			HtmlInput found = null;
			for (HtmlElement e : elements) {
				if (((HtmlInput)e).getValueAttribute().equals(value)) {
					found = (HtmlInput)e;
				}
			}
			setCurrPage((HtmlPage)found.click());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void clickButtonById(String string) {
		try {
			HtmlElement element = getCurrPage().getHtmlElementById(string);
			setCurrPage((HtmlPage)((HtmlInput)element).click());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getDivHtmlContentById(String id) {
		HtmlDivision div = (HtmlDivision)getCurrPage().getElementById(id);
		return div.asXml();
	}
	
	public void setFile(String fieldName, String fileName) {
		try {
			ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
			IOUtils.copy(new FileInputStream(fileName), byteOut);
			((HtmlFileInput)getCurrPage().getElementByName(fieldName)).setValueAttribute(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void execute(String script) {
		ScriptResult scriptResult = getCurrPage().executeJavaScript(script);
		setCurrPage((HtmlPage)scriptResult.getNewPage());
	}
	public void executeNoMove(String script) {
		getCurrPage().executeJavaScript(script);
	}
	
	public void close() {
		webClient.closeAllWindows();
		webClient = null;
	}

	private void setCurrPage(HtmlPage currPage) {
		this.currPage = currPage;
		HtmlElement element = currPage.getBody();
		System.out.println();
		System.out.println("***********************");
		System.out.println("current page is " + element.getId());
		System.out.println(currPage.asXml());
	}

	private HtmlPage getCurrPage() {
		return currPage;
	}



}
