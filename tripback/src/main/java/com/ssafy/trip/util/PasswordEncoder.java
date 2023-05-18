package com.ssafy.trip.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordEncoder {
	private static PasswordEncoder instance = new PasswordEncoder();
	public static PasswordEncoder getInstance() {
		return instance;
	}
	
	public String encrypt(String salt, String plainPwd) {
		String encrypt = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update((salt+plainPwd).getBytes());
			byte[] saltPwd = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b : saltPwd) {
				sb.append(String.format("%02x", b));
			}
			
			encrypt =  sb.toString();
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encrypt;
	}
	
	
	public String makeSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] salt = new byte[20];
		
		sr.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for(byte b : salt) {
			sb.append(String.format("%02x", b));
		}
		
		return sb.toString();
	}
}
