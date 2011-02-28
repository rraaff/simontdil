package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.validations.ResourceBundleValidation;
import com.tdil.simon.data.ibatis.ResourceBundleDAO;
import com.tdil.simon.data.model.ResourceBundle;

public class ResourceBundleForm extends TransactionalValidationForm implements ABMForm {

	private static final long serialVersionUID = -7158479525739355568L;

	private String operation;
	
	private int id;
	private String rbContext;
	private String rbKey;
	private String rbValue;
	
	private String rbSearchValue;
	
	private List<ContextBean> allContext;
	
	private List<ResourceBundle> searchResult;
	
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
		this.rbKey= null;
		this.rbValue= null;
		this.rbContext = null;
		this.setSearchResult(new ArrayList<ResourceBundle>());
		this.rbSearchValue = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#init()
	 */
	public void init() throws SQLException {
		List<ContextBean> allContexts = new ArrayList<ContextBean>();
		for (String context : ResourceBundleDAO.selectAvailabeContexts()) {
			allContexts.add(new ContextBean(context));
		}
		this.setAllContext(allContexts);
	}
	
	public void initWith(int userId) throws SQLException {
//		Category category = CategoryDAO.getCategory(userId);
//		if (category != null) {
//			this.id = userId;
//			this.name = category.getName();
//		}
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.simon.struts.forms.ABMForm#save()
	 */
	public void save() throws SQLException {
		this.modifyResourceBundle();
	}
	
	public ValidationError basicValidate() {
		ValidationError validation = new ValidationError();
		ResourceBundleValidation.validateValue(this.rbValue, "resourceBundle.value", validation);
		return validation;
	}
	
	@Override
	public void validateInTransaction(ValidationError validationError) throws SQLException {
	}
	
	public void search() throws SQLException {
		this.setSearchResult(ResourceBundleDAO.searchResourceBundle(this.getRbContext(), this.getRbSearchValue()));
	}
	
	private void modifyResourceBundle() throws SQLException {
		ResourceBundle resourceBundle = ResourceBundleDAO.getResourceBundle(this.getRbContext(), this.getRbKey());
		resourceBundle.setRbValue(this.getRbValue());
		ResourceBundleDAO.updateResourceBundle(resourceBundle);
	}
	
	public String getRbKey() {
		return rbKey;
	}
	public void setRbKey(String rbKey) {
		this.rbKey = rbKey;
	}
	public String getRbValue() {
		return rbValue;
	}
	public void setRbValue(String rbValue) {
		this.rbValue = rbValue;
	}
	public List<ContextBean> getAllContext() {
		return allContext;
	}
	public void setAllContext(List<ContextBean> allContext) {
		this.allContext = allContext;
	}
	public String getRbContext() {
		return rbContext;
	}
	public void setRbContext(String rbContext) {
		this.rbContext = rbContext;
	}
	public List<ResourceBundle> getSearchResult() {
		return searchResult;
	}
	public void setSearchResult(List<ResourceBundle> searchResult) {
		this.searchResult = searchResult;
	}
	public String getRbSearchValue() {
		return rbSearchValue;
	}
	public void setRbSearchValue(String rbSearchValue) {
		this.rbSearchValue = rbSearchValue;
	}
}
