package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.valueobjects.DocumentVO;

public class DocumentTypeTree {

	private int id;
	private String name;
	private List<Document> documents = new ArrayList<Document>();
	private List<DocumentSubTypeTree> documentSubTypes = new ArrayList<DocumentSubTypeTree>();
	
	public DocumentTypeTree(DocumentVO documentVO) {
		super();
		setId(documentVO.getDocumentTypeId());
		setName(documentVO.getDocumentTypeName());
	}

	public void insert(DocumentVO documentVO) {
		if (StringUtils.isEmpty(documentVO.getDocumentSubTypeName())) {
			documents.add(documentVO);
		} else {
			DocumentSubTypeTree tree = getTreeFor(documentVO);
			tree.insert(documentVO);
		}
	}
	
	private DocumentSubTypeTree getTreeFor(DocumentVO documentVO) {
		for (DocumentSubTypeTree documentSubTypeTree : documentSubTypes) {
			if (documentSubTypeTree.getName().equals(documentVO.getDocumentSubTypeName())) {
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

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	
}

