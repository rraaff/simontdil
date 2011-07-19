package com.tdil.simon.test;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestModeratorABM extends SimonTest {

	public void testCreateModerator() {
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
		BrowserUtils.setInput("username", RandomUtils.randomString("mod"), spec); // TODO
		BrowserUtils.setInput("email", RandomUtils.randomEmail("mod", "domain.com"), spec);
		BrowserUtils.setSetChecked("moderator", true, spec);
		BrowserUtils.clickButton("operation", "Create", spec);
		
		// assert de que esta
		
		//logout
		spec.open("http://localhost:8180/Simon/logout.st");
	}
}
