package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;

public class SubCategoryTree {

	private int parentId;
	private int id;
	private String name;
	private List<ReferenceDocumentVO> documents = new ArrayList<ReferenceDocumentVO>();
	
	public SubCategoryTree(ReferenceDocumentVO documentVO) {
		super();
		setParentId(documentVO.getCategoryId());
		setId(documentVO.getSubCategoryId());
		setName(documentVO.getSubCategoryName());
	}
	
	public void insert(ReferenceDocumentVO documentVO) {
		documents.add(documentVO);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int documentSubTypeId) {
		this.id = documentSubTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String documentSubTypeName) {
		this.name = documentSubTypeName;
	}
	public List<ReferenceDocumentVO> getDocuments() {
		return documents;
	}
	public void setDocuments(List<ReferenceDocumentVO> documents) {
		this.documents = documents;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int documentTypeId) {
		this.parentId = documentTypeId;
	}
	
}

