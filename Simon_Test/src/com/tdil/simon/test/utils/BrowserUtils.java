package com.tdil.simon.test.utils;

import org.watij.webspec.dsl.Tag;
import org.watij.webspec.dsl.WebSpec;

public class BrowserUtils {

	public static void waitUntilPage(String string, WebSpec spec) {
		int retries = 10;
		Tag tag = spec.findWithId(string);
		while (tag == null && retries >= 0) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			retries = retries - 1;
			tag = spec.find.div(string);
		}
		if (tag == null) {
			throw new RuntimeException("timeout waiting for page " + string);
		}
	}
	
	public static void setInput(String inputName, String value, WebSpec spec) {
		Tag username = spec.find.input().with.name(inputName);
		username.set.value(value);
	}


	public static void setSetChecked(String checkboxName, boolean checked, WebSpec spec) {
		Tag username = spec.find.input().with.name(checkboxName);
		username.set.checked(checked);
	}

	public static void clickButton(String inputName, String value, WebSpec spec) {
		Tag button =  spec.find("input").with.type("submit").at(0);
		button.click();
	}

	public static void setFile(String fieldName, String fileName, WebSpec spec) {
		spec.record.file().set(fileName).ok();
		spec.find.input().with.type("file").click();
		spec.find.input().with.name("flag").click();
		//spec.find.input().with.name(fieldName).set.value("/home/mgodoy/icarus/workspace/simon/Simon_Test/resources/argentina.png");//click();
	}

}
