package com.tdil.simon.struts.forms;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

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
import com.tdil.simon.data.valueobjects.VersionVO;

public class DelegateNegotiationForm extends ActionForm {

	private static final long serialVersionUID = 8448596721494052333L;

	private SystemUser user;
	private VersionVO versionVO;
	private HttpServletRequest request;

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
		FileOutputStream fout = new FileOutputStream(signature.getUserId() + "_" + signature.getVersionId() +".png");
		try {
			IOUtils.copy(input, fout);
		} catch (Exception e) {
			fout.close();
			e.printStackTrace();
		}
		
	}
	
}
