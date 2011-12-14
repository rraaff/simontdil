package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.GroupPermission;

public class CategoryPermissionVO extends GroupPermission {

	private String categoryName;
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
