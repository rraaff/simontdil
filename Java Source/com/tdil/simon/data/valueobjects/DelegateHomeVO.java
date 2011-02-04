package com.tdil.simon.data.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.Document;

public class DelegateHomeVO {

	private Document principal;
	private List<Document> secundary = new ArrayList<Document>();
	private List<CategoryVO> categories;
	
	public Document getPrincipal() {
		return principal;
	}
	public void setPrincipal(Document principal) {
		this.principal = principal;
	}
	public List<Document> getSecundary() {
		return secundary;
	}
	public void setSecundary(List<Document> secundary) {
		this.secundary = secundary;
	}
	public List<CategoryVO> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryVO> categories) {
		this.categories = categories;
	}
}
