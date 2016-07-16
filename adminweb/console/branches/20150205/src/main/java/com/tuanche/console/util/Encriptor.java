package com.tuanche.console.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class Encriptor {
	private static Logger logger = Logger.getLogger(Encriptor.class);

	public static String getMD5(String plainText) {
		String md5 = null;
		try {
			if(plainText!=null){
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(plainText.getBytes());
				byte b[] = md.digest();
				int i;
				StringBuffer buf = new StringBuffer("");
				for (int offset = 0; offset < b.length; offset++) {
					i = b[offset];
					if (i < 0) i += 256;
					if (i < 16) buf.append("0");
					buf.append(Integer.toHexString(i));
				}
				md5 = buf.toString();
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		}
		return md5;
	}
}
