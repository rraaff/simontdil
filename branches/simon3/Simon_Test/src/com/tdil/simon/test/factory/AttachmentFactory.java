package com.tdil.simon.test.factory;

import java.sql.SQLException;
import java.util.List;

import com.tdil.simon.data.ibatis.AttachmentDAO;
import com.tdil.simon.data.ibatis.IBatisManager;
import com.tdil.simon.data.model.Attachment;
import com.tdil.simon.data.model.AttachmentOwnerType;

public class AttachmentFactory {

	public static List<Attachment> get(int ownerId, AttachmentOwnerType ownerType) throws SQLException {
		List<Attachment> result = null;
		IBatisManager.beginTransaction();
		result = AttachmentDAO.getListFor(ownerId, ownerType);
		IBatisManager.commitTransaction();
		return result;
	}
	
	public static Attachment get(int id) throws SQLException {
		Attachment result = null;
		IBatisManager.beginTransaction();
		result = AttachmentDAO.get(id);
		IBatisManager.commitTransaction();
		return result;
	}
	
	public static Attachment getWithData(int id) throws SQLException {
		Attachment result = null;
		IBatisManager.beginTransaction();
		result = AttachmentDAO.getWithData(id);
		IBatisManager.commitTransaction();
		return result;
	}
}
