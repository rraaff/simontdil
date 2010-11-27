package com.tdil.simon.data.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Version;


public class VersionVO {

	public Document document;
	public Version version;
	public List<VersionNumberVO> allVersions;
	public List<Paragraph> paragraphs;

	public List<VersionNumberVO> getReducedVersions() {
		setAllAsNotCurrent(allVersions);
		int position = getPositionFor(version.getNumber());
		allVersions.get(position).setCurrent(true);
		if (allVersions.size() <= 7) {
			return allVersions;
		}
		if (position == 0) {
			return allVersions.subList(0, 7);
		}
		if (position == allVersions.size() - 1) {
			return allVersions.subList(allVersions.size() - 7, allVersions.size());
		}
		List<VersionNumberVO> after = new ArrayList<VersionNumberVO>();
		int afterIndex;
		for (afterIndex = position + 1; afterIndex < allVersions.size() && afterIndex <= position + 4; afterIndex++) {
			after.add(allVersions.get(afterIndex));
		}
		List<VersionNumberVO> before = new ArrayList<VersionNumberVO>();
		int beforeIndex;
		for (beforeIndex = position - 1; beforeIndex >= 0 && beforeIndex >= position - 3; beforeIndex--) {
			before.add(0, allVersions.get(beforeIndex));
		}
		if (after.size() < 3) {
			int toAdd = 3 - after.size();
			for (int i = 0; i < toAdd; i++) {
				before.add(0, allVersions.get(beforeIndex--));
			}
		} else {
			if (before.size() < 3) {
				int toAdd = 3 - before.size();
				for (int i = 0; i < toAdd; i++) {
					after.add(allVersions.get(afterIndex++));
				}
			}
		}
		List<VersionNumberVO> result = new ArrayList<VersionNumberVO>();
		result.addAll(before);
		result.add(allVersions.get(position));
		result.addAll(after);
		return result;
	}
	
	
	
	private void setAllAsNotCurrent(List<VersionNumberVO> allVersions2) {
		for (VersionNumberVO v : allVersions2) {
			v.setCurrent(false);
		}
	}



	private int getPositionFor(int number) {
		int index = 0;
		for (VersionNumberVO versionNumberVO : allVersions) {
			if (versionNumberVO.getNumber() == number) {
				return index;
			}
			index = index + 1;
		}
		return 0;
	}



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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


}
