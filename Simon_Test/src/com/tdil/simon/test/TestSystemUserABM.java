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

public class TestSystemUserABM extends SimonTest {

	public void testCreateSystemUsers() throws SQLException {
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
		spec.open(SimonTest.SERVER_URL + "goToSystemUserABM.st");
		BrowserUtils.waitUntilPage("jsp-systemUserABM", spec);
		// lleno datos para admin
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Admin"), spec);
			String username = RandomUtils.randomString("adm");
			BrowserUtils.setInput("username", username, spec);
			String email = RandomUtils.randomEmail("adm", "domain.com");
			BrowserUtils.setInput("email", email, spec);
			BrowserUtils.setSetChecked("administrator", true, spec);
			BrowserUtils.clickButton("operation", "Create", spec);

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
			BrowserUtils.setInput("name", RandomUtils.randomString("Moderator"), spec);
			String username = RandomUtils.randomString("mod");
			BrowserUtils.setInput("username", username, spec);
			String email = RandomUtils.randomEmail("mod", "domain.com");
			BrowserUtils.setInput("email", email, spec);
			BrowserUtils.setSetChecked("moderator", true, spec);
			BrowserUtils.clickButton("operation", "Create", spec);

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
		// lleno datos para designer
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Designer"), spec);
			String username = RandomUtils.randomString("des");
			BrowserUtils.setInput("username", username, spec);
			String email = RandomUtils.randomEmail("des", "domain.com");
			BrowserUtils.setInput("email", email, spec);
			BrowserUtils.setSetChecked("designer", true, spec);
			BrowserUtils.clickButton("operation", "Create", spec);

			// assert de que esta
			IBatisManager.beginTransaction();
			SystemUser systemUser = SystemUserDAO.getUser(username);
			assertNotNull(systemUser);
			assertTrue(systemUser.isDesigner());
			IBatisManager.endTransaction();
			// espera por el email del usuario
			Email emailObj = EmailUtils.getEmailTo(email);
			assertNotNull(emailObj);
			assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
		}
		// lleno datos para assistant
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("Assistant"), spec);
			String username = RandomUtils.randomString("assist");
			BrowserUtils.setInput("username", username, spec);
			String email = RandomUtils.randomEmail("assist", "domain.com");
			BrowserUtils.setInput("email", email, spec);
			BrowserUtils.setSetChecked("assistant", true, spec);
			BrowserUtils.clickButton("operation", "Create", spec);

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
		// lleno datos para translator
		{
			BrowserUtils.setInput("name", RandomUtils.randomString("translator"), spec);
			String username = RandomUtils.randomString("trans");
			BrowserUtils.setInput("username", username, spec);
			String email = RandomUtils.randomEmail("translator", "domain.com");
			BrowserUtils.setInput("email", email, spec);
			BrowserUtils.setSetChecked("translator", true, spec);
			BrowserUtils.clickButton("operation", "Create", spec);

			// assert de que esta
			IBatisManager.beginTransaction();
			SystemUser systemUser = SystemUserDAO.getUser(username);
			assertNotNull(systemUser);
			assertTrue(systemUser.isTranslator());
			IBatisManager.endTransaction();
			// espera por el email del usuario
			Email emailObj = EmailUtils.getEmailTo(email);
			assertNotNull(emailObj);
			assertTrue(emailObj.getMessage().contains(systemUser.getPassword()));
		}
		// logout
		spec.open(SimonTest.SERVER_URL + "logout.st");
	}


}
