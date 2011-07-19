package com.tdil.simon.test.tmp;

import org.watij.webspec.dsl.Tag;
import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.test.utils.BrowserUtils;

public class Delegate extends Thread{

	
	private String username;
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @param args
	 */
	public void run() {
		WebSpec.window_width = 800;
		WebSpec.window_height = 800;
		WebSpec spec = new WebSpec().mozilla();
		spec.open("http://localhost:8180/Simon/");
		BrowserUtils.waitUntilPage("jsp-login", spec);
		Tag username = spec.find.input().with.name("username");
		username.set.value(this.username);
		Tag password = spec.find.input().with.name("password");
		password.set.value("123123");
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-delegateHome", spec);
		for (int i = 0; i < 10; i++) {
			spec.open("http://localhost:8180/Simon/goToDelegateHome.st");
			BrowserUtils.waitUntilPage("jsp-delegateHome", spec);
			spec.open("http://localhost:8180/Simon/goToViewLastVersionOfDocument.st?&documentID=5");
			BrowserUtils.waitUntilPage("jsp-viewVersion", spec);
		}
		spec.open("http://localhost:8180/Simon/logout.st");
		spec.close();
		System.exit(0);
	}

}
