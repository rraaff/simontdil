package com.tdil.simon.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.tdil.simon.web.DownloadController;

public class UploadUtils {

	private static Logger getLog() {
		return LoggerProvider.getLogger(DownloadController.class);
	}
	
	public static void createFile(byte[] content, String destination) {
		if (content != null) {
			File file = new File(destination);
			if (file.exists()) {
				file.delete();
			}
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new ByteArrayInputStream(content);
				output = new FileOutputStream(destination);
				IOUtils.copy(input, output);
			} catch (Exception e) {
				getLog().error(e.getMessage(), e);
			} finally {
				try {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
						}
					}
				} finally {
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
						}
					}
				}
			}
		}
	}

	public static void uploadFileTo(FormFile uploaded, String destination) throws FileNotFoundException, IOException {
		String contentType = uploaded.getContentType();
		String fileName = uploaded.getFileName();
		int fileSize = uploaded.getFileSize();
		if (fileSize != 0) {
			File file = new File(destination);
			if (file.exists()) {
				file.delete();
			}
			if (fileSize != 0) {
				InputStream input = null;
				OutputStream output = null;
				try {
					input = uploaded.getInputStream();
					output = new FileOutputStream(destination);
					IOUtils.copy(input, output);
				} finally {
					try {
						if (input != null) {
							input.close();
						}
					} finally {
						if (output != null) {
							output.close();
						}
					}
				}
			}
		}
	}
}
