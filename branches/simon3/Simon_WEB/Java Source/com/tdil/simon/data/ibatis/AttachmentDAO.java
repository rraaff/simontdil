package com.tdil.simon.data.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.tdil.simon.data.model.Attachment;
import com.tdil.simon.data.model.AttachmentOwnerType;

public class AttachmentDAO {

	/* Las lista resultado no tiene el cambo blob*/
	public static List<Attachment> getListFor(int ownerId, AttachmentOwnerType ownerType) throws SQLException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("ownerId", ownerId);
		params.put("ownerType", ownerType.name());
		return (List<Attachment>)IBatisManager.sqlMapper.queryForList("selectAttachmentsList", params);
	}
	
	public static Attachment get(int id) throws SQLException {
		return (Attachment) IBatisManager.sqlMapper.queryForObject(
				"selectAttachmentById", id);
	}
	
	public static Attachment getWithData(int id) throws SQLException {
		return (Attachment) IBatisManager.sqlMapper.queryForObject(
				"selectAttachmentByIdWithData", id);
	}
	
	public static Integer insert(Attachment attachment) throws SQLException {
		return (Integer)IBatisManager.sqlMapper.insert("insertAttachment", attachment);
	}
	
	public static void update(Attachment attachment) throws SQLException {
		if (attachment.getData() != null) {
			IBatisManager.sqlMapper.update("updateAttachmentWithData", attachment);
		} else {
			IBatisManager.sqlMapper.update("updateAttachment", attachment);
		}
	}
}
