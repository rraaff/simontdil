package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.SubCategory;

public class SubCategoryDAO {

	public static List selectAllSubCategoryByCategoryId(int categoryId) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllSubCategoryByCategoryId", categoryId);
	}
	
	// List de SubCategoryVO
	public static List selectAllSubCategoryNotDeleted() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllSubCategoryNotDeleted");
	}
	
	public static SubCategory getSubCategory(int id) throws SQLException {
		return (SubCategory) IBatisManager.sqlMapper.queryForObject(
				"selectSubCategoryById", id);
	}

	public static SubCategory getSubCategory(String name, int categoryId) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("categoryId", categoryId);
		return (SubCategory) IBatisManager.sqlMapper.queryForObject(
				"selectSubCategoryByNameAnCategoryId", params);
	}
	
	public static void insertSubCategory(SubCategory subCategory) throws SQLException {
		IBatisManager.sqlMapper.insert("insertSubCategory", subCategory);
	}

	public static void updateSubCategory(SubCategory subCategory) throws SQLException {
		IBatisManager.sqlMapper.update("updateSubCategory", subCategory);
	}

	public static void logicallyDeleteSubCategory(SubCategory subCategory)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteSubCategory", subCategory);
	}


}
