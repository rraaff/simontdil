package com.tdil.simon.struts.forms;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import com.tdil.simon.data.ibatis.VersionDAO;
import com.tdil.simon.data.model.Version;

public class ListForm extends ActionForm {

	private static final long serialVersionUID = -5321349803437449615L;

	private Map<String, String> params = new HashMap<String, String>();
	private List list;
	
	private String operation;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
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

	public void deleteVersion(int position) throws SQLException {
		Version version = (Version)this.getList().get(position);
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
	
}
