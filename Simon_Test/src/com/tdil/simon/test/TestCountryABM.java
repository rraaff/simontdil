package com.tdil.simon.test;

import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;
// TODO arreglarlo
public class TestCountryABM extends SimonTest {

	public void testCreateCountry() throws Exception {
		SMTPServer.cleanAllMailsReceived();
		// login
		BrowserUtils.open("");
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", "Admin");
		BrowserUtils.setInput("password", "Admin");
		BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome");
		// ir a system user
		BrowserUtils.open("goToCountryABM.st");
		BrowserUtils.waitUntilPage("jsp-countryABM");
		// lleno datos
		BrowserUtils.setInput("name", RandomUtils.randomString("Country "));
		BrowserUtils.setFile("flag", "/home/mgodoy/icarus/workspace/simon/Simon_Test/resources/argentina.png");
		BrowserUtils.clickButton("operation", "Create");
		
		// assert de que esta
//		IBatisManager.beginTransaction();
//		SystemUser systemUser = SystemUserDAO.getUser(username);
//		assertNotNull(systemUser);
//		assertTrue(systemUser.isModerator());
//		IBatisManager.endTransaction();
		//logout
		BrowserUtils.open("logout.st");
		
	}
}
