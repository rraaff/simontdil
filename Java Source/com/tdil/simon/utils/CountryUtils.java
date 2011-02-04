package com.tdil.simon.utils;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.tdil.simon.web.SystemConfig;

public class CountryUtils {

	public static void writeFlagToDisk(int countryOid, byte[] flag) throws IOException {
		FileOutputStream fileOutputStream = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(getServerFileName(countryOid));
			IOUtils.write(flag, fileOut);
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}
	
	public static String getServerFileName(int countryOid) {
		return SystemConfig.getFlagStore() + "country" + countryOid;
	}
}
