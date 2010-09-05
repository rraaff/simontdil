package com.tdil.simon.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.struts.upload.FormFile;

public class UploadUtils {

	public static void uploadFileTo(FormFile uploaded, String destination) throws FileNotFoundException, IOException {
		String contentType = uploaded.getContentType();
        String fileName    = uploaded.getFileName();
        int fileSize       = uploaded.getFileSize();
        if (fileSize != 0) {
			File file = new File(destination);
			if(file.exists()) {
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
