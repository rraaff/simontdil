package com.tdil.simon.test.simulation;

import com.tdil.simon.test.SimonTest;
import com.tdil.simon.test.factory.SystemUserFactory;
import com.tdil.simon.test.utils.Browser;
import com.tdil.simon.test.utils.BrowserUtilsHtmlUnit;

public class Delegate extends Thread {

	private String username;
	
	public Delegate(String username) {
		super();
		this.username = username;
	}
	
	@Override
	public void run() {
		try {
			Browser browser = new BrowserUtilsHtmlUnit();
			browser.open(SimonTest.SERVER_URL + "login.jsp");
			browser.waitUntilPage("jsp-login");
			browser.setInput("username", username);
			browser.setInput("password", SystemUserFactory.DELEGATE_PASSWORD);
			browser.execute("doOperationSubmit('LoginForm','login-ingresar')");
			while (true) {
				Thread.sleep(3000);
				String page = browser.getPageName();
				StringBuffer stringBuffer = new StringBuffer();
				stringBuffer.append(username).append(" at ").append(page);
				if (page.equals("jsp-delegateNegotiation")) {
					String innerHtml = browser.getDivHtmlContentById("lastParagraphText");
					if (innerHtml.indexOf('.') != -1) {
						int index = innerHtml.indexOf('.');
						stringBuffer.append(" seeing paragraph ").append(innerHtml.substring(index - 2));
					} else {
						stringBuffer.append(" no paragraph ");
					}
				}
				System.out.println(stringBuffer.toString());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
