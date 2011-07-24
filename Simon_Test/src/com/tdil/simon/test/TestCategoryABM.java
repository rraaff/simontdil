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
		BrowserUtils.waitUntilPage("jsp-login", spec);
		BrowserUtils.setInput("username", systemUser.getUsername(), spec);
		BrowserUtils.setInput("password", "mod_act", spec);
		spec.execute("doOperationSubmit('LoginForm','login-ingresar')");
		BrowserUtils.waitUntilPage("jsp-moderatorHome", spec);
		// alta de categoria
		spec.open(SimonTest.SERVER_URL + "goToCategoryABM.st");
		BrowserUtils.waitUntilPage("jsp-categoryABM", spec);
		String catName = RandomUtils.randomString("Categoria 1-");
		BrowserUtils.setInput("name", catName, spec);
		BrowserUtils.setInput("orderNumber", "1", spec);
		BrowserUtils.clickButton("operation", "Create", spec);
		IBatisManager.beginTransaction();
		Category category = CategoryDAO.getCategory(catName);
		assertNotNull(category);
		int categoryId = category.getId();
		assertEquals(catName, category.getName());
		assertEquals(1, category.getOrderNumber());
		IBatisManager.commitTransaction();
		// edicion de categoria
		spec.open(SimonTest.SERVER_URL + "editCategory.st?&id=" + category.getId());
		BrowserUtils.waitUntilPage("jsp-categoryABM", spec);
		String editedCatName = RandomUtils.randomString("Categoria 1X-");
		BrowserUtils.setInput("name", editedCatName, spec);
		BrowserUtils.setInput("orderNumber", "2", spec);
		BrowserUtils.clickButton("operation", "Modify", spec);
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
		BrowserUtils.waitUntilPage("jsp-subCategoryABM", spec);
		String subcatName = RandomUtils.randomString("Subcategoria 1-");
		BrowserUtils.setInput("name", subcatName, spec);
		BrowserUtils.setInput("orderNumber", "1", spec);
		BrowserUtils.clickButton("operation", "Create", spec);
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
		BrowserUtils.waitUntilPage("jsp-subCategoryABM", spec);
		String editedSubCatName = RandomUtils.randomString("Subcategoria 1X-");
		BrowserUtils.setInput("name", editedSubCatName, spec);
		BrowserUtils.setInput("orderNumber", "2", spec);
		BrowserUtils.clickButton("operation", "Modify", spec);
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
