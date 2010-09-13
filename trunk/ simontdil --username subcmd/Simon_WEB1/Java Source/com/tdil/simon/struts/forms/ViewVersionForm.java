package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import sun.jdbc.odbc.OdbcDef;

import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.ibatis.SignatureDAO;
import com.tdil.simon.data.ibatis.SiteDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Site;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.data.valueobjects.SignatureVO;
import com.tdil.simon.data.valueobjects.VersionNumberVO;
import com.tdil.simon.data.valueobjects.VersionVO;

public class ViewVersionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4101468206992176127L;
	
	private SystemUser user;
	private String operation;
	private VersionVO version;
	private List observations;
	private List<SignatureVO> signatures = new ArrayList<SignatureVO>();

	public VersionVO getVersion() {
		return version;
	}

	public void setVersion(VersionVO version) {
		this.version = version;
	}

	public List<SignatureVO> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<SignatureVO> signatures) {
		this.signatures = signatures;
	}
	
	public void init(int versionID) throws SQLException {
		Version version = VersionDAO.getVersion(versionID);
		VersionVO versionVO = new VersionVO();
		versionVO.setVersion(version);
		versionVO.setAllVersions(VersionDAO.getAllVersionNumbersFor(version.getDocumentId()));
		versionVO.setParagraphs(ParagraphDAO.selectNotDeletedParagraphsFor(versionID));
		versionVO.setDocument(DocumentDAO.getDocument(version.getDocumentId()));
		setVersion(versionVO);
		if (version.isFinal()) {
			setSignatures(SignatureDAO.selectSignaturesFor(version.getId()));
		}
	}
	
	public String getParagraphText(int observationId) {
		ObservationVO observationVO = (ObservationVO)this.getObservations().get(observationId);
		for (Paragraph p : this.getVersion().getParagraphs()) {
			if (p.getParagraphNumber() == observationVO.getParagraphNumber()) {
				return p.getParagraphText();
			}
		}
		return "";
	}
	
	public Paragraph getParagraph(int paragraphId) {
		for (Paragraph p : this.getVersion().getParagraphs()) {
			if (p.getId() == paragraphId) {
				return p;
			}
		}
		return null;
	}
	
	public String getParagraphText(Object object) {
		ObservationVO observationVO = (ObservationVO)object;
		for (Paragraph p : this.getVersion().getParagraphs()) {
			if (p.getParagraphNumber() == observationVO.getParagraphNumber()) {
				return p.getParagraphText();
			}
		}
		return "";
	}
	
	public boolean getVersionCanBeEdited() {
		return this.getVersion().getVersion().canBeEdited();
	}
	
	
	public boolean getVersionIsInSign() {
		return Version.IN_SIGN.equals(this.getVersion().getVersion().getStatus());
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
	
	public boolean getHasObservations() {
		return this.getObservations() != null && this.getObservations().size() > 0;
	}

	public void setObservations(List observations) {
		for (Object observationVO : observations) {
			ObservationVO observationVO2 = (ObservationVO)observationVO;
			observationVO2.setParagraph(this.getParagraph(observationVO2.getParagraphId()));
		}
		this.observations = observations;
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public void finishSign(int id) throws SQLException {
		Version version = VersionDAO.getVersion(id);
		version.setStatus(Version.FINAL);
		VersionDAO.updateVersionStatus(version);
		Site site = SiteDAO.getSite(Site.DELEGATE);
		site.setStatus(Site.NORMAL);
		site.setDataId(0);
		SiteDAO.updateSite(site);
	}

	public boolean isFinal() {
		return this.getVersion().getVersion().isFinal();
	}

}
