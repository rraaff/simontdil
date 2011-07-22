package com.tdil.simon.test;

import java.sql.SQLException;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestCountryABM extends SimonTest {

	public void testCreateCountry() throws SQLException {
		WebSpec spec = getSpec();
		SMTPServer.cleanAllMailsReceived();
		// login
		spec.open(SimonTest.SERVER_URL);
		BrowserUtils.waitUntilPage("jsp-login", spec);
		BrowserUtils.setInput("username", "Admin", spec);
		BrowserUtils.setInput("password", "Admin", spec);
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome", spec);
		// ir a system user
		spec.open(SimonTest.SERVER_URL + "goToCountryABM.st");
		BrowserUtils.waitUntilPage("jsp-countryABM", spec);
		// lleno datos
		BrowserUtils.setInput("name", RandomUtils.randomString("Country"), spec);
		BrowserUtils.setFile("flag", "/home/mgodoy/icarus/workspace/simon/Simon_Test/resources/argentina.png", spec);
		BrowserUtils.clickButton("operation", "Create", spec);
		
		// assert de que esta
//		IBatisManager.beginTransaction();
//		SystemUser systemUser = SystemUserDAO.getUser(username);
//		assertNotNull(systemUser);
//		assertTrue(systemUser.isModerator());
//		IBatisManager.endTransaction();
		//logout
		spec.open(SimonTest.SERVER_URL + "logout.st");
		
	}
}
