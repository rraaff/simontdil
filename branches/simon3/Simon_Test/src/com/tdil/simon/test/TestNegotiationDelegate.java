package com.tdil.simon.test;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.DocumentType;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.UserGroup;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.test.factory.DocumentFactory;
import com.tdil.simon.test.factory.DocumentTypeFactory;
import com.tdil.simon.test.factory.SystemUserFactory;
import com.tdil.simon.test.factory.UserGroupFactory;
import com.tdil.simon.test.factory.VersionFactory;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestNegotiationDelegate extends SimonTest {

	public void testDelegateAccess() throws Exception {
		try {

			List<SystemUser> delegates = new ArrayList<SystemUser>();

			DocumentType docType = DocumentTypeFactory.newDocumentType();
			for (int i = 0; i < 3; i++) {
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
			BrowserUtils.close();

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
			BrowserUtils.waitFor(500);
			BrowserUtils.clickButton("operation", "Next (Add paragraphs)");
			BrowserUtils.executeNoMove("editor.setData('parrafo')");
			BrowserUtils.waitFor(500);
			BrowserUtils.clickButton("operation", "Next (Preview document)");
			BrowserUtils.clickButton("operation", "Save and Publish");

			BrowserUtils.setTextArea("consolidateText", "Consolidacion de test");
			BrowserUtils.clickButton("operation", "Save and publish");

			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Admin pone el sitio en negociacion
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", "Admin");
			BrowserUtils.setInput("password", "Admin");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-adminHome");
			BrowserUtils.open("setModSiteEvent.st");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
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
			BrowserUtils.close();
			
			// Aca logueo los delegados y veo si van a la negociacion o no
			// el 1 debe ir a la negociacion
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(0).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateNegotiation");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// el 2 debe estar con el sitio normal
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(1).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateHome");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// el 3 debe estar con el sitio normal
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(2).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateHome");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo el moderador y avanzo hasta el preambulo
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", systemUser.getUsername());
			BrowserUtils.setInput("password", "mod_act");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-moderatorHome");
			// Avanzo al preambulo
			Version version = VersionFactory.getLastVersionForDocument(document);
			BrowserUtils.open("editVersion.st?&id=" + version.getId());
			BrowserUtils.setInput("versionName", RandomUtils.randomString("versionName "));
			BrowserUtils.clickButtonById("next");
			BrowserUtils.waitUntilPage("jsp-createDocumentStepParagraph");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo el delegado y que vea el preambulo
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(0).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateNegotiation");
			BrowserUtils.waitFor(5000); //espero por el ajax
			String innerHtml = BrowserUtils.getDivHtmlContentById("lastParagraphText");
			assertTrue(innerHtml.contains("a. preambulo"));
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo el moderador y avanzo hasta el parrafo
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", systemUser.getUsername());
			BrowserUtils.setInput("password", "mod_act");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-moderatorHome");
			// Avanzo hasta el parrafo
			BrowserUtils.open("editVersion.st?&id=" + version.getId());
			BrowserUtils.setInput("versionName", RandomUtils.randomString("versionName "));
			BrowserUtils.clickButtonById("next");
			BrowserUtils.waitUntilPage("jsp-createDocumentStepParagraph");
			BrowserUtils.executeNoMove("editor.setData('preambulo')");
			BrowserUtils.clickButtonById("next");
			BrowserUtils.waitUntilPage("jsp-createDocumentStepParagraph");
			BrowserUtils.executeNoMove("editor.setData('parrafo')");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo el delegado y que vea el parrafo
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(0).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateNegotiation");
			BrowserUtils.waitFor(5000); //espero por el ajax
			innerHtml = BrowserUtils.getDivHtmlContentById("lastParagraphText");
			assertTrue(innerHtml.contains("1. parrafo"));
			
			// ahora abro la pantalla forced docs, no es igual pero
			BrowserUtils.open("forcedDocs.jsp");
			BrowserUtils.waitUntilPage("jsp-forcedDocs");
			BrowserUtils.open("goToViewLastVersionOfDocumentPopup.st?documentID=" + document.getId());
			BrowserUtils.waitUntilPage("jsp-viewVersionPopup");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Finalizo el documento sin firmas
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", systemUser.getUsername());
			BrowserUtils.setInput("password", "mod_act");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-moderatorHome");
			// Avanzo hasta el parrafo
			BrowserUtils.open("editVersion.st?&id=" + version.getId());
			BrowserUtils.setInput("versionName", RandomUtils.randomString("versionName "));
			BrowserUtils.clickButtonById("next");
			BrowserUtils.waitUntilPage("jsp-createDocumentStepParagraph");
			BrowserUtils.clickButtonById("next");
			BrowserUtils.waitUntilPage("jsp-createDocumentStepParagraph");
			BrowserUtils.clickButtonById("preview");
			BrowserUtils.waitUntilPage("jsp-previewDocument");
			BrowserUtils.clickButtonById("finish");
			BrowserUtils.waitUntilPage("jsp-moderatorHome");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo el delegado y veo la version final
			BrowserUtils.open("");
			BrowserUtils.waitUntilPage("jsp-login");
			BrowserUtils.setInput("username", delegates.get(0).getUsername());
			BrowserUtils.setInput("password", "delegate");
			BrowserUtils.execute("doOperationSubmit('LoginForm','login-ingresar')");
			BrowserUtils.waitUntilPage("jsp-delegateHome");
			BrowserUtils.open("logout.st");
			BrowserUtils.close();
			
			// Logueo un traductor y traduzco todo
			
			// Logueo el delegado y veo la version final traducida
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			BrowserUtils.open("logout.st");
		}

	}
}
