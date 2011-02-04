package com.tdil.simon.utils;

public class CryptoUtils {

	private static byte[] computeHash(String x) throws Exception {
		java.security.MessageDigest d = null;
		d = java.security.MessageDigest.getInstance("SHA-1");
		d.reset();
		d.update(x.getBytes());
		return d.digest();
	}
	
	public static String getHashedValue(String originalValue)  {
		try {
			return byteArrayToHexString(computeHash(originalValue));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(CryptoUtils.getHashedValue("Admin"));
	}
}
