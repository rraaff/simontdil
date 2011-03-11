package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.valueobjects.LastLoginVO;
import com.tdil.simon.data.valueobjects.StatisticsVO;
import com.tdil.simon.web.SystemConfig;

public class StatisticsForm extends ActionForm {

	private static final long serialVersionUID = -1910932863219835678L;
	
	private StatisticsVO statisticsVO;

	public StatisticsVO getStatisticsVO() {
		return statisticsVO;
	}

	public void setStatisticsVO(StatisticsVO statisticsVO) {
		this.statisticsVO = statisticsVO;
	}

	public void init() throws SQLException {
		StatisticsVO statisticsVO = new StatisticsVO();
		statisticsVO.init();
		setStatisticsVO(statisticsVO);
		
	}
	
	public String getCountryCount() {
		return String.valueOf(this.getStatisticsVO().getAllCountries().size());
	}
	
	public List<String> getLastLogins() {
		DateFormat lastLoginFormat = SystemConfig.getDateFormatWithMinutes();
		List<String> result = new ArrayList<String>();
		for (Country country : statisticsVO.getAllCountries()) {
			Date date = this.getLastLoginFor(country.getId());
			if (date == null) {
				result.add("-");
			} else {
				result.add(lastLoginFormat.format(date));	
			}
			
		}
		return result;
	}

	private Date getLastLoginFor(int id) {
		for (LastLoginVO lastLogin : statisticsVO.getLastLogins()) {
			if (lastLogin.getCountryId() == id) {
				return lastLogin.getLastLogin();
			}
		}
		return null;
	}
	
}
