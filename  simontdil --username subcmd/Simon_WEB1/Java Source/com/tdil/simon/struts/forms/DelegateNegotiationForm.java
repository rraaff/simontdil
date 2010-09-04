package com.tdil.simon.struts.forms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Signature;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionVO;

public class DelegateNegotiationForm extends ActionForm {

	private static final long serialVersionUID = 8448596721494052333L;

	private SystemUser user;
	private VersionVO versionVO;
	private HttpServletRequest request;
	private List<SignatureVO> signatures = new ArrayList<SignatureVO>();
	private boolean goToSignShow;

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void init() throws SQLException {
		VersionVO versionVO = new VersionVO();
		Version version = VersionDAO.getVersionUnderWork();
		versionVO.setVersion(version);
		if (version != null) {
			versionVO.setDocument(DocumentDAO.getDocument(version.getDocumentId()));
			versionVO.setParagraphs(ParagraphDAO.selectAllParagraphsFor(version.getId()));
		}
		setVersionVO(versionVO);
		if (Version.IN_SIGN.equals(version.getStatus())) {
			if (!user.isCanSign()) {
				goToSignShow = true;
				return;
			}
			if (SignatureDAO.exist(version.getId(), user.getId())) {
				goToSignShow = true;
				return;
			} 
		} 
	}

	public VersionVO getVersionVO() {
		return versionVO;
	}

	public void setVersionVO(VersionVO versionVO) {
		this.versionVO = versionVO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void sign() throws SQLException, IOException {
		Signature signature = new Signature();
		signature.setUserId(this.getUser().getId());
		signature.setVersionId(this.getVersionVO().getVersion().getId());
		signature.setDeleted(false);
		SignatureDAO.insertSignature(signature);
		InputStream input = request.getInputStream();
		// TODO deshardcodear
		FileOutputStream fout = new FileOutputStream(signature.getSignatureFileName());
		FileOutputStream fout2 = new FileOutputStream("C:/icarus/workspace/simon/Simon_WEB/Web Content/signatures/" + signature.getSignatureFileName());
		try {
			IOUtils.copy(input, fout);
			fout.close();
			FileInputStream finput = new FileInputStream(signature.getSignatureFileName());
			IOUtils.copy(finput, fout2);
		} finally {
			try {
				fout2.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public List<SignatureVO> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<SignatureVO> signatures) {
		this.signatures = signatures;
	}

	public void searchSignatures() throws SQLException {
		setSignatures(SignatureDAO.selectSignaturesFor(this.getVersionVO().getVersion().getId()));
	}

	public boolean contains(SignatureVO signatureVO) {
		for (SignatureVO signatureVO2 : getSignatures()) {
			if (signatureVO2.getUserId() == signatureVO.getUserId()) {
				return true;
			}
		}
		return false;
	}

	public boolean isGoToSignShow() {
		return goToSignShow;
	}

	public void setGoToSignShow(boolean goToSignShow) {
		this.goToSignShow = goToSignShow;
	}
	
}