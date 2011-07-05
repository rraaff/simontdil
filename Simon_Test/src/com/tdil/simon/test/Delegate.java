package com.tdil.simon.test;

import org.watij.webspec.dsl.Tag;
import org.watij.webspec.dsl.WebSpec;

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
		//spec.pauseUntilReady();
		System.out.println(System.currentTimeMillis());
		waitUntilPage("jsp-login", spec);
		Tag username = spec.find.input().with.name("username");
		System.out.println(System.currentTimeMillis());
		username.set.value(this.username);
		Tag password = spec.find.input().with.name("password");
		password.set.value("123123");
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		waitUntilPage("jsp-delegateHome", spec);
//		spec.pauseUntilReady();
		for (int i = 0; i < 10; i++) {
			System.out.println("antes de goToDelegateHome");
			spec.open("http://localhost:8180/Simon/goToDelegateHome.st");
			waitUntilPage("jsp-delegateHome", spec);
			System.out.println("antes de goToViewLastVersionOfDocument");
			spec.open("http://localhost:8180/Simon/goToViewLastVersionOfDocument.st?&documentID=5");
			waitUntilPage("jsp-viewVersion", spec);
			// spec.pauseUntilReady();
			System.out.println("despues de goToViewLastVersionOfDocument");
		}
		spec.open("http://localhost:8180/Simon/logout.st");
		spec.close();
		System.exit(0);
	}


	private void waitUntilPage(String string, WebSpec spec) {
		int retries = 10;
		System.out.println("antes del find " + string);
		Tag tag = spec.findWithId(string);
		while (tag == null && retries >= 0) {
			try {
				System.out.println("sleeping " + string);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retries = retries - 1;
			tag = spec.find.div(string);
		}
		if (tag == null) {
			throw new RuntimeException("timeout waiting for page " + string);
		}
		System.out.println("despues del find " + string);
	}


	private void waitUntilNotBusy(WebSpec spec) {
		while(spec.busy()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
