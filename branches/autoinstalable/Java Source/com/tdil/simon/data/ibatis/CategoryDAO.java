package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.Category;
import com.tdil.simon.data.model.DocumentType;

public class CategoryDAO {

	// TODO VER los selects que no se bajen la banderaaa
	public static List selectAllCategories() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllRootCategories");
	}
	
	public static List selectAllCategoriesNotDeleted() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCategoriesNotDeleted");
	}
	
	public static List selectAllCategoriesNotDeletedVO() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCategoriesNotDeletedVO");
	}
	
	public static Category getCategory(int id) throws SQLException {
		return (Category) IBatisManager.sqlMapper.queryForObject(
				"selectCategoryById", id);
	}

	public static Category getCategory(String name) throws SQLException {
		return (Category) IBatisManager.sqlMapper.queryForObject(
				"selectCategoryByName", name);
	}
	
	public static void insertCategory(Category country) throws SQLException {
		IBatisManager.sqlMapper.insert("insertCategory", country);
	}

	public static void updateCategory(Category category) throws SQLException {
		IBatisManager.sqlMapper.update("updateCategory", category);
	}

	public static void logicallyDeleteCategory(Category category)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteCategory", category);
	}

	public static List<Category> selectAllCategoriesByParentId(int parentId) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCategoriesByParentId", parentId);
	}

	public static Category getCategory(String name, int parentId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("parentId", parentId);
		return (Category) IBatisManager.sqlMapper.queryForObject("selectCategoriesByNameAndParentId", params);
	}
}
