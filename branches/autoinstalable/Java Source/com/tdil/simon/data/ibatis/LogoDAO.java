package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.Logo;

public class LogoDAO {

	/*List<ReferenceDocumentVO>*/
	public static List selectAllLogo() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllLogos");
	}
	
	public static Logo getLogo(String key) throws SQLException {
		return (Logo) IBatisManager.sqlMapper.queryForObject(
				"selectLogoByKey", key);
	}
	
	public static Integer insertLogo(Logo logo) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertLogo", logo);
	}
	
	public static void updateLogo(Logo logo) throws SQLException {
		IBatisManager.sqlMapper.update("updateLogo", logo);
	}

}
