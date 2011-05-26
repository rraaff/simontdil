package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.GroupPermission;

public class SubCategoryPermissionVO extends GroupPermission {

	private String categoryName;
	private String subCategoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
}
