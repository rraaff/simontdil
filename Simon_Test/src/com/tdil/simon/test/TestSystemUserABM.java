package com.tdil.simon.test;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.test.smtp.Email;
import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.EmailUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestSystemUserABM extends SimonTest {

	public void testCreateSystemUsers() throws Exception {
		SMTPServer.cleanAllMailsReceived();
		// login
		BrowserUtils.open("");
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", "Admin");
		BrowserUtils.setInput("password", "Admin");
		BrowserUtils.setSelect("language", "English");
		BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome");
		// ir a system user
		BrowserUtils.open("goToSystemUserABM.st");
		BrowserUtils.waitUntilPage("jsp-systemUserABM");
		// lleno datos para admin
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Admin"));
			String username = RandomUtils.randomString("adm");
			BrowserUtils.setInput("username", username);
			String email = RandomUtils.randomEmail("adm", "domain.com");
			BrowserUtils.setInput("email", email);
			BrowserUtils.setSetChecked("administrator", true);
			BrowserUtils.clickButton("operation", "Create");

			// assert de que esta
			IBatisManager.beginTransaction();
			SystemUser systemUser = SystemUserDAO.getUser(username);
			assertNotNull(systemUser);
			assertTrue(systemUser.isAdministrator());
			IBatisManager.endTransaction();
			// espera por el email del usuario
			Email emailObj = EmailUtils.getEmailTo(email);
			assertNotNull(emailObj);
			assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
		}
		// lleno datos para moderador
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Moderator"));
			String username = RandomUtils.randomString("mod");
			BrowserUtils.setInput("username", username);
			String email = RandomUtils.randomEmail("mod", "domain.com");
			BrowserUtils.setInput("email", email);
			BrowserUtils.setSetChecked("moderator", true);
			BrowserUtils.clickButton("operation", "Create");

			// assert de que esta
			IBatisManager.beginTransaction();
			SystemUser systemUser = SystemUserDAO.getUser(username);
			assertNotNull(systemUser);
			assertTrue(systemUser.isModerator());
			IBatisManager.endTransaction();
			// espera por el email del usuario
			Email emailObj = EmailUtils.getEmailTo(email);
			assertNotNull(emailObj);
			assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
		}
		// lleno datos para assistant
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Assistant"));
			String username = RandomUtils.randomString("assist");
			BrowserUtils.setInput("username", username);
			String email = RandomUtils.randomEmail("assist", "domain.com");
			BrowserUtils.setInput("email", email);
			BrowserUtils.setSetChecked("assistant", true);
			BrowserUtils.clickButton("operation", "Create");

			// assert de que esta
			IBatisManager.beginTransaction();
			SystemUser systemUser = SystemUserDAO.getUser(username);
			assertNotNull(systemUser);
			assertTrue(systemUser.isAssistant());
			IBatisManager.endTransaction();
			// espera por el email del usuario
			Email emailObj = EmailUtils.getEmailTo(email);
			assertNotNull(emailObj);
			assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
		}
		// logout
		BrowserUtils.open("logout.st");
	}


}
