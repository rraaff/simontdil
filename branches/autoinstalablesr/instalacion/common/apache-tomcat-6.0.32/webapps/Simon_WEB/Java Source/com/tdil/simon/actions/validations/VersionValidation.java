package com.tdil.simon.actions.validations;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.tdil.simon.actions.response.ValidationError;
import com.tdil.simon.actions.response.ValidationException;
import com.tdil.simon.data.ibatis.DocumentDAO;
import com.tdil.simon.data.model.Document;
import com.tdil.simon.data.model.SystemUser;
import com.tdil.simon.data.model.Version;

public class VersionValidation {

	public static String validateName(String versionName, ValidationError validation) {
		return FieldValidation.validateText(versionName, "versionName", 100, validation);
	}	
	public static String validateDescription(String versionDescription, ValidationError validation) {
		return FieldValidation.validateText(versionDescription, "versionDescription", 500, validation);
	}
	
	public static Date validateUpToCommentDate(String upToCommentDate, ValidationError validation) {
		String result = upToCommentDate;
		if (StringUtils.isEmpty(upToCommentDate)) {
			validation.setFieldError("upToCommentDate", "upToCommentDate."+ValidationErrors.UP_TO_COMMENT_DATE_CANNOT_BE_NULL);
			return null;
		} else {
			result = upToCommentDate.trim();
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			try {
				Date date = dateFormat.parse(result);
				Date today = new Date();
				if (date.before(today)) {
					validation.setFieldError("upToCommentDate", "upToCommentDate."+ValidationErrors.UP_TO_COMMENT_DATE_BEFORE_TODAY);
				}
				return date;
			} catch (ParseException e) {
				validation.setFieldError("upToCommentDate", "upToCommentDate."+ValidationErrors.INVALID_DATE);
				return null;
			}
			
		}
	}

	public static String validateConsolidatedComment(String consolidatedComment, boolean consolidatedBoolean,
			ValidationError validation) {
		if (consolidatedBoolean) {
			return FieldValidation.validateText(consolidatedComment, "consolidatedComment", 100, validation);
		} else {
			return "";
		}
	}
	public static void validateDocumentAndUserType(Version version, SystemUser user) throws SQLException, ValidationException {
		Document doc = DocumentDAO.getDocument(version.getDocumentId());
		boolean typeOk = false;
		if (doc.isTypeOne()) {
			if(user.isTypeOne()) {
				typeOk = true;
			} else {
				throw new ValidationException(new ValidationError(ValidationErrors.DOCUMENT_NOT_FOR_USER));
			}
		}
		if (!typeOk) {
			if (doc.isTypeTwo() && !user.isTypeTwo()) {
				throw new ValidationException(new ValidationError(ValidationErrors.DOCUMENT_NOT_FOR_USER));
			}
		}
	}
}
