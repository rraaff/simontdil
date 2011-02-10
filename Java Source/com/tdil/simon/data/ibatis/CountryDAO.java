package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.model.Country;

public class CountryDAO {

	// TODO VER los selects que no se bajen la banderaaa
	public static List selectAllCountries() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCountries");
	}
	
	public static List selectAllCountriesVO() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCountriesVO");
	}
	
	public static List selectNotDeletedCountries() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCountriesNotDeleted");
	}
	
	public static List selectNotDeletedCountriesVO() throws SQLException {
		return IBatisManager.sqlMapper.queryForList("selectAllCountriesVONotDeleted");
	}

	public static Country getCountryHost() throws SQLException {
		return (Country) IBatisManager.sqlMapper.queryForObject(
				"selectCountryHost");
	}
	
	public static Country getCountry(int id) throws SQLException {
		return (Country) IBatisManager.sqlMapper.queryForObject(
				"selectCountryById", id);
	}
	
	public static Country getCountryWithFlag(int id) throws SQLException {
		return (Country) IBatisManager.sqlMapper.queryForObject(
				"selectCountryByIdWithFlag", id);
	}

	public static Country getCountry(String name) throws SQLException {
		return (Country) IBatisManager.sqlMapper.queryForObject(
				"selectCountryByName", name);
	}

	
	public static Integer insertCountry(Country country) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertCountry", country);
	}

	public static void updateCountry(Country country) throws SQLException {
		IBatisManager.sqlMapper.update("updateCountry", country);
	}

	public static void logicallyDeleteCountry(Country country)
			throws SQLException {
		IBatisManager.sqlMapper.update("logDeleteCountry", country);
	}


}
