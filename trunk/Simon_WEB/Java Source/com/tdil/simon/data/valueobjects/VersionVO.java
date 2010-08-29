package com.tdil.simon.data.valueobjects;

import java.util.List;

import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Version;


public class VersionVO {

	public Version version;
	public List<VersionNumberVO> allVersions;
	public List<Paragraph> paragraphs;

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<VersionNumberVO> getAllVersions() {
		return allVersions;
	}

	public void setAllVersions(List<VersionNumberVO> allVersions) {
		this.allVersions = allVersions;
	}


}
