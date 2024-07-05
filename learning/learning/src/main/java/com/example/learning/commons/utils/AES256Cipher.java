package com.example.learning.commons.utils;

import com.example.learning.BaseException;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class AES256Cipher {
	public static byte[] ivBytes = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };


	public static String AES_Encode(String str, String key)	throws BaseException {

		byte[] textBytes = null;
		try {
			textBytes = str.getBytes(StandardCharsets.UTF_8);
			AlgorithmParameterSpec ivSpec = new IvParameterSpec (ivBytes);
			SecretKeySpec newKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = null;
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);
			return Base64.encodeBase64String(cipher.doFinal(textBytes));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException |
				 InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException |
				 BadPaddingException e) {
			throw new BaseException(e);
		}
	}

	public static String AES_Decode(String str, String key)	throws BaseException {
		byte[] textBytes = Base64.decodeBase64(str);
		//byte[] textBytes = str.getBytes("UTF-8");
		AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
		SecretKeySpec newKey = null;
		try {
			newKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
			return new String(cipher.doFinal(textBytes), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			throw new BaseException(e);
		}
	}
}
