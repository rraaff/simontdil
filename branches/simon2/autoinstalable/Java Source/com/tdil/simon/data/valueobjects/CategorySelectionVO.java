package com.tdil.simon.data.valueobjects;

import com.tdil.simon.data.model.Category;

public class CategorySelectionVO extends Category {

	private int selectedId = 0;

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
