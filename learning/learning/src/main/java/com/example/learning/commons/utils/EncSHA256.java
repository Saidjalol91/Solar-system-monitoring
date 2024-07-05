package com.example.learning.commons.utils;

import com.example.learning.BaseException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncSHA256 {
	public String doCryption(String input) throws BaseException {
		if (input == null || input.equals("")) {
			return input;
		}

		StringBuffer sb = new StringBuffer();
		StringBuffer hexString = new StringBuffer();

		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256");

			sh.update(input.getBytes());

			byte[] byteData = sh.digest();

			for(int i = 0; i < byteData.length; i++){
				sb.append(Integer.toString((byteData[i]&0xff)+0x100,16).substring(1));
			}

			for(int i = 0; i < byteData.length; i++){
				String hex = Integer.toHexString(0xff&byteData[i]);

				if (hex.length() == 1){
					hexString.append('0');
				}
				hexString.append(hex);
			}

		}catch(NoSuchAlgorithmException e){
			throw new BaseException(e);
		}

		return hexString.toString();

	}

	public String SHA256(String str) throws BaseException {
		String SHA = "";
		try {
			String str1 = str;
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str1.getBytes());
			byte[] byteData = sh.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0 ; i < byteData.length ; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
		} catch (NoSuchAlgorithmException e){
			throw new BaseException(e);
		}
		return SHA;
	}
}
