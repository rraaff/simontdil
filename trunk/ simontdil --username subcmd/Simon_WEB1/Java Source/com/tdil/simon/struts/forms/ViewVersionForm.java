package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.VersionNumberVO;
import com.tdil.simon.data.valueobjects.VersionVO;

public class ViewVersionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4101468206992176127L;
	
	private String operation;
	private VersionVO version;
	private List observations;

	public VersionVO getVersion() {
		return version;
	}

	public void setVersion(VersionVO version) {
		this.version = version;
	}

	public void init(int versionID) throws SQLException {
		Version version = VersionDAO.getVersion(versionID);
		VersionVO versionVO = new VersionVO();
		versionVO.setVersion(version);
		versionVO.setAllVersions(VersionDAO.getAllVersionNumbersFor(version.getDocumentId()));
		versionVO.setParagraphs(ParagraphDAO.selectAllParagraphsFor(versionID));
		versionVO.setDocument(DocumentDAO.getDocument(version.getDocumentId()));
		setVersion(versionVO);
	}
	
	public boolean getVersionCanBeEdited() {
		return this.getVersion().getVersion().canBeEdited();
	}
	
	
	public boolean getVersionIsInSign() {
		return Version.IN_SIGN.equals(this.getVersion().getVersion());
	}
	
	public boolean getVersionCanBeNegotiated() {
		if (this.getVersion().getDocument().isDeleted()) {
			return false;
		}
		if (this.getVersion().getVersion().isDeleted()) {
			return false;
		}
		try {
			if(!Site.EVENT.equals(Site.getMODERATOR_SITE().getStatus())) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (this.getVersion().getDocument().isPrincipal()) {
			Version version = this.getVersion().getVersion();
			if (Version.CONSOLIDATED.equals(version.getStatus())) {
				int max = getMaxConsolidatedVersion();
				return version.getNumber() == max;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean getConsolidated() {
		return Version.CONSOLIDATED.equals(this.getVersion().getVersion());
	}
	
	public boolean getVersionCanBeCommented() {
		if (this.getVersion().getDocument().isDeleted()) {
			return false;
		}
		if (this.getVersion().getVersion().isDeleted()) {
			return false;
		}
		Version version = this.getVersion().getVersion();
		
		if (Version.CONSOLIDATED.equals(version.getStatus())) {
			Date today = new Date();
			if (today.after(version.getUpToCommentDate())) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	private int getMaxConsolidatedVersion() {
		int max = 0;
		for (VersionNumberVO versionVO : getVersion().getAllVersions()) {
			if (versionVO.getNumber() > max) {
				max = versionVO.getNumber();
			}
		}
		return max;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List getObservations() {
		return observations;
	}

	public void setObservations(List observations) {
		this.observations = observations;
	}

}
