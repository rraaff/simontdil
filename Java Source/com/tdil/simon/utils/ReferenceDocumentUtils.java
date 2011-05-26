package com.tdil.simon.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;

import com.tdil.simon.web.SystemConfig;

public class ReferenceDocumentUtils {

	public static void writeDocumentToDisk(int docId, FileItem fileItem) throws IOException {
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		try {
			inputStream = fileItem.getInputStream();
			fileOutputStream = new FileOutputStream(getServerFileName(docId));
			IOUtils.copy(inputStream, fileOutputStream);
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}
	
	public static String getServerFileName(int docId) {
		return SystemConfig.getReferenceDocumentStore() + "ref" + docId;
	}
}
