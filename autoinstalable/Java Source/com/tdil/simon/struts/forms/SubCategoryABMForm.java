package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.CategoryValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.CategoryDAO;
import com.tdil.simon.data.model.Category;

public class SubCategoryABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private String categoryName;
	private int categoryId;
	private int id;
	private String name;
	private String orderNumber;
	
	private List<Category> allSubCategory;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
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
	public List<Category> getAllSubCategory() {
		return allSubCategory;
	}
	public void setAllSubCategory(List<Category> allSubCategory) {
		this.allSubCategory = allSubCategory;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.orderNumber = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		Category category = CategoryDAO.getCategory(this.getCategoryId());
		setCategoryName(category.getName());
		this.setAllSubCategory(CategoryDAO.selectAllCategoriesByParentId(this.getCategoryId()));
	}
	
	public void initWith(int subCategoryId) throws SQLException {
		Category subCategory = CategoryDAO.getCategory(subCategoryId);
		if (subCategory != null) {
			this.id = subCategoryId;
			this.name = subCategory.getName();
			this.orderNumber = String.valueOf(subCategory.getOrderNumber());
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		if (id == 0) {
			this.addSubCategory();
		} else {
			this.modifySubCategory();
		}
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		CategoryValidation.validateName(this.name, "subCategory.name", validation);
		IdValidation.validate(this.orderNumber, true, "category.orderNumber", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
		Category exists = CategoryDAO.getCategory(this.name, this.getCategoryId());
		if (exists != null && exists.getId() != this.getId()) {
			validationError.setFieldError("subCategory.name", "subCategory.name." + ValidationErrors.SUB_CATEGORY_ALREADY_EXISTS);
		}
	}
	
	private void modifySubCategory() throws SQLException {
		Category subCategory = CategoryDAO.getCategory(this.getId());
		subCategory.setName(this.getName());
		subCategory.setOrderNumber(Integer.parseInt(this.orderNumber));
		CategoryDAO.updateCategory(subCategory);
	}
	private void addSubCategory() throws SQLException {
		Category subCategory = new Category();
		subCategory.setParentId(this.getCategoryId());
		subCategory.setName(this.getName());
		subCategory.setOrderNumber(Integer.parseInt(this.orderNumber));
		CategoryDAO.insertCategory(subCategory);
	}
	public void delete(int position) throws SQLException {
		// TODO se puede borrar uno usado?? borrar es que no se puede asociar mas, pero si ya esta fue
		Category subCategory = this.getAllSubCategory().get(position);
		subCategory.setDeleted(true);
		CategoryDAO.logicallyDeleteCategory(subCategory);
	}
	public void reactivate(int position) throws SQLException {
		Category subCategory = this.getAllSubCategory().get(position);
		subCategory.setDeleted(false);
		CategoryDAO.updateCategory(subCategory);
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
