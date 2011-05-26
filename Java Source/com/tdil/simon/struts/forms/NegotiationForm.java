package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionVO;
import com.tdil.simon.utils.LoggerProvider;

public class NegotiationForm extends ActionForm {

	private static final long serialVersionUID = 8448596721494052333L;

	private SystemUser user;
	private VersionVO version;
	
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
			versionVO.setParagraphs(ParagraphDAO.selectNotDeletedParagraphsFor(version.getId()));
		}
		setVersion(versionVO);
	}

	public VersionVO getVersion() {
		return version;
	}

	public void setVersion(VersionVO versionVO) {
		this.version = versionVO;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	private static Logger getLog() {
		return LoggerProvider.getLogger(NegotiationForm.class);
	}

}
