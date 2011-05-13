package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.SubCategory;

public class SubCategoryVO extends SubCategory {

	private int selectedId = 0;
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public boolean getSelected() {
		return this.getId() == this.getSelectedId();
	}

	public int getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(int selectedId) {
		this.selectedId = selectedId;
	}

}
