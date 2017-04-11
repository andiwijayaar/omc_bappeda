package com.omcbappeda.sumsel.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DecryptPassword {
	private static final Logger logger = LoggerFactory.getLogger(DecryptPassword.class);
	public String encryptPassword(String password){
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("DECRYPT", e);
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
