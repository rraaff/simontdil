package com.tdil.simon.data.ibatis;

import java.sql.SQLException;

import com.tdil.simon.data.model.NotificationEmail;

public class NotificationEmailDAO {

	public static NotificationEmail getEmail(String key) throws SQLException {
		return (NotificationEmail) IBatisManager.sqlMapper.queryForObject(
				"selectEmailByKey", key);
	}
	
	public static void updateEmail(NotificationEmail notificationEmail) throws SQLException {
		IBatisManager.sqlMapper.update("updateEmail", notificationEmail);
	}
	
	public static void insertEmail(NotificationEmail notificationEmail) throws SQLException {
		IBatisManager.sqlMapper.insert("insertEmail", notificationEmail);
	}
}
