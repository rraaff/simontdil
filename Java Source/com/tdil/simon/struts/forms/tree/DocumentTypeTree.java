package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.valueobjects.DocumentVO;

public class DocumentTypeTree {

	private int id;
	private String name;
	private List<DocumentSubTypeTree> documentSubTypes = new ArrayList<DocumentSubTypeTree>();
	
	public DocumentTypeTree(DocumentVO documentVO) {
		super();
		setId(documentVO.getDocumentTypeId());
		setName(documentVO.getDocumentTypeName());
	}

	public void insert(DocumentVO documentVO) {
		DocumentSubTypeTree tree = getTreeFor(documentVO);
		tree.insert(documentVO);
	}
	
	private DocumentSubTypeTree getTreeFor(DocumentVO documentVO) {
		for (DocumentSubTypeTree documentSubTypeTree : documentSubTypes) {
			if (documentSubTypeTree.getId() == documentVO.getDocumentSubTypeId()) {
				return documentSubTypeTree;
			}
		}
		DocumentSubTypeTree documentSubTypeTree = new DocumentSubTypeTree(documentVO);
		documentSubTypes.add(documentSubTypeTree);
		return documentSubTypeTree;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String documentTypeName) {
		this.name = documentTypeName;
	}
	public List<DocumentSubTypeTree> getDocumentSubTypes() {
		return documentSubTypes;
	}
	public void setDocumentSubTypes(List<DocumentSubTypeTree> documentSubTypes) {
		this.documentSubTypes = documentSubTypes;
	}
	public int getId() {
		return id;
	}
	public void setId(int documentTypeId) {
		this.id = documentTypeId;
	}

	public void fixSubTypesIds() {
		for (DocumentSubTypeTree subTypeTree : documentSubTypes) {
			subTypeTree.setId(subTypeTree.getId() + 1000000);
		}
		
	}

	
}

