package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;

public class CategoryTree {

	private int id;
	private String name;
	private List<SubCategoryTree> documentSubTypes = new ArrayList<SubCategoryTree>();
	
	public CategoryTree(ReferenceDocumentVO documentVO) {
		super();
		setId(documentVO.getCategoryId());
		setName(documentVO.getCategoryName());
	}

	public void insert(ReferenceDocumentVO documentVO) {
		SubCategoryTree tree = getTreeFor(documentVO);
		tree.insert(documentVO);
	}
	
	private SubCategoryTree getTreeFor(ReferenceDocumentVO documentVO) {
		for (SubCategoryTree documentSubTypeTree : documentSubTypes) {
			if (documentSubTypeTree.getId() == documentVO.getSubCategoryId()) {
				return documentSubTypeTree;
			}
		}
		SubCategoryTree documentSubTypeTree = new SubCategoryTree(documentVO);
		documentSubTypes.add(documentSubTypeTree);
		return documentSubTypeTree;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String documentTypeName) {
		this.name = documentTypeName;
	}
	public List<SubCategoryTree> getDocumentSubTypes() {
		return documentSubTypes;
	}
	public void setDocumentSubTypes(List<SubCategoryTree> documentSubTypes) {
		this.documentSubTypes = documentSubTypes;
	}
	public int getId() {
		return id;
	}
	public void setId(int documentTypeId) {
		this.id = documentTypeId;
	}

	public void fixSubTypesIds() {
		for (SubCategoryTree subTypeTree : documentSubTypes) {
			subTypeTree.setId(subTypeTree.getId() + 1000000);
		}
		
	}

	
}

