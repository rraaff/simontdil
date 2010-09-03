package com.tdil.simon.utils;

public class PrivateMessageUtils {

	public static boolean mustBeShownIn(String pageName) {
		if ("createDocumentStep1".equals(pageName)) {
			return true;
		}
		if ("createDocumentStep2".equals(pageName)) {
			return true;
		}
		if ("createDocumentStepParagraph".equals(pageName)) {
			return true;
		}
		if ("previewDocument".equals(pageName)) {
			return true;
		}
		return false;
	}
}
