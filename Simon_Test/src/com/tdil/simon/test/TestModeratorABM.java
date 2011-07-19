package com.tdil.simon.test;

import java.sql.SQLException;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.ibatis.SystemUserDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestModeratorABM extends SimonTest {

	public void testCreateModerator() throws SQLException {
		WebSpec spec = getSpec();
		// login
		spec.open("http://localhost:8180/Simon/");
		BrowserUtils.waitUntilPage("jsp-login", spec);
		BrowserUtils.setInput("username", "Admin", spec);
		BrowserUtils.setInput("password", "Admin", spec);
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome", spec);
		// ir a system user
		spec.open("http://localhost:8180/Simon/goToSystemUserABM.st");
		BrowserUtils.waitUntilPage("jsp-systemUserABM", spec);
		// lleno datos
		BrowserUtils.setInput("name", RandomUtils.randomString("Moderator"), spec);
		String username = RandomUtils.randomString("mod");
		BrowserUtils.setInput("username", username, spec); // TODO
		BrowserUtils.setInput("email", RandomUtils.randomEmail("mod", "domain.com"), spec);
		BrowserUtils.setSetChecked("moderator", true, spec);
		BrowserUtils.clickButton("operation", "Create", spec);
		
		// assert de que esta
		// TODO
		IBatisManager.beginTransaction();
		SystemUser systemUser = SystemUserDAO.getUser(username);
		assertNotNull(systemUser);
		assertTrue(systemUser.isModerator());
		IBatisManager.endTransaction();
		//logout
		spec.open("http://localhost:8180/Simon/logout.st");
	}
}
