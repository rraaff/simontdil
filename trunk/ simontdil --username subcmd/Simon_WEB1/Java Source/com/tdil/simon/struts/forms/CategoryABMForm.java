package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.model.Category;

public class CategoryABMForm extends ActionForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String name;
	
	private List<Category> allCategories;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Category> getAllCategories() {
		return allCategories;
	}
	public void setAllCategories(List<Category> allCategories) {
		this.allCategories = allCategories;
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
	}
	
	public void init() throws SQLException {
		this.setAllCategories(CategoryDAO.selectAllCategories());
	}
	
	public void initWith(int userId) throws SQLException {
		Category category = CategoryDAO.getCategory(userId);
		if (category != null) {
			this.id = userId;
			this.name = category.getName();
		}
	}
	
	public void save() throws SQLException {
		if (id == 0) {
			this.addCategory();
		} else {
			this.modifyCategory();
		}
	}
	private void modifyCategory() throws SQLException {
		Category category = CategoryDAO.getCategory(this.getId());
		category.setName(this.getName());
		CategoryDAO.updateCategory(category);
	}
	private void addCategory() throws SQLException {
		Category category = new Category();
		category.setName(this.getName());
		CategoryDAO.insertCategory(category);
	}
}
