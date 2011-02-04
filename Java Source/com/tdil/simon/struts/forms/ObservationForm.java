package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.Date;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.SystemUser;

public class ObservationForm extends ActionForm {

	
	private static final long serialVersionUID = 44784135736887042L;

	private String paragraphNumber;
	private String paragraphNumberForDiplay;

	private String newParagraph;
	private String paragraphText;
	private String versionId;
	private Date creationDate = new Date();
	private SystemUser user;
	
	public String getParagraphNumber() {
		return paragraphNumber;
	}
	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}
	public String getNewParagraph() {
		return newParagraph;
	}
	public void setNewParagraph(String newParagraph) {
		this.newParagraph = newParagraph;
	}
	public String getParagraphText() {
		return paragraphText;
	}
	public void setParagraphText(String paragraphText) {
		this.paragraphText = paragraphText;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public SystemUser getUser() {
		return user;
	}
	public void setUser(SystemUser user) {
		this.user = user;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getParagraphNumberForDiplay() {
		return paragraphNumberForDiplay;
	}
	public void setParagraphNumberForDiplay(String paragraphNumberForDiplay) {
		this.paragraphNumberForDiplay = paragraphNumberForDiplay;
	}
	public void addUpdatePortugues() throws SQLException {
		Integer versionId = Integer.valueOf(this.getVersionId());
		Integer number = Integer.valueOf(this.getParagraphNumber());
		String text = this.getParagraphText();
		Paragraph paragraph = ParagraphDAO.getParagraph(versionId, number);
		Observation observation = ObservationDAO.getPortuguesFor(paragraph.getId());
		if (observation != null) {
			observation.setObservationText(text);
			ObservationDAO.updatePortuguesText(observation);
		} else {
			observation = new Observation();
			observation.setParagraphId(paragraph.getId());
			observation.setPrivateObservation(true);
			observation.setAddNewParagraph(false);
			observation.setUserId(this.getUser().getId());
			observation.setObservationText(text);
			observation.setPortuguesTranslation(true);
			observation.setDeleted(false);
			ObservationDAO.insertObservation(observation);
		}
	}
	public Observation getPortuguesTranslation() throws SQLException {
		Integer versionId = Integer.valueOf(this.getVersionId());
		Integer number = Integer.valueOf(this.getParagraphNumber());
		Paragraph paragraph = ParagraphDAO.getParagraph(versionId, number);
		Observation observation = ObservationDAO.getPortuguesFor(paragraph.getId());
		return observation;
	}
}
