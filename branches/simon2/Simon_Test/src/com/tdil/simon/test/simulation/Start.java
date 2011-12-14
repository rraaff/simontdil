package com.tdil.simon.test.simulation;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.DocumentType;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.test.factory.DocumentTypeFactory;
import com.tdil.simon.test.factory.SystemUserFactory;
import com.tdil.simon.test.factory.UserGroupFactory;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;
import com.tdil.simon.test.utils.SimonTestInit;

public class Start {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimonTestInit.init();
		List<SystemUser> delegates = prepareDelegatesAndDocument();
//		List<SystemUser> delegates = getDelegates();
		
		for (SystemUser systemUser : delegates) {
			Delegate del = new Delegate(systemUser.getUsername());
			del.start();
		}
		while(true) {
			Thread.sleep(10000);
		}
	}
	
	public static List<SystemUser> getDelegates() throws Exception {
		List<SystemUser> delegates = new ArrayList<SystemUser>();
		for (int i = 0; i < 20; i++) {
			delegates.add(SystemUserFactory.getDelegate("del" + i));
		}
		return delegates;
	}
	
	public static List<SystemUser> prepareDelegatesAndDocument() throws Exception {
		DocumentType docType = DocumentTypeFactory.newDocumentType();
		List<SystemUser> delegates = new ArrayList<SystemUser>();
		for (int i = 0; i < 20; i++) {
			delegates.add(SystemUserFactory.getDelegate("del" + i));
		}
		// login
		BrowserUtils.open("login.jsp");
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", "Admin");
		BrowserUtils.setInput("password", "Admin");
		BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-adminHome");
		 
		// creo el grupo
		String groupName = RandomUtils.randomString("grupo ");
		BrowserUtils.open("goToUserGroupABM.st");
		BrowserUtils.setInput("name", groupName);
		BrowserUtils.clickButton("operation", "Create");
		UserGroup group = UserGroupFactory.getGroupByName(groupName);
		
		groupName = RandomUtils.randomString("grupo ");
		BrowserUtils.open("goToUserGroupABM.st");
		BrowserUtils.setInput("name", groupName);
		BrowserUtils.clickButton("operation", "Create");
		UserGroup group1 = UserGroupFactory.getGroupByName(groupName);
		
		BrowserUtils.open("goToDocumentTypePermissionABM.st?&userGroupId=" + group.getId());
		BrowserUtils.setSelect("documentTypeId", String.valueOf(docType.getId()));
		BrowserUtils.clickButton("operation", "Add");

		// Agrego los delegados al grupo
		for (SystemUser del : delegates) {
			BrowserUtils.open("goToManageUsersOfGroupABM.st?&userGroupId=" + group.getId());
			BrowserUtils.setSelect("systemUserId", String.valueOf(del.getId()));
			BrowserUtils.clickButton("operation", "Add");
		}
		BrowserUtils.open("setModSiteEvent.st");
		BrowserUtils.open("logout.st");
		
		SystemUser systemUser = SystemUserFactory.getModeratorActive();
		// Login del moderador
		BrowserUtils.open("");
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", systemUser.getUsername());
		BrowserUtils.setInput("password", "mod_act");
		BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-moderatorHome");

		// Moderador da de alta el documento
		String docTitle = RandomUtils.randomString("title ");
		BrowserUtils.open("createDocument.st");
		BrowserUtils.setInput("title", docTitle);
		BrowserUtils.setInput("versionName", RandomUtils.randomString("versionName "));
		BrowserUtils.setSetChecked("principal", true);
		BrowserUtils.setInput("limitObservations", "31-12-2050");
		BrowserUtils.setSelect("documentSubTypeId", String.valueOf(docType.getId()));
		BrowserUtils.setInput("orderNumber", "1");

		BrowserUtils.clickButton("operation", "Next (Preamble)");
		BrowserUtils.executeNoMove("editor.setData('preambulo')");
		BrowserUtils.clickButton("operation", "Next (Add paragraphs)");
		BrowserUtils.executeNoMove("editor.setData('parrafo')");
		BrowserUtils.clickButton("operation", "Next (Preview document)");
		BrowserUtils.clickButton("operation", "Save and Publish");

		BrowserUtils.setTextArea("consolidateText", "Consolidacion de test");
		BrowserUtils.clickButton("operation", "Save and publish");

		BrowserUtils.open("logout.st");
		return delegates;
	}

}
