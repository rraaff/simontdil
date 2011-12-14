package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.actions.validations.ValidationErrors;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;

public class ListForm extends ActionForm {

	private static final long serialVersionUID = -5321349803437449615L;

	private Map<String, String> params = new HashMap<String, String>();
	private List list;
	
	private SystemUser user;
	private String operation;
	private String selectedIds[];

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
		selectedIds = new String[list.size()];
	}
	
	public void addParam(String key, String o) {
		params.put(key, o);
	}
	
	public String getParam(String key) {
		return params.get(key);
	}
	
	public void setParam(String key, String o) {
		params.put(key, o);
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void deleteVersion(int position) throws SQLException, ValidationException {
		Version version = (Version)this.getList().get(position);
		if (Version.IN_NEGOTIATION.equals(version.getStatus())) {
			throw new ValidationException(new ValidationError(ValidationErrors.CAN_NOT_DELETE_VERSION_IN_NEGOTIATION));
		}
		version.setDeleted(true);
		VersionDAO.logicallyDeleteVersion(version);
	}

	public void reactivateVersion(int position) throws SQLException {
		Version version = (Version)this.getList().get(position);
		version.setDeleted(false);
		VersionDAO.reactivateVersion(version);
	}
	
	public void disableCommentsForVersion(int position) throws SQLException {
		Version version = (Version)this.getList().get(position);
		version.setCommentsEnabled(false);
		VersionDAO.blockComments(version);
	}

	public void enableCommentsForVersion(int position) throws SQLException {
		Version version = (Version)this.getList().get(position);
		version.setCommentsEnabled(true);
		VersionDAO.enableComments(version);
	}

	public SystemUser getUser() {
		return user;
	}

	public void setUser(SystemUser user) {
		this.user = user;
	}
	
	public String[] getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String[] selectedIds) {
		this.selectedIds = selectedIds;
	}

	public void selectAsRelevant() throws NumberFormatException, SQLException {
		for (String id  : selectedIds) {
			DocumentDAO.updateDocumentRelevance(Integer.parseInt(id), Document.DEFAULT_RELEVANT);
		}
	}

	public void deselectAsRelevant() throws NumberFormatException, SQLException {
		for (String id  : selectedIds) {
			DocumentDAO.updateDocumentRelevance(Integer.parseInt(id), Document.NO_RELEVANT);
		}
	}
}
