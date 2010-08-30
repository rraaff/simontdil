package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.Version;

public class ParagraphDAO {

	public static List selectAllParagraphsFor(int versionID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllParagraphsForVersion", versionID);
	}
	
	public static List selectNotDeletedParagraphsFor(int versionID) throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllParagraphsForVersionNotDeleted", versionID);
	}

	public static Paragraph getParagraph(int id) throws SQLException {
		return (Paragraph) IBatisManager.sqlMapper.queryForObject(
				"selectParagraphById", id);
	}
	
	public static Paragraph getParagraph(Version version, int number) throws SQLException {
		return getParagraph(version.getId(), number);
	}
	
	public static Paragraph getParagraph(int version, int number) throws SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("versionId", version);
		params.put("paragraphNumber", number);
		return (Paragraph) IBatisManager.sqlMapper.queryForObject(
				"selectParagraphByVersionAndNumber", params);
	}
	
	public static Integer insertParagraph(Paragraph paragraph) throws SQLException {
		return (Integer) IBatisManager.sqlMapper.insert("insertParagraph", paragraph);
	}
	
	public static void updateParagraph(Paragraph Paragraph)
			throws SQLException {
		IBatisManager.sqlMapper.update("updateParagraph", Paragraph);
	}

	public static void logicallyDeleteParagraph(Paragraph Paragraph)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteParagraph", Paragraph);
	}
}
