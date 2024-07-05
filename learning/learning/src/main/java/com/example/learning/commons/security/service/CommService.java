package com.example.learning.commons.security.service;

import com.example.learning.BaseException;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public interface CommService {
	String AES256_encrypt(String str) throws BaseException;
	String AES256_decrypt(String str) throws BaseException;

	String SHA256(String str) throws NoSuchAlgorithmException;


	int saveRequestLog(HttpServletRequest req) throws BaseException;

	List<Map<String, Object>> getUrlRequestLogList (Map<String, Object> param) throws BaseException;

	List<Map<String, Object>> getLoginHistoryList (Map<String, Object> param) throws BaseException;
}
