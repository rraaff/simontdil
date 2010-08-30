package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionVO;

public class DelegateNegotiationForm extends ActionForm {

	private static final long serialVersionUID = 8448596721494052333L;

	private SystemUser user;
	private VersionVO versionVO;


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
	
}
