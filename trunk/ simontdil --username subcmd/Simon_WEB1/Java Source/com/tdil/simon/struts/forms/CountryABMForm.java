package com.tdil.simon.struts.forms;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.valueobjects.CountryVO;
import com.tdil.simon.utils.UploadUtils;
import com.tdil.simon.web.SystemConfig;

public class CountryABMForm extends ActionForm {

	private static final long serialVersionUID = -8744930020582842123L;
	
	private String operation;
	
	private int id;
	private String name;
	private FormFile flag;
	
	private List<CountryVO> allCountries;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FormFile getFlag() {
		return flag;
	}
	public void setFlag(FormFile myFile) {
		this.flag = myFile;
	}
	public List<CountryVO> getAllCountries() {
		return allCountries;
	}
	public void setAllCountries(List<CountryVO> allCountries) {
		this.allCountries = allCountries;
	}
	
	public void reset() throws SQLException {
		this.id = 0;
		this.name = null;
		this.flag = null;
	}
	public void init() throws SQLException {
		this.setAllCountries(CountryDAO.selectAllCountriesVO());
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public void initWith(int userId) throws SQLException {
		Country country = CountryDAO.getCountry(userId);
		if (country != null) {
			this.id = userId;
			this.name = country.getName();
		}
	}
	
	public void save() throws SQLException, FileNotFoundException, IOException {
		if (id == 0) {
			this.addCountry();
		} else {
			this.modifyCountry();
		}
	}
	private void modifyCountry() throws SQLException, FileNotFoundException, IOException {
		Country country = CountryDAO.getCountry(this.getId());
		country.setName(this.getName());
		CountryDAO.updateCountry(country);
		UploadUtils.uploadFileTo(this.flag, SystemConfig.getFlagStore() + "/" + this.getId() + ".png");
	}
	private void addCountry() throws SQLException, FileNotFoundException, IOException {
		Country country = new Country();
		country.setName(this.getName());
		int countryId = CountryDAO.insertCountry(country);
		UploadUtils.uploadFileTo(this.flag, SystemConfig.getFlagStore() + "/" + countryId + ".png");
	}
	public void reactivate(int position) throws SQLException {
		Country country = this.getAllCountries().get(position);
		country.setDeleted(false);
		CountryDAO.updateCountry(country);
	}
	public void delete(int position) throws SQLException {
		Country country = this.getAllCountries().get(position);
		country.setDeleted(true);
		CountryDAO.logicallyDeleteCountry(country);
	}
}
