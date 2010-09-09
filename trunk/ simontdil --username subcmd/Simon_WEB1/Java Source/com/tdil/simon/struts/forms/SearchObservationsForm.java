package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.mysql.jdbc.StringUtils;
import com.tdil.simon.data.ibatis.CountryDAO;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.ibatis.ParagraphDAO;
import com.tdil.simon.data.model.Country;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.Observation;
import com.tdil.simon.data.model.Paragraph;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.ObservationVO;

public class SearchObservationsForm extends ActionForm {

	private static final long serialVersionUID = -7500638031230915592L;

	private String operation;
	private int versionId;

	private String countryId;

	private String exactDate;

	private String exactDay;
	private String exactMonth;

	private String lowerDay;
	private String lowerMonth;
	private String upperDay;
	private String upperMonth;

	private String paragraphNumber;

	private String words;

	private List observations;

	private List<Paragraph> paragraphs;
	
	private List<Country> allCountries;
	private List<String> allParagraphs;

	private static List<MonthOption> allMonths;
	private static List<DayOption> allDays;

	private SystemUser user;

	static {
		allMonths = new ArrayList<MonthOption>();
		allMonths.add(new MonthOption("1", "Enero"));
		allMonths.add(new MonthOption("2", "Febrero"));
		allMonths.add(new MonthOption("3", "Marzo"));
		allMonths.add(new MonthOption("4", "Abril"));
		allMonths.add(new MonthOption("5", "Mayo"));
		allMonths.add(new MonthOption("6", "Junio"));
		allMonths.add(new MonthOption("7", "Julio"));
		allMonths.add(new MonthOption("8", "Agosto"));
		allMonths.add(new MonthOption("9", "Septiembre"));
		allMonths.add(new MonthOption("10", "Octubre"));
		allMonths.add(new MonthOption("11", "Noviembre"));
		allMonths.add(new MonthOption("12", "Diciembre"));

		allDays = new ArrayList<DayOption>(31);
		for (int i = 1; i <= 31; i++) {
			allDays.add(new DayOption(String.valueOf(i)));
		}
	}
	
	
	public Paragraph getParagraph(int paragraphId) {
		for (Paragraph p : getParagraphs()) {
			if (p.getId() == paragraphId) {
				return p;
			}
		}
		return null;
	}

	public List<MonthOption> getMonths() {
		return allMonths;
	}

	public List<DayOption> getDays() {
		return allDays;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getExactDay() {
		return exactDay;
	}

	public void setExactDay(String exactDay) {
		this.exactDay = exactDay;
	}

	public String getExactMonth() {
		return exactMonth;
	}

	public void setExactMonth(String exactMonth) {
		this.exactMonth = exactMonth;
	}

	public String getLowerDay() {
		return lowerDay;
	}

	public void setLowerDay(String lowerDay) {
		this.lowerDay = lowerDay;
	}

	public String getLowerMonth() {
		return lowerMonth;
	}

	public void setLowerMonth(String lowerMonth) {
		this.lowerMonth = lowerMonth;
	}

	public String getUpperDay() {
		return upperDay;
	}

	public void setUpperDay(String upperDay) {
		this.upperDay = upperDay;
	}

	public String getUpperMonth() {
		return upperMonth;
	}

	public void setUpperMonth(String upperMonth) {
		this.upperMonth = upperMonth;
	}

	public String getParagraphNumber() {
		return paragraphNumber;
	}

	public void setParagraphNumber(String paragraphNumber) {
		this.paragraphNumber = paragraphNumber;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public List getObservations() {
		return observations;
	}

	public void setObservations(List observations) {
		for (Object observationVO : observations) {
			ObservationVO observationVO2 = (ObservationVO)observationVO;
			observationVO2.setParagraph(this.getParagraph(observationVO2.getParagraphId()));
		}
		this.observations = observations;
	}

	public List<Country> getAllCountries() {
		return allCountries;
	}

	public void setAllCountries(List<Country> allCountries) {
		this.allCountries = allCountries;
	}

	public String getExactDate() {
		return exactDate;
	}

	public void setExactDate(String exactDate) {
		this.exactDate = exactDate;
	}

	public List<String> getAllParagraphs() {
		return allParagraphs;
	}

	public void setAllParagraphs(List<String> allParagraphs) {
		this.allParagraphs = allParagraphs;
	}

	public void init() throws SQLException {
		Calendar calendar = Calendar.getInstance();
		this.setExactDay(String.valueOf(calendar.get(Calendar.DATE)));
		this.setExactMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
		this.setUpperDay(this.getExactDay());
		this.setUpperMonth(this.getExactMonth());
		setAllCountries(CountryDAO.selectAllCountries());
		List pars = ParagraphDAO.selectNotDeletedParagraphsFor(this.getVersionId());
		setParagraphs(pars);
		List<String> numbers = new ArrayList<String>();
		for (Object o : pars) {
			numbers.add(String.valueOf(((Paragraph) o).getParagraphNumber()));
		}
		setAllParagraphs(numbers);

	}

	public void search() throws SQLException {
		HashMap params = new HashMap();
		params.put("versionId", new Integer(this.getVersionId()));
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getCountryId())) {
			params.put("countryId", new Integer(this.getCountryId()));
		}
		params.put("dateMin", getDateMin());
		params.put("dateMax", getDateMax());
		if (!StringUtils.isEmptyOrWhitespaceOnly(this.getParagraphNumber())) {
			params.put("paragraphNumber", new Integer(this.getParagraphNumber()));
		}
		List observations = ObservationDAO.searchObservations(params);
		
		List result = new ArrayList();
		if (StringUtils.isEmptyOrWhitespaceOnly(this.words)) {
			result = observations;
		} else {
			String search = this.words.trim().toUpperCase();
			while (search.indexOf("  ") != -1) {
				search = org.apache.commons.lang.StringUtils.replace(search, "  ", " ");
			}
			String wordsToSearch[] = search.split(" ");
			for (Object o : observations) {
				Observation observation = (Observation)o;
				if (includesAny(observation, wordsToSearch)) {
					result.add(observation);
				}
			}
		}
		setObservations(result);
		for (Object o : result) {
			Observation observation = (Observation)o;
			DelegateAuditDAO.registerViewObservation(this.getUser(), observation);
		}
	}

	private boolean includesAny(Observation observation, String[] wordsToSearch) {
		String text = observation.getObservationText().toUpperCase();
		for (String word : wordsToSearch) {
			if (text.indexOf(word) != -1) {
				return true;
			}
		}
		return false;
	}

	private Date getDateMax() {
		if ("exact".equals(this.getExactDate())) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, Integer.valueOf(this.getExactDay()));
			cal.set(Calendar.MONTH, Integer.valueOf(this.getExactMonth()) - 1);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, Integer.valueOf(this.getUpperDay()));
			cal.set(Calendar.MONTH, Integer.valueOf(this.getUpperMonth()) - 1);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		}
	}

	private Date getDateMin() {
		if ("exact".equals(this.getExactDate())) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, Integer.valueOf(this.getExactDay()));
			cal.set(Calendar.MONTH, Integer.valueOf(this.getExactMonth()) - 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			return cal.getTime();
		} else {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.DATE, Integer.valueOf(this.getLowerDay()));
			cal.set(Calendar.MONTH, Integer.valueOf(this.getLowerMonth()) - 1);
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 999);
			return cal.getTime();
		}
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
}
