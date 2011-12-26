package com.tdil.simon.test;

import java.sql.SQLException;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.test.smtp.Email;
import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.EmailUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestDelegateABM extends SimonTest {

	public void testCreateDelegate() throws Exception {
		SMTPServer.cleanAllMailsReceived();
		// login
		BrowserUtils.open("");
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", "Admin");
		BrowserUtils.setInput("password", "Admin");
		
		BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome");
		// ir a system user
		BrowserUtils.open("goToDelegateABM.st");
		BrowserUtils.waitUntilPage("jsp-delegateABM");
		// lleno datos
		BrowserUtils.setInput("name", RandomUtils.randomString("Delegate"));
		String username = RandomUtils.randomString("del");
		BrowserUtils.setInput("username", username);
		String email = RandomUtils.randomEmail("del", "domain.com");
		BrowserUtils.setInput("email", email);
		BrowserUtils.setInput("job", "Job");
		BrowserUtils.clickButton("operation", "Create");
		
		// assert de que esta
		IBatisManager.beginTransaction();
		SystemUser systemUser = SystemUserDAO.getUser(username);
		assertNotNull(systemUser);
		assertTrue(systemUser.isDelegate());
		IBatisManager.endTransaction();
		//logout
		BrowserUtils.open("logout.st");
		// espera por el email del usuario
		Email emailObj = EmailUtils.getEmailTo(email);
		assertNotNull(emailObj);
		assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
	}
}
