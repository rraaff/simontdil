package com.tdil.simon.test;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.valueobjects.CategorySelectionVO;
import com.tdil.simon.struts.forms.ReferenceDocumentABMForm;
import com.tdil.simon.test.utils.RandomUtils;

public class TestReferenceDocumentABM extends SimonTest {

	public void testCategoryList() throws SQLException {
		IBatisManager.beginTransaction();
		String numerator = String.valueOf(RandomUtils.nextNumerator());
		String first = "AAA AAA " + numerator;
		String firstChild = "ZZZ BBB " + numerator;
		
		String second = "AAA BBB " + numerator;
		String secondChild = "ZZZ AAA " + numerator;
		Category c = new Category();
		c.setName(first);
		CategoryDAO.insertCategory(c);
		c.setName(second);
		CategoryDAO.insertCategory(c);
		IBatisManager.commitTransaction();

		IBatisManager.beginTransaction();
		Category parent = CategoryDAO.getCategory(first);
		c.setName(firstChild);
		c.setParentId(parent.getId());
		CategoryDAO.insertCategory(c);
		
		parent = CategoryDAO.getCategory(second);
		c.setName(secondChild);
		c.setParentId(parent.getId());
		CategoryDAO.insertCategory(c);
		IBatisManager.commitTransaction();
		
		IBatisManager.beginTransaction();
		ReferenceDocumentABMForm refDoc = new ReferenceDocumentABMForm();
		refDoc.init();
		int indexFirst = getIndexFor(refDoc.getAllCategories(), first);
		int indexFirstChild = getIndexFor(refDoc.getAllCategories(), firstChild);
		
		int indexSecond = getIndexFor(refDoc.getAllCategories(), second);
		int indexSecondChild = getIndexFor(refDoc.getAllCategories(), secondChild);
		IBatisManager.commitTransaction();
		
		assertEquals("Child should be after", indexFirst + 1, indexFirstChild);
		assertEquals("Child should be after", indexSecond + 1, indexSecondChild);
	}

	private int getIndexFor(List<CategorySelectionVO> allCategories, String first) {
		for (CategorySelectionVO catvo : allCategories) {
			if (catvo.getName().equals(first)) {
				return allCategories.indexOf(catvo);
			}
		}
		return -1;
	}
}
