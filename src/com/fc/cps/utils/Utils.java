package com.fc.cps.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	/**
	 * 对密码进行MD5加密
	 * 
	 */
	public static String toMD5(String data) {
		if (data == null) return null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data.getBytes());
			String result = new BigInteger(1, md.digest()).toString(16);
			return result;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
