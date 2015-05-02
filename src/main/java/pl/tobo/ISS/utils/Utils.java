/**
*  Copyright (C) 2014,2015 Tomasz Bosak.
**/
package pl.tobo.ISS.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Utils {

	public static  String getMD5(String password) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] bytes = m.digest();
			return String.format("%032x", new BigInteger(1, bytes));
		} catch (Exception e) {
			throw new RuntimeException("Not possible to make MD5 digest");
		}
	}
	
	public static boolean logByLdap(String username, String password, String ldapConn){
		
	   
		return true;
		
	}
	
}
