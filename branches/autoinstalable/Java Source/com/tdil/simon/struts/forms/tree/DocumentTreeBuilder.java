package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.valueobjects.DocumentVO;

public class DocumentTreeBuilder {

	private List<DocumentTypeTree> trees = new ArrayList<DocumentTypeTree>();
	
	public void insert(DocumentVO documentVO) {
		DocumentTypeTree tree = getTreeFor(documentVO);
		tree.insert(documentVO);
	}
	
	private DocumentTypeTree getTreeFor(DocumentVO documentVO) {
		for (DocumentTypeTree documentTypeTree : trees) {
			if (documentTypeTree.getId() == documentVO.getDocumentTypeId()) {
				return documentTypeTree;
			}
		}
		DocumentTypeTree documentTypeTree = new DocumentTypeTree(documentVO);
		trees.add(documentTypeTree);
		return documentTypeTree;
	}

	public List<DocumentTypeTree> getTrees() {
		// TODO sort
		return trees;
	}
}
