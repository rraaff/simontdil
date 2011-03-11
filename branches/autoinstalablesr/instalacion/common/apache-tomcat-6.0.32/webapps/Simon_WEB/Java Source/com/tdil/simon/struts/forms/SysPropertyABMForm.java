package com.tdil.simon.struts.forms;

import java.sql.SQLException;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.data.ibatis.SysPropertiesDAO;
import com.tdil.simon.data.model.SysProperties;

public class SysPropertyABMForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String key;
	private String value;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#reset()
	 */
	public void reset() throws SQLException {
		this.id = 0;
		this.key = "";
		this.value = "";
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
	}
	
	public void initWith(String key) throws SQLException {
		String prop = SysPropertiesDAO.getPropertyByKey(key);
		if (prop != null) {
			this.key = key;
			this.value = prop;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		this.modifyProperty();
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	
	private void modifyProperty() throws SQLException {
		SysProperties sysProperties = new SysProperties();
		sysProperties.setPropKey(this.getKey());
		sysProperties.setPropValue(this.getValue());
		SysPropertiesDAO.updateProperty(sysProperties);
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
