package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.valueobjects.ReferenceDocumentVO;

public class CategoryTreeBuilder {

	private List<CategoryTree> trees = new ArrayList<CategoryTree>();
	
	public void insert(ReferenceDocumentVO documentVO) {
		CategoryTree tree = getTreeFor(documentVO);
		tree.insert(documentVO);
	}
	
	private CategoryTree getTreeFor(ReferenceDocumentVO documentVO) {
		for (CategoryTree documentTypeTree : trees) {
			if (documentTypeTree.getId() == documentVO.getCategoryId()) {
				return documentTypeTree;
			}
		}
		CategoryTree documentTypeTree = new CategoryTree(documentVO);
		trees.add(documentTypeTree);
		return documentTypeTree;
	}

	public List<CategoryTree> getTrees() {
		// TODO sort
		return trees;
	}
}
