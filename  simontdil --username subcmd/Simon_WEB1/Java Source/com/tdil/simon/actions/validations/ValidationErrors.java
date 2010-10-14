package com.tdil.simon.actions.validations;

public interface ValidationErrors {

	// General errors
	String GENERAL_ERROR_TRY_AGAIN = "GENERAL_ERROR_TRY_AGAIN";
	String CANNOT_BE_EMPTY = "CANNOT_BE_EMPTY";
	String INVALID_NUMBER = "INVALID_NUMBER";
	String INVALID_EMAIL = "INVALID_EMAIL";
	String INVALID_DATE = "INVALID_DATE";
	String TEXT_TOO_LONG = "TEXT_TOO_LONG";
	
	String USER_ALREADY_EXISTS = "USER_ALREADY_EXISTS";
	String USER_DOES_NOT_EXISTS = "USER_DOES_NOT_EXISTS";
	String ADMIN_CANNOT_BE_DELETED = "ADMIN_CANNOT_BE_DELETED";
	String ONLY_ONE_DELEGATE_CAN_SIGN = "ONLY_ONE_DELEGATE_CAN_SIGN";
	
	String PASSWORD_INCORRECT = "PASSWORD_INCORRECT"; // the password does not match
	String PASSWORD_EXPIRED = "PASSWORD_EXPIRED"; // the password is expired
	
	String EMAIL_FAILED = "EMAIL_FAILED";
	
	String SELECT_USER_TYPE = "SELECT_USER_TYPE";
	
	// New password and retype password are different
	String PASSWORD_NOT_EQUALS = "PASSWORD_NOT_EQUALS";
	String ALREADY_LOGGED = "ALREADY_LOGGED";
	String MODERATOR_LOGGED = "MODERATOR_LOGGED";
	
	// Country
	String COUNTRY_DOES_NOT_EXISTS = "COUNTRY_DOES_NOT_EXISTS";
	String HOST_COUNTRY_DOES_NOT_EXISTS = "HOST_COUNTRY_DOES_NOT_EXISTS";
	String HOST_COUNTRY_CANNOT_BE_DELETED = "HOST_COUNTRY_CANNOT_BE_DELETED";
	String COUNTRY_ALREADY_EXISTS = "COUNTRY_ALREADY_EXISTS";
	String COUNTRY_FLAG_CANNOT_BE_WRITTEN = "COUNTRY_FLAG_CANNOT_BE_WRITTEN";
	String FLAG_MUST_BE_PNG = "FLAG_MUST_BE_PNG";
	
	// Category
	String CATEGORY_DOES_NOT_EXISTS = "CATEGORY_DOES_NOT_EXISTS";
	String CATEGORY_ALREADY_EXISTS = "CATEGORY_ALREADY_EXISTS";
	
	// Ref document
	String REFERENCE_DOCUMENT_DOES_NOT_EXISTS = "REFERENCE_DOCUMENT_DOES_NOT_EXISTS";
	String REFERENCE_DOCUMENT_CANNOT_BE_WRITTEN = "REFERENCE_DOCUMENT_CANNOT_BE_WRITTEN";
	String REFERENCE_DOCUMENT_ALREADY_EXISTS = "REFERENCE_DOCUMENT_ALREADY_EXISTS";
	String INVALID_DOC_TYPE = "INVALID_DOC_TYPE";
	
	// Document
	String NO_DOCUMENT_FOR_NEGOTIATION = "NO_DOCUMENT_FOR_NEGOTIATION";
	String NO_DOCUMENT_FOR_SIGN = "NO_DOCUMENT_FOR_SIGN";
	String DOCUMENT_DOES_NOT_EXISTS = "DOCUMENT_DOES_NOT_EXISTS";
	String UP_TO_COMMENT_DATE_CANNOT_BE_NULL = "UP_TO_COMMENT_DATE_CANNOT_BE_NULL";
	String UP_TO_COMMENT_DATE_BEFORE_TODAY = "UP_TO_COMMENT_DATE_BEFORE_TODAY";
	String DOCUMENT_NOT_FOR_USER = "DOCUMENT_NOT_FOR_USER";
	
	String SELECT_TYPE_ONE_OR_TWO = "SELECT_TYPE_ONE_OR_TWO";
	String DOCUMENT_IN_NEGOTIATION = "DOCUMENT_IN_NEGOTIATION";
	
	// Paragraph
	String NO_PARAGRAPH_FOR_NEGOTIATION = "NO_PARAGRAPH_FOR_NEGOTIATION";
	String VERSION_NOT_COMMENTABLE = "VERSION_NOT_COMMENTABLE";
	String PARAGRAPH_DOES_NOT_EXISTS = "PARAGRAPH_DOES_NOT_EXISTS";
	String NO_PARAGRAPH = "NO_PARAGRAPH";
	
	// Version
	String NO_VERSION_FOR_NEGOTIATION = "NO_VERSION_FOR_NEGOTIATION";
	String NO_VERSION_FOR_SIGN = "NO_VERSION_FOR_SIGN";
	String VERSION_DOES_NOT_EXISTS = "VERSION_DOES_NOT_EXISTS";
}
