package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.CategoryValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.model.Category;

public class CategoryABMForm extends TransactionalValidationForm implements ABMForm {

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
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
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
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		if (id == 0) {
			this.addCategory();
		} else {
			this.modifyCategory();
		}
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		CategoryValidation.validateName(this.name, "category.name", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		Category exists = CategoryDAO.getCategory(this.name);
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("category.name", "category.name." + ValidationErrors.CATEGORY_ALREADY_EXISTS);
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
	public void delete(int position) throws SQLException {
		Category category = this.getAllCategories().get(position);
		category.setDeleted(true);
		CategoryDAO.logicallyDeleteCategory(category);
	}
	public void reactivate(int position) throws SQLException {
		Category category = this.getAllCategories().get(position);
		category.setDeleted(false);
		CategoryDAO.updateCategory(category);
	}
}
