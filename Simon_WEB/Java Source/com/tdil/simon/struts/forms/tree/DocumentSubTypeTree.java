package com.tdil.simon.struts.forms.tree;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.valueobjects.DocumentVO;

public class DocumentSubTypeTree {

	private int parentId;
	private int id;
	private String name;
	private List<Document> documents = new ArrayList<Document>();
	
	public DocumentSubTypeTree(DocumentVO documentVO) {
		super();
		setParentId(documentVO.getDocumentTypeId());
		setId(documentVO.getDocumentSubTypeId());
		setName(documentVO.getDocumentSubTypeName());
	}
	
	public void insert(DocumentVO documentVO) {
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
	public List<Document> getDocuments() {
		return documents;
	}
	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int documentTypeId) {
		this.parentId = documentTypeId;
	}
	
}

