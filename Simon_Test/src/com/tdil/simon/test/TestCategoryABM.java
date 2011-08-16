package com.tdil.simon.test;

import java.sql.SQLException;

import org.watij.webspec.dsl.WebSpec;

import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.test.factory.SystemUserFactory;
import com.tdil.simon.test.smtp.SMTPServer;
import com.tdil.simon.test.utils.BrowserUtils;
import com.tdil.simon.test.utils.RandomUtils;

public class TestCategoryABM extends SimonTest {

	public void testCategories() throws SQLException {
		SystemUser systemUser = SystemUserFactory.getModeratorActive();
		WebSpec spec = getSpec();
		SMTPServer.cleanAllMailsReceived();
		// login
		spec.open(SimonTest.SERVER_URL);
		BrowserUtils.waitUntilPage("jsp-login");
		BrowserUtils.setInput("username", systemUser.getUsername());
		BrowserUtils.setInput("password", "mod_act");
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-moderatorHome");
		// alta de categoria
		spec.open(SimonTest.SERVER_URL + "goToCategoryABM.st");
		BrowserUtils.waitUntilPage("jsp-categoryABM");
		String catName = RandomUtils.randomString("Categoria 1-");
		BrowserUtils.setInput("name", catName);
		BrowserUtils.setInput("orderNumber", "1");
		BrowserUtils.clickButton("operation", "Create");
		IBatisManager.beginTransaction();
		Category category = CategoryDAO.getCategory(catName);
		assertNotNull(category);
		int categoryId = category.getId();
		assertEquals(catName, category.getName());
		assertEquals(1, category.getOrderNumber());
		IBatisManager.commitTransaction();
		// edicion de categoria
		spec.open(SimonTest.SERVER_URL + "editCategory.st?&id=" + category.getId());
		BrowserUtils.waitUntilPage("jsp-categoryABM");
		String editedCatName = RandomUtils.randomString("Categoria 1X-");
		BrowserUtils.setInput("name", editedCatName);
		BrowserUtils.setInput("orderNumber", "2");
		BrowserUtils.clickButton("operation", "Modify");
		IBatisManager.beginTransaction();
		category = CategoryDAO.getCategory(catName);
		assertNull(category);
		category = CategoryDAO.getCategory(editedCatName);
		assertNotNull(category);
		assertEquals(editedCatName, category.getName());
		assertEquals(2, category.getOrderNumber());
		assertEquals(categoryId, category.getId());
		IBatisManager.commitTransaction();
		
		// alta de subcategoria
		spec.open(SimonTest.SERVER_URL + "goToSubCategoryABM.st?&categoryId=" + category.getId());
		BrowserUtils.waitUntilPage("jsp-subCategoryABM");
		String subcatName = RandomUtils.randomString("Subcategoria 1-");
		BrowserUtils.setInput("name", subcatName);
		BrowserUtils.setInput("orderNumber", "1");
		BrowserUtils.clickButton("operation", "Create");
		IBatisManager.beginTransaction();
		Category subcategory = CategoryDAO.getCategory(subcatName);
		assertNotNull(subcategory);
		int subcategoryId = subcategory.getId();
		assertEquals(subcatName, subcategory.getName());
		assertEquals(1, subcategory.getOrderNumber());
		assertEquals(categoryId, subcategory.getParentId());
		IBatisManager.commitTransaction();
		// edicion de subcategoria
		spec.open(SimonTest.SERVER_URL + "editSubCategory.st?&id=" + subcategoryId);
		BrowserUtils.waitUntilPage("jsp-subCategoryABM");
		String editedSubCatName = RandomUtils.randomString("Subcategoria 1X-");
		BrowserUtils.setInput("name", editedSubCatName);
		BrowserUtils.setInput("orderNumber", "2");
		BrowserUtils.clickButton("operation", "Modify");
		IBatisManager.beginTransaction();
		category = CategoryDAO.getCategory(subcatName);
		assertNull(category);
		category = CategoryDAO.getCategory(editedSubCatName);
		assertNotNull(category);
		assertEquals(editedSubCatName, category.getName());
		assertEquals(2, category.getOrderNumber());
		assertEquals(subcategoryId, category.getId());
		assertEquals(categoryId, subcategory.getParentId());
		IBatisManager.commitTransaction();
		spec.open(SimonTest.SERVER_URL + "logout.st");
	}


}
