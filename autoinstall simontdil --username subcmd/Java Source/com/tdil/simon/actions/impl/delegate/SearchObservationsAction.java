package com.tdil.simon.actions.impl.delegate;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.tdil.simon.actions.AbstractAction;
import com.tdil.simon.actions.TransactionalAction;
import com.tdil.simon.actions.UserTypeValidation;
import com.tdil.simon.actions.response.ActionResponse;
import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.FieldValidation;
import com.tdil.simon.actions.validations.IdValidation;
import com.tdil.simon.data.ibatis.DelegateAuditDAO;
import com.tdil.simon.data.ibatis.ObservationDAO;
import com.tdil.simon.data.model.DelegateAudit;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.valueobjects.ObservationVO;
import com.tdil.simon.database.TransactionProvider;

public class SearchObservationsAction extends AbstractAction implements TransactionalAction {

	private String countryId;
	private Integer countryIdInt;
	private String date;
	private Date dateDate;
	private String dateMin;
	private Date dateMinDate;
	private String dateMax;
	private Date dateMaxDate;
	private String paragraphNumber;
	private Integer paragraphNumberInt;
	
	private String documentId;
	private String version;
	private String text; // TODO esto es listado separado por comas o algo asi?
	
	private SystemUser user;
	
	@Override
	protected ActionResponse basicExecute(HttpServletRequest req)
			throws ValidationException, SQLException {
		TransactionProvider.executeInTransaction(this);
		return ActionResponse.newOKResponse(this.getResponseData());
	}
	
	public void executeInTransaction() throws SQLException, ValidationException {
		HashMap params = new HashMap();
		params.put("countryId", countryIdInt);
		params.put("dateMin", dateMinDate);
		params.put("dateMax", dateMaxDate);
		params.put("paragraphNumber", paragraphNumberInt);
		List observations = ObservationDAO.searchObservations(params);
		if (this.user.isDelegate()) {
			for (Object o : observations) {
				ObservationVO vo = (ObservationVO)o;
				DelegateAuditDAO.registerAction(this.user.getId(), this.user.getCountryId(), vo.getId(), DelegateAudit.VIEW_OBSERVATION);
			}
		}
		this.setResponseData(observations);
	}

	@Override
	protected void basicValidate(HttpServletRequest req,
			ValidationError validation) {
		this.countryIdInt = IdValidation.validate(this.countryId, false, "countryId", validation);
		this.dateDate = FieldValidation.validateDate(this.date, "date", false, validation);
		this.dateMinDate = FieldValidation.validateDate(this.dateMin, "dateMin", false, validation);
		this.dateMaxDate = FieldValidation.validateDate(this.dateMax, "dateMax", false, validation);
		this.paragraphNumberInt = IdValidation.validate(this.paragraphNumber, false, "paragraphNumber", validation);
		if (this.dateDate != null) {
			Calendar min = Calendar.getInstance();
			min.setTime(this.dateDate);
			min.set(Calendar.HOUR_OF_DAY, 0);
			min.set(Calendar.MINUTE, 0);
			min.set(Calendar.SECOND, 0);
			min.set(Calendar.MILLISECOND, 0);
			this.dateMinDate = min.getTime();
			
			Calendar max = Calendar.getInstance();
			max.setTime(this.dateDate);
			max.set(Calendar.HOUR_OF_DAY, 23);
			max.set(Calendar.MINUTE, 59);
			max.set(Calendar.SECOND, 59);
			max.set(Calendar.MILLISECOND, 999);
			this.dateMaxDate = max.getTime();
		} else {
			if (this.dateMaxDate != null) {
				Calendar max = Calendar.getInstance();
				max.setTime(this.dateMaxDate);
				max.set(Calendar.HOUR_OF_DAY, 23);
				max.set(Calendar.MINUTE, 59);
				max.set(Calendar.SECOND, 59);
				max.set(Calendar.MILLISECOND, 999);
				this.dateMaxDate = max.getTime();
			}
			if (this.dateMinDate != null) {
				Calendar min = Calendar.getInstance();
				min.setTime(this.dateMinDate);
				min.set(Calendar.HOUR_OF_DAY, 0);
				min.set(Calendar.MINUTE, 0);
				min.set(Calendar.SECOND, 0);
				min.set(Calendar.MILLISECOND, 0);
				this.dateMinDate = min.getTime();
			}
		}
	}

	@Override
	protected UserTypeValidation getUserTypeValidation() {
		return UserTypeValidation.DELEGATE;
	}

	@Override
	public void takeValuesFrom(HttpServletRequest req) {
		this.countryId = req.getParameter("countryId");
		this.date = req.getParameter("date");
		this.dateMin = req.getParameter("dateMin");
		this.dateMax = req.getParameter("dateMax");
		this.paragraphNumber = req.getParameter("paragraphNumber");
		this.text = req.getParameter("text");
		this.user = (SystemUser)req.getSession().getAttribute("user");
	}

}
