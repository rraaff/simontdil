package com.tdil.simon.test;

import java.sql.SQLException;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.test.smtp.Email;
import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.EmailUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestDelegateABM extends SimonTest {

	public void testCreateDelegate() throws SQLException {
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
		spec.open(SimonTest.SERVER_URL + "goToDelegateABM.st");
		BrowserUtils.waitUntilPage("jsp-delegateABM", spec);
		// lleno datos
		BrowserUtils.setInput("name", RandomUtils.randomString("Delegate"), spec);
		String username = RandomUtils.randomString("del");
		BrowserUtils.setInput("username", username, spec);
		String email = RandomUtils.randomEmail("del", "domain.com");
		BrowserUtils.setInput("email", email, spec);
		BrowserUtils.setInput("job", "Job", spec);
		BrowserUtils.clickButton("operation", "Create", spec);
		
		// assert de que esta
		IBatisManager.beginTransaction();
		SystemUser systemUser = SystemUserDAO.getUser(username);
		assertNotNull(systemUser);
		assertTrue(systemUser.isDelegate());
		IBatisManager.endTransaction();
		//logout
		spec.open(SimonTest.SERVER_URL + "logout.st");
		// espera por el email del usuario
		Email emailObj = EmailUtils.getEmailTo(email);
		assertNotNull(emailObj);
		assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
	}
}
