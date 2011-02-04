package com.tdil.simon.struts.forms;

import com.tdil.simon.data.valueobjects.SignatureVO;

public class SignatureRow {

	private SignatureVO left;
	private SignatureVO right;
	
	public SignatureVO getLeft() {
		return left;
	}
	public void setLeft(SignatureVO left) {
		this.left = left;
	}
	public SignatureVO getRight() {
		return right;
	}
	public void setRight(SignatureVO right) {
		this.right = right;
	}
	
	public boolean getHasRight() {
		return this.right != null;
	}
}
