package com.tdil.simon.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.DocumentType;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.test.factory.DocumentFactory;
import com.tdil.simon.test.factory.DocumentTypeFactory;
import com.tdil.simon.test.factory.SystemUserFactory;
import com.tdil.simon.test.factory.UserGroupFactory;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestNegotiationDelegate extends SimonTest {

	public void testDelegateAccess() throws SQLException {
		WebSpec spec = getSpec();
		try {

			List<SystemUser> delegates = new ArrayList<SystemUser>();

			DocumentType docType = DocumentTypeFactory.newDocumentType();
			for (int i = 0; i < 3; i++) {
				delegates.add(SystemUserFactory.getNewDelegate());
			}
			// login
			BrowserUtils.open("");
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
			
			// Agrego el delegado 1 al grupo
			BrowserUtils.open("goToManageUsersOfGroupABM.st?&userGroupId=" + group.getId());
			BrowserUtils.setSelect("systemUserId", String.valueOf(delegates.get(0).getId()));
			BrowserUtils.clickButton("operation", "Add");
			// Doy permiso sobre el tipo del doc al grupo 1
			BrowserUtils.open("goToDocumentTypePermissionABM.st?&userGroupId=" + group.getId());
			BrowserUtils.setSelect("documentTypeId", String.valueOf(docType.getId()));
			BrowserUtils.clickButton("operation", "Add");
			
			// Agrego el delegado 2 al grupo
			BrowserUtils.open("goToManageUsersOfGroupABM.st?&userGroupId=" + group1.getId());
			BrowserUtils.setSelect("systemUserId", String.valueOf(delegates.get(1).getId()));
			BrowserUtils.clickButton("operation", "Add");
			
			BrowserUtils.open("setModSiteNormal.st");
			
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
			BrowserUtils.execute("editor.setData('texto')");
			BrowserUtils.clickButton("operation", "Next (Add paragraphs)");
			BrowserUtils.execute("editor.setData('texto')");
			BrowserUtils.clickButton("operation", "Next (Preview document)");
			BrowserUtils.clickButton("operation", "Save and Publish");

			BrowserUtils.setTextArea("consolidateText", "Consolidacion de test");
			BrowserUtils.clickButton("operation", "Save and publish");

			BrowserUtils.open("logout.st");
			
			// Admin pone el sitio en negociacion
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", "Admin");
			BrowserUtils.setInput("password", "Admin");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-adminHome");
			BrowserUtils.open("setModSiteEvent.st");
			BrowserUtils.open("logout.st");

			// Moderador pone el documento en negociacion
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", systemUser.getUsername());
			BrowserUtils.setInput("password", "mod_act");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-moderatorHome");

			Document document = DocumentFactory.getDocumentByTitle(docTitle);
			BrowserUtils.open("goToViewLastVersionOfDocument.st?&documentID=" + document.getId());
			BrowserUtils.clickButton("operation", "Discuss document");
			BrowserUtils.open("logout.st");
			
			// Aca logueo los delegados y veo si van a la negociacion o no
			// el 1 debe ir a la negociacion
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(0).getUsername());
			BrowserUtils.setInput("password", "negociador");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateNegotiation");
			BrowserUtils.open("logout.st");
			
			// el 2 debe estar con el sitio normal
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(1).getUsername());
			BrowserUtils.setInput("password", "negociador");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateHome");
			BrowserUtils.open("logout.st");
			
			// el 3 debe estar con el sitio normal
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(2).getUsername());
			BrowserUtils.setInput("password", "negociador");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateHome");
			BrowserUtils.open("logout.st");
			
			// TODO
			// LOgueo el moderador y avanzo hasta el preambulo
			
			// Logueo el delegado y que vea el preambulo
			
			// LOgueo el moderador y avanzo hasta el parrafo
			
			// Logueo el delegado y que vea el parrafo
			
			// logueo un traductor y doy de alta una traduccion
			
			// Logueo un delegado y veo la traduccion
			
			// Finalizo el documento sin firmas
			
			// Logueo el delegado y veo la version final
			
			// Logueo un traductor y traduzco todo
			
			// Logueo el delegado y veo la version final traducida
			
			
		} finally {
			BrowserUtils.open("logout.st");
		}

	}
}
