package com.example.learning.commons.utils;

import com.example.learning.BaseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
//import com.j256.simplemagic.ContentInfo;
//import com.j256.simplemagic.ContentInfoUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public final class Utility {
	private static SimpleDateFormat shortDateFmt = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat longDateFmt = new SimpleDateFormat("yyyy/MM/dd");
	private static SimpleDateFormat longDateTimeFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private static SimpleDateFormat longToDateFmt = new SimpleDateFormat("yyyy.MM.dd");

	private static SimpleDateFormat dayDateFmt = new SimpleDateFormat("MM/dd");

	private static SimpleDateFormat shortTimeFmt = new SimpleDateFormat("yyyyMMddHHmm");
	private static SimpleDateFormat LongTimeFmt = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat LongTimeFmtUnder = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat timeFmt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static SimpleDateFormat DateFmtMs = new SimpleDateFormat("yyMMddHHmmssSSS");

	private static Log logger = LogFactory.getLog(Utility.class);

	public static boolean isNumber(String str) {
		boolean result = false;
		try {
			Double.parseDouble(str);
			result = true;
		} catch (NumberFormatException e) {
			return false;
		}
		return result;
	}

	public static String getRamdomWord(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
				'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '~', '!', '@', '#', '$', '%', '^', '&', '-', '_', '+', '=', '/', '?',
				'*', '[', ']', '{', '}', '\\', '₩' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random());
			sb.append(charSet[idx]);
		}
		return sb.toString();
	}

	/**
	 * Null to ""
	 *
	 * @param arg0
	 * @return
	 */
	public static String null2Str(String arg0, String arg1) {
		String result = arg1;
		if (arg0 != null) {
			result = arg0;
		}
		return result;
	}

	/**
	 * Null to ""
	 *
	 * @param arg0
	 * @return
	 */
	public static int null2Int(String arg0, String arg1) {
		int result = Integer.parseInt(arg1);
		if (arg0 != null) {
			if (!arg0.equals(""))
				result = Integer.parseInt(arg0);
		}
		return result;
	}

	/**
	 * Null to ""
	 *
	 * @param arg0
	 * @return
	 */
	public static String convertNull(String arg0) {
		String result = "";
		if (arg0 != null) {
			result = arg0;
		}
		return result;
	}

	public static String convertNull(String arg0, String arg1) {
		String result = arg1;
		if (arg0 != null) {
			result = arg0;
		}
		return result;
	}

	/**
	 * Enter To BR
	 *
	 * @param arg0
	 * @return
	 */
	public static String enter2BR(String arg0) {
		String result = "";
		if (!"".equals(arg0) && arg0 != null) {
			result = arg0.replaceAll("\r\n", "<br>");
			result = result.replaceAll("\r", "<br>");
			result = result.replaceAll("\n", "<br>");
		}
		return result;
	}

	/**
	 * Enter To Nbsp
	 *
	 * @param arg0
	 * @return
	 */
	public static String enter2Nbsp(String arg0) {
		String result = "";
		if (!"".equals(arg0) && arg0 != null) {
			result = arg0.replaceAll("\r\n", " ");
		}
		return result;
	}

	/**
	 * BR to Nbsp
	 *
	 * @param args
	 * @return
	 */
	public static String br2Nbsp(String args) {
		String result = "";
		if (!"".equals(args) && args != null) {
			result = args.replaceAll("<br>", "&nbsp;");
			result = result.replaceAll("</br>", "&nbsp;");
		}
		return result;
	}


	public static String br2Enter(String arg0) {
		String result = "";
		if (!"".equals(arg0) && arg0 != null) {
			result = arg0.replaceAll("<br>", "\r\n");
		}
		return result;
	}

	/**
	 * 문자열에서 HTML 태그 제거
	 *
	 * @param s
	 * @return
	 */
	public static String removeTag(String s) {
		if (s == null)
			return "";
		final int NORMAL_STATE = 0;
		final int TAG_STATE = 1;
		final int START_TAG_STATE = 2;
		final int END_TAG_STATE = 3;
		final int SINGLE_QUOT_STATE = 4;
		final int DOUBLE_QUOT_STATE = 5;
		int state = NORMAL_STATE;
		int oldState = NORMAL_STATE;
		char[] chars = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		char a;
		for (int i = 0; i < chars.length; i++) {
			a = chars[i];
			switch (state) {
				case NORMAL_STATE:
					if (a == '<')
						state = TAG_STATE;
					else
						sb.append(a);
					break;
				case TAG_STATE:
					if (a == '>')
						state = NORMAL_STATE;
					else if (a == '\"') {
						oldState = state;
						state = DOUBLE_QUOT_STATE;
					} else if (a == '\'') {
						oldState = state;
						state = SINGLE_QUOT_STATE;
					} else if (a == '/')
						state = END_TAG_STATE;
					else if (a != ' ' && a != '\t' && a != '\n' && a != '\r' && a != '\f')
						state = START_TAG_STATE;
					break;
				case START_TAG_STATE:
				case END_TAG_STATE:
					if (a == '>')
						state = NORMAL_STATE;
					else if (a == '\"') {
						oldState = state;
						state = DOUBLE_QUOT_STATE;
					} else if (a == '\'') {
						oldState = state;
						state = SINGLE_QUOT_STATE;
					} else if (a == '\"')
						state = DOUBLE_QUOT_STATE;
					else if (a == '\'')
						state = SINGLE_QUOT_STATE;
					break;
				case DOUBLE_QUOT_STATE:
					if (a == '\"')
						state = oldState;
					break;
				case SINGLE_QUOT_STATE:
					if (a == '\'')
						state = oldState;
					break;
			}
		}
		return sb.toString();

	}

	/*
	 * XSS 방지
	 */
	public static String removeXSS(String str, boolean use_html) {
		String str_low = "";
		if (use_html) { // HTML tag를 사용하게 할 경우 부분 허용
			// HTML tag를 모두 제거
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");

			// 특수 문자 제거
			str = str.replaceAll("\"", "&gt;");
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("%00", null);
			str = str.replaceAll("\"", "&#34;");
			str = str.replaceAll("\'", "&#39;");
			str = str.replaceAll("%", "&#37;");
			str = str.replaceAll("../", "");
			str = str.replaceAll("..\\\\", "");
			str = str.replaceAll("./", "");
			str = str.replaceAll("%2F", "");
			// 허용할 HTML tag만 변경
			str = str.replaceAll("&amp;lt;p&amp;gt;", "<p>");
			str = str.replaceAll("&amp;lt;P&amp;gt;", "<P>");
			str = str.replaceAll("&amp;lt;br&amp;gt;", "<br>");
			str = str.replaceAll("&amp;lt;BR&amp;gt;", "<BR>");
			// 스크립트 문자열 필터링 (선별함 - 필요한 경우 보안가이드에 첨부된 구문 추가)
			str_low = str.toLowerCase();
			if (str_low.contains("javascript") || str_low.contains("script") || str_low.contains("iframe") || str_low.contains("document")
					|| str_low.contains("vbscript") || str_low.contains("applet") || str_low.contains("embed") || str_low.contains("object")
					|| str_low.contains("frame") || str_low.contains("grameset") || str_low.contains("layer") || str_low.contains("bgsound")
					|| str_low.contains("alert") || str_low.contains("onblur") || str_low.contains("onchange") || str_low.contains("onclick")
					|| str_low.contains("ondblclick") || str_low.contains("enerror") || str_low.contains("onfocus") || str_low.contains("onload")
					|| str_low.contains("onmouse") || str_low.contains("onscroll") || str_low.contains("onsubmit") || str_low.contains("onunload")) {
				str = str_low;
				str = str.replaceAll("javascript", "x-javascript");
				str = str.replaceAll("script", "x-script");
				str = str.replaceAll("iframe", "x-iframe");
				str = str.replaceAll("document", "x-document");
				str = str.replaceAll("vbscript", "x-vbscript");
				str = str.replaceAll("applet", "x-applet");
				str = str.replaceAll("embed", "x-embed");
				str = str.replaceAll("object", "x-object");
				str = str.replaceAll("frame", "x-frame");
				str = str.replaceAll("grameset", "x-grameset");
				str = str.replaceAll("layer", "x-layer");
				str = str.replaceAll("bgsound", "x-bgsound");
				str = str.replaceAll("alert", "x-alert");
				str = str.replaceAll("onblur", "x-onblur");
				str = str.replaceAll("onchange", "x-onchange");
				str = str.replaceAll("onclick", "x-onclick");
				str = str.replaceAll("ondblclick", "x-ondblclick");
				str = str.replaceAll("enerror", "x-enerror");
				str = str.replaceAll("onfocus", "x-onfocus");
				str = str.replaceAll("onload", "x-onload");
				str = str.replaceAll("onmouse", "x-onmouse");
				str = str.replaceAll("onscroll", "x-onscroll");
				str = str.replaceAll("onsubmit", "x-onsubmit");
				str = str.replaceAll("onunload", "x-onunload");
			}
		} else { // HTML tag를 사용하지 못하게 할 경우
			str = str.replaceAll("\"", "&gt;");
			str = str.replaceAll("&", "&amp;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("%00", null);
			str = str.replaceAll("\"", "&#34;");
			str = str.replaceAll("\'", "&#39;");
			str = str.replaceAll("%", "&#37;");
			str = str.replaceAll("../", "");
			str = str.replaceAll("..\\\\", "");
			str = str.replaceAll("./", "");
			str = str.replaceAll("%2F", "");
		}
		return str;
	}

	/**
	 * List Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터를 가지고있는 배열
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toJSON(List<Object> obj) throws BaseException {
		String jsonStr = "";
		// if(obj.size()<=0) return "";
		Gson gson = new Gson();
		if (obj.size() > 0) {
			jsonStr = gson.toJson(obj);
			jsonStr = "\"DATA\":" + jsonStr;
		} else {
			jsonStr = "\"DATA\":[]";
		}
		return jsonStr;
	}

	/**
	 * List Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터를 가지고있는 배열
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toNoDataCoverJSON(List<Object> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();

		jsonStr = gson.toJson(obj);
		jsonStr = "[" + jsonStr + "]";
		return jsonStr;
	}

	/**
	 * Map Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toJSON(Map<String, String> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();

		// Map Object 를 JSON 문자열로 변환
		jsonStr = gson.toJson(obj);
		jsonStr = "{\"DATA\":" + jsonStr + "}";
		return jsonStr;
	}

	/**
	 * Map Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toETCJSON(Map<String, String> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();

		// Map Object 를 JSON 문자열로 변환
		jsonStr = gson.toJson(obj);
		jsonStr = "{\"ETC\":" + jsonStr + "}";
		return jsonStr;
	}

	/**
	 * List Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터를 가지고있는 배열
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toETCJSON(List<Map<String, String>> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();
		if (obj != null && obj.size() > 0) {
			Map<String, String> mp = obj.get(0);
			jsonStr = gson.toJson(mp);
			jsonStr = "\"ETC\":" + jsonStr;
		}
		return jsonStr;
	}

	/**
	 * Map Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toJSONdefault(Map<String, String> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();

		// Map Object 를 JSON 문자열로 변환
		jsonStr = gson.toJson(obj);
		jsonStr = "{\"DATA\":" + jsonStr + "}";
		return jsonStr;
	}

	/**
	 * Map Object 를 JSON 문자열로 변환한다.
	 *
	 * @param obj Map데이터
	 * @return String json형태의 문자열
	 * @throws BaseException
	 */
	public static String toJSONdefault(List<Object> obj) throws BaseException {
		String jsonStr = "";
		Gson gson = new Gson();

		// Map Object 를 JSON 문자열로 변환
		jsonStr = gson.toJson(obj);
		jsonStr = "{\"DATA\":" + jsonStr + "}";
		return jsonStr;
	}

	/**
	 * JSON 문자열을 출력한다.
	 *
	 * @param res     HttpServletResponse
	 * @param jsonStr json 형태의 문자열
	 * @return void
	 * @throws BaseException
	 */
	public static void flushJSON(HttpServletResponse res, String jsonStr) throws BaseException {
		PrintWriter out = null;
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(res.getOutputStream(), "utf-8");
			out = new PrintWriter(osw);
			jsonStr = jsonStr.replaceAll("\r", "<br/>"); // 두드림 결재창에서 엔터값 먹도록 처리함.
			out.print(jsonStr);
			out.flush();
		} catch (IOException e) {
			throw new BaseException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				throw new BaseException(e);
			}
		}
	}

	/**
	 * JSON 문자열을 출력한다.
	 *
	 * @param res     HttpServletResponse
	 * @param jsonStr json 형태의 문자열
	 * @return void
	 * @throws BaseException
	 */
	public static void flushJSONKR(HttpServletResponse res, String jsonStr) throws BaseException {
		PrintWriter out = null;
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(res.getOutputStream(), "euc-kr");
			out = new PrintWriter(osw);
			out.print(jsonStr);
			out.flush();
		} catch (IOException e) {
			throw new BaseException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				throw new BaseException(e);
			}
		}
	}

	/**
	 * XML 문자열을 출력한다.
	 *
	 * @param res    HttpServletResponse
	 * @param xmlStr xml 형태의 문자열
	 * @return void
	 * @throws BaseException
	 */
	public static void flushXML(HttpServletResponse res, String xmlStr) throws BaseException {
		PrintWriter out = null;
		OutputStreamWriter osw = null;
		try {
			osw = new OutputStreamWriter(res.getOutputStream(), "utf-8");
			out = new PrintWriter(osw);
			StringBuffer xmlBuff = new StringBuffer();
			xmlBuff.append("<?xml version=\"1.0\" encoding=\"euc-kr\" ?>\n");
			xmlBuff.append(xmlStr);
			out.print(xmlBuff.toString());
			out.flush();
		} catch (IOException e) {
			throw new BaseException(e);
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (osw != null) {
					osw.close();
				}
			} catch (IOException e) {
				throw new BaseException(e);
			}
		}
	}

	/**
	 * JSON 그리드 전체 로우 데이터를 DataClass 로 변환혀여 배열에 담아 리턴한다.
	 *
	 * @param jsonArr json배열데이터 문자열
	 * @return ArrayList 전체 그리드데이터 배열
	 * @throws BaseException
	 */

	public static ArrayList<Object> getGridArray(String jsonArr) throws BaseException {
		// Gson gson = new Gson();

		//JsonParser parser = new JsonParser();
		//JsonElement je = parser.parse(jsonArr);

		JsonReader reader = new JsonReader(new StringReader (jsonArr));
		reader.setLenient(true);
		JsonParser parser = new JsonParser();
		JsonElement je = parser.parse(reader);

		JsonElement dataJson = je.getAsJsonObject().get("data");
		JsonArray array = dataJson.getAsJsonArray();

		String key = "";
		String val = "";
		Iterator<Entry<String, JsonElement>> it = null;
		Map.Entry<String, JsonElement> me = null;
		DataClass data = null;
		ArrayList<Object> dataArr = new ArrayList<Object> ();

		for (int i = 0; i < array.size(); i++) {
			data = new DataClass();
			it = (array.get(i).getAsJsonObject().entrySet()).iterator();

			while (it.hasNext()) {
				me = it.next();

				key = me.getKey().toString().toLowerCase();
				val = me.getValue().toString();
				val = val.replaceAll("\\n", "\n");
				val = val.replaceAll("\\r", "");
				if (val.startsWith("\"")) {
					val = val.substring(1);
				}
				if (val.endsWith("\"")) {
					val = val.substring(0, val.length() - 1);
				}
				data.set(key, val);
			}

			dataArr.add(data);
		}
		return dataArr;
	}

	/**
	 * 두날짜의 차이 구하기
	 *
	 * @return long
	 * @throws BaseException
	 */

	public static long doDiffOfDate(String start) {

		// if (!StringUtils.isNumeric(start)) return 0;

		String end = toLongDate(null);
		long diffDays = 0;
		try {
			if (start == null || start.length() == 0)
				start = end;

			Date beginDate = longDateFmt.parse(start);
			Date endDate = longDateFmt.parse(end);

			// 시간차이를 시간,분,초를 곱한 값으로 나누면 하루 단위가 나옴
			long diff = endDate.getTime() - beginDate.getTime();

			diffDays = diff / (24 * 60 * 60 * 1000);

		} catch (ParseException e) {
			diffDays = -1;
		}
		return diffDays;
	}

	/**
	 * 현재 일자를 yyyy-MM-dd 형태로 리턴한다.
	 *
	 * @return String
	 */
	public static String getLongDate() {
		return toLongDate(null);
	}

	/**
	 * 현재 일자를 yyyyMMdd 형태로 리턴한다.
	 *
	 * @return String
	 */
	public static String getShortDate() {
		return toShortDate(null);
	}

	/**
	 * 현재 일자를 yyyyMMddhhmmss 형태로 리턴한다.
	 *
	 * @return String
	 */
	public static String getLongDateTime() {
		return LongTimeFmt.format(new Date());
	}

	/**
	 * 현재 일자를 yyyyMMdd hhmmss 형태로 리턴한다.
	 *
	 * @return String
	 */
	public static String getDateFmtMs() {
		return DateFmtMs.format(new Date());
	}

	/**
	 * 현재 일자를 yyyy_MM_dd_hh_mm 형태로 리턴한다.
	 *
	 * @return String
	 */
	public static String getLongDateTimeUnder() {
		return LongTimeFmtUnder.format(new Date());
	}

	/**
	 * yyyy-MM-dd 형태의 날짜 문자열을 yyyy/MM/dd 형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param s String
	 * @return String
	 */
	public static String toLongDate(String s) {
		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {
			return "";
		} else {
			s = replace(s, "-", "");

			ParsePosition pos = new ParsePosition(0);
			t = shortDateFmt.parse(s, pos);
		}
		if (t == null)
			return s;
		return longDateFmt.format(t);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss형태의 날짜 문자열을 yyyy/MM/dd HH:mm:ss형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param s String
	 * @return String
	 */
	public static String toLongDateTime(String s) throws BaseException {
		s = s.replace("T", " ");

		Date t = null;
		try {
			t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
		} catch (ParseException e) {
			throw new BaseException(e);
		}

		if (t == null)
			return s;

		return longDateTimeFmt.format(t);
	}

	/**
	 * yyyyMMdd 형태의 날짜 문자열을 yyyy/MM/dd HH:mm 형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param s String
	 * @return String
	 */
	public static String toTimeDate(String s) {
		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {
			return "";
		} else {
			s = replace(s, "-", "");
			ParsePosition pos = new ParsePosition(0);
			t = shortTimeFmt.parse(s, pos);
		}
		if (t == null)
			return s;
		return timeFmt.format(t);
	}

	/**
	 * yyyyMMdd 형태의 날짜 문자열을 yyyy-MM-dd 형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param
	 * @return String
	 */
	public static String toLongToDate() {
		Date t = new Date();
		return longToDateFmt.format(t);
	}

	/**
	 * yyyy-MM-dd 형태의 날짜 문자열을 yyyyMMdd 형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param s String
	 * @return String
	 */
	public static String toShortDate(String s) {
		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {

			return "";

		} else {
			s = replace(s, "-", "");
			s = toLongDate(s);

			ParsePosition pos = new ParsePosition(0);
			t = longToDateFmt.parse(s, pos);
		}
		if (t == null)
			return s;
		return shortDateFmt.format(t);
	}

	/**
	 * yyyy-MM-dd 형태의 날짜 문자열을 MM/dd 형태의 날짜 문자열로 변환하여 리턴한다.
	 *
	 * @param s String
	 * @return String
	 */
	public static String toDayDate(String s) {
		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {

			return "";

		} else {
			s = replace(s, "-", "");
			s = toLongDate(s);

			ParsePosition pos = new ParsePosition(0);
			t = longDateFmt.parse(s, pos);
		}
		if (t == null)
			return s;
		return dayDateFmt.format(t);
	}

	/**
	 * 현재 년도를 return 한다.
	 *
	 * @return 현재년도
	 */
	public static String getYearString() {
		String yyyyMMdd = getShortDate();
		return yyyyMMdd.substring(0, 4);
	}

	/**
	 * 현재 월을 return 한다.
	 *
	 * @return 현재 월
	 */
	public static String getMonthString() {
		String yyyyMMdd = getShortDate();
		return yyyyMMdd.substring(4, 6);
	}

	/**
	 * 현재 년월을 return 한다.
	 *
	 * @return 현재 년월
	 */
	public static String getYYYYMMString() {
		String yyyyMMdd = getShortDate();
		return yyyyMMdd.substring(0, 6);
	}

	/**
	 * 현재 월 기준 +/-한 월을 return 한다.
	 *
	 * @return 현재 월
	 */
	public static String getMonthStringSel(int val) {
		String yyyyMMdd = getShortDate();
		int month = Integer.parseInt(yyyyMMdd.substring(4, 6));
		int value = month + val;
		if (value < 0) {
			value = value + 12;
		} else if (value > 12) {
			value = value - 12;
		}

		if (value == 0) {
			return "12";
		} else if (value < 10) {
			return "0" + value;
		} else {
			return "" + value;
		}
	}

	/**
	 * 현재 일을 return 한다.
	 *
	 * @return 현재 일
	 */
	public static String getDayString() {
		String yyyyMMdd = getShortDate();
		return yyyyMMdd.substring(6, 8);
	}

	/**
	 * 현재 월의 마지막일 return 한다.
	 *
	 * @return 현재 일
	 */
	public static String getLastDayString() {
		// java.util.GregorianCalendar currDtim = new GregorianCalendar();
		return "" + Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 해당 일의 달에 마지막 일을 리턴한다.
	 *
	 * @return 현재 일자
	 */
	public static String getLastDay() {
		return getYearString() + getMonthString() + getLastDayString();
	}

	/**
	 * 설정 일의 마지막 일을 리턴한다.
	 *
	 * @return 현재 일자
	 */
	public static String getLastDay(String s) {

		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {
			return "";
		} else {
			s = replace(s, "-", "");

			ParsePosition pos = new ParsePosition(0);
			t = shortDateFmt.parse(s, pos);
		}
		if (t == null)
			return s;

		String lastDay = "";
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(4, 6)) - 1, Integer.parseInt(s.substring(6, 8)));

		lastDay = gc.get(Calendar.YEAR) + "";
		lastDay += gc.get(Calendar.MONTH) + 1 + "";
		lastDay += gc.getActualMaximum(Calendar.DAY_OF_MONTH) + "";
		return toLongDate(lastDay);
	}

	/**
	 * 숫자 세자리마다 , 를 삽입한다.
	 *
	 * @param str String 숫자형식의 String
	 * @return String ,가 삽입된 숫자형식의 String
	 */
	public static String getCommaNum(String str) {
		return getCommaNum(str, "###,###.###");
	}

	public static String getCommaNum(String str, String form) {
		String rtn = "";
		if (str == null || "".equals(str)) {
			rtn = "";
		} else {
			try {
				java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();
				java.text.DecimalFormat df = (java.text.DecimalFormat) nf;
				df.applyPattern(form);
				rtn = df.format(Double.valueOf(str));
			} catch (NumberFormatException e) {
				// rtn = "";
				// 출력물 스트링 반환
				// logger.info(e.toString());
				rtn = str;
			}
		}
		return rtn;
	}

	/**
	 * String을 받아 oldstr String을 찾아 newstr String으로 바꿔준다.
	 *
	 * @param original String 원본
	 * @param oldstr   String 바뀔 String
	 * @param newstr   String 바꿀 String
	 * @return String
	 */
	public static String replace(String original, String oldstr, String newstr) {
		StringBuffer convert = new StringBuffer();
		int pos = 0;
		int begin = 0;
		pos = original.indexOf(oldstr);

		if (pos == -1)
			return original;

		while (pos != -1) {
			convert.append(original.substring(begin, pos) + newstr);
			begin = pos + oldstr.length();
			pos = original.indexOf(oldstr, begin);
		}
		convert.append(original.substring(begin));

		return convert.toString();
	}

	/**
	 * 문자열의 byte 수를 리턴한다.
	 *
	 * @param in String
	 * @return int
	 */
	public static int getBytesLength(String in) throws BaseException {
		if (in == null) {
			return 0;
		} else {
			try {
				return in.getBytes("KSC5601").length;
			} catch (UnsupportedEncodingException e) {
				throw new BaseException(e);
			}
		}
	}

	/**
	 * day 수 만큼 +,- 한 일자를 리턴한다.
	 *
	 * @param day int
	 * @return String
	 */
	public static String getDateAddString(int day) {
		java.util.GregorianCalendar currDtim = new GregorianCalendar();
		currDtim.add(Calendar.DATE, day);
		java.sql.Date date = new java.sql.Date(currDtim.getTime().getTime());

		return toLongDate(date.toString());
	}

	/**
	 * 넘겨받은 날짜형 String에 day 수 만큼 +,- 한 일자를 리턴한다.
	 *
	 * @param s   String
	 * @param day int
	 * @return String
	 */
	public static String getDateAddString(String s, int day) {
		Date t;
		if (s == null) {
			t = new Date();
		} else if (s.length() == 0) {
			return "";
		} else {
			s = replace(s, "-", "");

			ParsePosition pos = new ParsePosition(0);
			t = shortDateFmt.parse(s, pos);
		}
		if (t == null)
			return s;

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(4, 6)) - 1, Integer.parseInt(s.substring(6, 8)));

		gc.add(Calendar.DATE, day);
		java.sql.Date date = new java.sql.Date(gc.getTime().getTime());
		return toLongDate(date.toString());
	}

	/**
	 * month 수 만큼 +,- 한 일자를 리턴한다.
	 *
	 * @param mon int
	 * @return String
	 */
	public static String getMonthAddString(int mon) {
		java.util.GregorianCalendar currDtim = new GregorianCalendar();
		currDtim.add(Calendar.MONTH, mon);
//		currDtim.add(Calendar.DATE, -1);
		java.sql.Date date = new java.sql.Date(currDtim.getTime().getTime());

		return toLongDate(date.toString());
	}

	/**
	 * year 수 만큼 +,- 한 일자를 리턴한다.
	 *
	 * @param year int
	 * @return String
	 */
	public static String getYearAddString(int year) {
		java.util.GregorianCalendar currDtim = new GregorianCalendar();
		currDtim.add(Calendar.YEAR, year);
//		currDtim.add(Calendar.DATE, -1);
		java.sql.Date date = new java.sql.Date(currDtim.getTime().getTime());

		return toLongDate(date.toString());
	}

	/**
	 * String => int 변환
	 *
	 * @param str String
	 * @return int
	 */
	public static int convInt(String str) {
		return convInt(str, "0");
	}

	/**
	 * String => int 변환. str이 null이면 def 사용
	 *
	 * @param str String
	 * @param def String def
	 * @return int
	 */
	public static int convInt(String str, String def) {
		str = replace(nullCheck(str), ",", "");
		if (str.indexOf(".") > -1) {
			str = str.substring(0, str.indexOf("."));
		}
		if ("".equals(str))
			str = def;
		return Integer.parseInt(str);
	}

	/**
	 * String => double 변환
	 *
	 * @param str String
	 * @return double
	 */
	public static double convDouble(String str) {
		return convDouble(str, "0");
	}

	/**
	 * String => double 변환. str이 null이면 def 사용
	 *
	 * @param str String
	 * @param def String
	 * @return double
	 */
	public static double convDouble(String str, String def) {
		str = replace(nullCheck(str), ",", "");
		if ("".equals(str))
			str = def;
		return Double.parseDouble(str);
	}

	/**
	 * String => long 변환. str이 null이면 def 사용
	 *
	 * @param str String
	 * @param def String
	 * @return double
	 */
	public static long convLong(String str, String def) {
		str = replace(nullCheck(str), ",", "");
		if ("".equals(str))
			str = def;
		return Long.parseLong(str);
	}

	/**
	 * null => ""으로 변환
	 *
	 * @param param String
	 * @return String
	 */
	public static String nullCheck(String param) {
		if (param == null || "null".equals(param)) {
			param = "";
		}
		return param.trim();
	}

	/**
	 * HTML 문자( < > " & ) => &lt; &gt; &quot; &amp;nbsp; 로 변환
	 *
	 * @param htmlstr String
	 * @return String
	 */
	public static String convTag2Char(String htmlstr) {
		String convert = new String();
		convert = replace(htmlstr, "<", "&lt;");
		convert = replace(convert, ">", "&gt;");
		convert = replace(convert, "\"", "&quot;");
		convert = replace(convert, "&nbsp;", "&amp;nbsp;");
		return convert;
	}

	/**
	 * &lt; &gt; &quot; &amp;nbsp; 를 HTML 문자( < > " & ) 로 변환
	 *
	 * @param str String
	 * @return String
	 */
	public static String convChar2Tag(String str) {
		String convert = new String();
		convert = replace(str, "&lt;", "<");
		convert = replace(convert, "&gt;", ">");
		convert = replace(convert, "&quot;", "\"");
		convert = replace(convert, "&amp;nbsp;", "&nbsp;");
		return convert;
	}

	/**
	 * 주어진 String을 encoding한다.
	 *
	 * @param str String
	 * @return String
	 */
	public static String ksc2ascii(String str) throws BaseException {
		String result = null;

		try {
			result = new String(str.getBytes("KSC5601"), "8859_1");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException(e);
		}

		return result;
	}

	/**
	 * 주어진 String을 encoding한다.
	 *
	 * @param str String
	 * @return String
	 */
	public static String ascii2ksc(String str) throws BaseException {
		String result = null;
		try {
			result = new String(str.getBytes("8859_1"), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException(e);
		}

		return result;
	}

	/**
	 * 8859-1로 encoding 한다.
	 *
	 * @param str
	 * @return
	 */
	public static String toascii(String str) throws BaseException {
		String result = null;
		try {
			result = new String(str.getBytes(), "8859_1");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException(e);
		}

		return result;
	}

	/**
	 * EUC-KR로 encoding 한다.
	 *
	 * @param str
	 * @return
	 */
	public static String toksc(String str) throws BaseException {
		String result = null;
		try {
			result = new String(str.getBytes(), "KSC5601");
		} catch (UnsupportedEncodingException e) {
			throw new BaseException(e);
		}

		return result;
	}


	public static String cutString(String str, int start) {
		if (str.length() > start) {
			return str.substring(start);
		} else {
			return "";
		}
	}


	public static String cutStringBytes(String str, int length) {
		byte[] bytes = str.getBytes();
		int len = bytes.length;
		int counter = 0;
		if (length >= len) {
			return str;
		}
		for (int i = length - 1; i >= 0; i--) {
			if ((bytes[i] & 0x80) != 0)
				counter++;
		}
		return new String(bytes, 0, length - (counter % 2));
	}

	/**
	 * 현재 월,일,요일을 가져온다.
	 *
	 * @return string
	 */
	public static String getWeek() {

		Calendar time = Calendar.getInstance();

		final String[] week = { "일", "월", "화", "수", "목", "금", "토" };

		String yyyyMMdd = getShortDate();
		String MM = yyyyMMdd.substring(4, 6);// 월

		String dd = yyyyMMdd.substring(6, 8);// 일

		StringBuffer timing = new StringBuffer();

		timing.append(MM + "월 " + dd + "일 " + week[time.get(Calendar.DAY_OF_WEEK) - 1] + "요일");

		String weekday = timing.toString();
		return weekday;

	}

	public static String mkSelBoxYear(int min, int max, String selYear) {
		StringBuffer buff = new StringBuffer();

		String nowYear = getYearString();// 현재연도를 가져온다.

		int minYear = Integer.parseInt(nowYear) - min;
		int maxYear = Integer.parseInt(nowYear) + max;

		for (int i = maxYear; i >= minYear; i--) {
			buff.append("\t\t<option value='" + i + "'");

			if (!"".equals(selYear)) {
				if ((i + "").equals(selYear))
					buff.append(" selected='yes' ");
			} else {
				if ((i + "").equals(nowYear))
					buff.append(" selected='yes' ");
			}

			buff.append(">");
			buff.append(i);
			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxYearTerm(int min, int max, String selYear, int term) {
		StringBuffer buff = new StringBuffer();

		String nowYear = getYearString();// 현재연도를 가져온다.

		int minYear = Integer.parseInt(nowYear) - min;
		int maxYear = Integer.parseInt(nowYear) + max;

		for (int i = maxYear; i >= minYear; i--) {
			buff.append("\t\t<option value='" + i + "'");

			if (!"".equals(selYear)) {
				if ((i + "").equals(selYear))
					buff.append(" selected='yes' ");
			} else {
				// term 값이 현재년도보다 클순없다.
				if (Integer.parseInt(nowYear) > term) {
					if ((i == (Integer.parseInt(nowYear) + term)))
						buff.append(" selected='yes' ");
				}
			}

			buff.append(">");
			buff.append(i);
			buff.append("</option>	\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxYearStart(int start, int end, String selYear) {
		StringBuffer buff = new StringBuffer();

		String nowYear = getYearString();// 현재연도를 가져온다.
		int startYear = start;

		int endYear = Integer.parseInt(nowYear) + end;

		for (int i = endYear; i >= startYear; i--) {
			buff.append("\t\t<option value='" + i + "'");

			/*
			 * if(!"".equals(selYear)){ if((i+"").equals(selYear)) buff.append(" selected='yes' "); }else{ if((i+"").equals(nowYear))
			 * buff.append(" selected='yes' "); }
			 */

			buff.append(">");
			buff.append(i);
			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxMonth(String selMonth) {
		StringBuffer buff = new StringBuffer();

		String nowMonth = getMonthString();

		if (selMonth == null || "".equals(selMonth.trim())) {
			selMonth = nowMonth;
		}

		for (int i = 1; i <= 12; i++) {

			if ((i + "").length() == 1) {
				buff.append("\t\t<option value='0" + i + "'");

				if (!"".equals(selMonth)) {
					if (("0" + i).equals(selMonth))
						buff.append(" selected='yes' ");
				}

			} else {
				buff.append("\t\t<option value='" + i + "'");

				if (!"".equals(selMonth)) {
					if ((i + "").equals(selMonth))
						buff.append(" selected='yes' ");
				}
			}

			buff.append(">");

			if ((i + "").length() == 1) {
				buff.append("0" + i);
			} else {
				buff.append(i);
			}

			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxDate(String selDate) {
		StringBuffer buff = new StringBuffer();

		for (int i = 1; i <= 31; i++) {

			if ((i + "").length() == 1) {
				buff.append("\t\t<option value='0" + i + "'");

				if (!"".equals(selDate)) {
					if (("0" + i).equals(selDate))
						buff.append(" selected='yes' ");
				}

			} else {
				buff.append("\t\t<option value='" + i + "'");

				if (!"".equals(selDate)) {
					if ((i + "").equals(selDate))
						buff.append(" selected='yes' ");
				}
			}

			buff.append(">");

			if ((i + "").length() == 1) {
				buff.append("0" + i);
			} else {
				buff.append(i);
			}

			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxQuarter(String selQuarter) {
		StringBuffer buff = new StringBuffer();

		for (int i = 1; i <= 4; i++) {

			if ((i + "").length() == 1) {
				buff.append("\t\t<option value='" + i + "'");

				if (!"".equals(selQuarter)) {
					if (("" + i).equals(selQuarter))
						buff.append(" selected='yes' ");
				}

			} else {
				buff.append("\t\t<option value='" + i + "'");

				if (!"".equals(selQuarter)) {
					if (("" + i).equals(selQuarter))
						buff.append(" selected='yes' ");
				}
			}

			buff.append(">");

			if ((i + "").length() == 1) {
				buff.append("0" + i);
			} else {
				buff.append(i);
			}
			buff.append("분기");

			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxTime(String selTime) {
		StringBuffer buff = new StringBuffer();

		for (int i = 0; i <= 23; i++) {
			buff.append("\t\t<option value='" + i + "'");

			if (!"".equals(selTime)) {
				if ((i + "").equals(selTime))
					buff.append(" selected='yes' ");
			}
			buff.append(">");

			if ((i + "").length() == 1) {
				buff.append("0" + i);
			} else {
				buff.append(i);
			}

			buff.append("</option>\n");
		}

		return buff.toString();
	}


	public static String mkSelBoxMin(int term, String selMin) {
		StringBuffer buff = new StringBuffer();

		for (int i = 0; i <= 59; i++) {

			if (i % term == 0 && term != 0) {
				buff.append("\t\t<option value='" + i + "'");

				if (!"".equals(selMin)) {
					if ((i + "").equals(selMin))
						buff.append(" selected='yes' ");
				}

				buff.append(">");

				if ((i + "").length() == 1) {
					buff.append("0" + i);
				} else {
					buff.append(i);
				}

				buff.append("</option>\n");
			}
		}

		return buff.toString();
	}

	/**
	 * 넘어온 파라미터배열의 키명을 대문자로 변경하여 map 에 더하여준다.
	 *
	 * @param params DataClass 파라미터배열
	 * @param map    HashMap 파라미터 배열을 더하여줄 맵 데이터
	 * @return HashMap
	 */
	public static void addParamsToUpper(DataClass params, HashMap<String, String> map) {
		String key = "";
		String val = "";
		Map.Entry<String, Object> me = null;
		Iterator<Entry<String, Object>> it = params.getMap().entrySet().iterator();

		while (it.hasNext()) {
			me = it.next();
			key = me.getKey().toString().toUpperCase();
			val = me.getValue().toString();

			map.put(key, val);
		}
	}

	/**
	 * 파일이 저장되어있는 full경로를 어플리케이션 경로로 변경시켜준다.
	 *
	 * @param fullPath String 파일이 저장되어있는 full 경로
	 * @ex D:/project/upload/mp/1354236009855.jpg -> /mp/1354236009855.jpg
	 * @return String 파일의 어플리케이션 경로
	 */
	public static String getFilePath(String fullPath) {

		String str = nullCheck(fullPath);
		if (str.indexOf("upload") > -1) {
			str = str.substring(str.indexOf("upload") + 6);
			str = str.replaceAll("\\\\", "/");
		}

		return str;
	}

	public static Boolean isImageFile(String str) {
		String ext = "";
		int idx = str.lastIndexOf(".");
		if (idx > -1) {
			ext = str.substring(idx + 1).toLowerCase();// 확장자
			if (ext.equals("jpg") || ext.equals("jpeg") || ext.equals("jpe") || ext.equals("png") || ext.equals("bmp") || ext.equals("gif")) {
				return true;
			}
		}
		return false;
	}

	public static String maskingPhoneNumber(String phoneNoStr) {
		String regEx = "(010|011|016|017|018?019)(.+)(.{4})";
		if (!Pattern.matches(regEx, phoneNoStr))
			return phoneNoStr;

		return phoneNoStr.replaceAll(regEx, "$1-****-$3");
	}

	public static String maskingName(String nameStr) {
		String tmp = nameStr;
		if (nameStr.length() < 2)
			return nameStr;
		else if (tmp.length() == 2)
			return nameStr.substring(0, 1) + "*";
		else
			return nameStr.substring(0, 1) + "*" + nameStr.substring(2);
	}

	public static String getRemoteIP(HttpServletRequest httpReq) {
		String client_ip = httpReq.getHeader("X-FORWARDED-FOR");
		if (client_ip == null || client_ip.length() == 0)
			client_ip = httpReq.getRemoteAddr();
		return client_ip;
	}

	/**
	 * 현사이트의 도메인을 가져온다.
	 *
	 * @param request
	 * @return 도메인명
	 */
	public static String getSiteDomain(HttpServletRequest request) {

		StringBuffer sb = request.getRequestURL();
		String domainStr = sb.toString();

		if (domainStr.indexOf("/ehs") > -1) {
			domainStr = domainStr.substring(0, domainStr.indexOf("/ehs"));
		}
		return domainStr;
	}

	public static String convertResult(Object param) throws BaseException {
		ObjectMapper m = new ObjectMapper();

		try {
			return m.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			throw new BaseException(e);
		}
	}

	public static String convertResult(Map<String, Object> param) throws BaseException {
		ObjectMapper m = new ObjectMapper();

		try {
			return m.writeValueAsString(param);
		} catch (JsonProcessingException e) {
			throw new BaseException(e);
		}
	}

	public static String getErrorMsg(Exception e) {
		String msg = Utility.getErrorMessage(e).replaceAll("java.lang.Exception: ", "");

		int errIdx1 = msg.indexOf("ORA-");
		int errIdx2 = msg.indexOf("###", errIdx1);
		if (errIdx1 > 0) {
			msg = msg.substring(errIdx1, errIdx2).replace("ORA-", "");
		}
		return msg;
	}

	public static String failResult(Exception e) throws BaseException {

		String msg = getErrorMsg(e);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		result.put("message", enter2BR(msg.replaceAll("\'", "′")));

		ObjectMapper m = new ObjectMapper();

		try {
			return m.writeValueAsString(result);
		} catch (JsonProcessingException e1) {
			throw new BaseException(e);
		}
	}

	public static File getFileInstance(File path, String fileNm) throws BaseException {

		return Utility.getFileInstance(path.getAbsoluteFile() + File.separator + fileNm);
	}

	public static File getFileInstance(String path, String fileNm) throws BaseException {

		return Utility.getFileInstance(path + File.separator + fileNm);
	}

	public static File getFileInstance(String pathWithFileNm) throws BaseException {

		/****************************************
		 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 1-02. 경로조작 및 자원삽입 ▶수정 내역 1. 외부 입력받은 값을 경로순회 문자열(./￦)을 제거하고 사용
		 ****************************************/

		// 지정된 디렉토리 이외의 접근을 시도하는 경우
		if (pathWithFileNm.indexOf(File.separator + "..") >= 0 && pathWithFileNm.indexOf(File.separator + "temp" + File.separator) < 0) {

			throw new BaseException("허용하지 않는 경로 탐색이 발견되었습니다");
		}

		File file = new File(pathWithFileNm);

		return file;
	}

	public static String getOriginalFilename(MultipartFile multipartFile) throws BaseException {

		/****************************************
		 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 1-05. 위험한 형식 파일 업로드 ▶수정 내역 1. 업로드 파일의 마지막 “.” 문자열의 기준으로 실제 확장자 여부를 확인하고, 대소문자 구별
		 * 2. 화이트 리스트 방식으로 허용되는 확장자로 업로드를 제한
		 ****************************************/

		String fineName = multipartFile.getOriginalFilename();

		String fileExt = fineName.substring(fineName.lastIndexOf(".") + 1).toLowerCase();
		if (!"".equals(fileExt) && !"svg".equals(fileExt) && !"png".equals(fileExt) && !"xlsx".equals(fileExt) && !"xls".equals(fileExt)
				&& !"docx".equals(fileExt) && !"docx".equals(fileExt) && !"ppt".equals(fileExt) && !"zip".equals(fileExt) && !"7z".equals(fileExt)
				&& !"pptx".equals(fileExt) && !"hwp".equals(fileExt) && !"pdf".equals(fileExt) && !"jpg".equals(fileExt) && !"jpeg".equals(fileExt)) {
			throw new BaseException ("허용하지 않는 파일 확장자가 발견되었습니다");
		}

		return multipartFile.getOriginalFilename();
	}

	/****************************************
	 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 1-05. 위험한 형식 파일 업로드 위변조체크 ▶수정 내역 1. 업로드 파일의 마지막 “.” 문자열의 기준으로 실제 확장자 여부를 확인하고, 대소문자 구별
	 * 2. 화이트 리스트 방식으로 허용되는 확장자로 업로드를 제한
	 ****************************************/
//	public static void isVailidFile(File file, String fileExt) throws BaseException {
//		boolean hitFlag = false;
//		ContentInfo ci = null;
//
//		try {
//			ci = (new ContentInfoUtil()).findMatch(file);
//			if (ci != null) {
//				String[] arrExt = ci.getFileExtensions();
//				if (arrExt != null && arrExt.length > 0) {
//					for (int i = 0; i < arrExt.length; i++) {
//						if (fileExt.equals(arrExt[i])) {
//							hitFlag = true;
//							break;
//						}
//					}
//				}
//			}
//		} catch (IOException e) {
//			// 파일정보를 취득할 수 없는 파일은 확장자 확인처리를 스킵한다.
//			hitFlag = true;
//		}
//
//		if (!hitFlag)
//			throw new BaseException("파일의 확장자 정보가 상이합니다.");
//
//		// 파일권한 변경(644)
//		file.setReadable(true);
//		file.setWritable(true, true);
//		file.setExecutable(false);
//
//		return;
//	}

	public static void redirect(HttpServletRequest req, HttpServletResponse res, String url) throws BaseException {

		/****************************************
		 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 1-06. 신뢰되지 않는 URL 주소로 자동 접속 연결 ▶수정 내역 1. 이동 할 수 있는 URL범위를 제한하여 피싱 사이트 등으로 이동 못하도록
		 * 처리
		 ****************************************/

		try {

			// 타 사이트로는 이동되지 않도록 체크
			if (url.indexOf(req.getContextPath()) < 0) {
				throw new BaseException("허용하지 않는 URL이 발견되었습니다");
			}

			res.sendRedirect(url);
		} catch (IOException e) {
			throw new BaseException(e);
		}
	}

	public static Cookie getCookieInstance(String name, String value) {

		return Utility.getCookieInstance(name, value, 0);
	}

	public static Cookie getCookieInstance(String name, String value, int maxAge) {

		final int MAX_AGE = 60 * 60 * 24;

		/****************************************
		 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 1-11. HTTP 응답분할 2-12. 사용자 하드디스크에 저장되는 쿠키를 통한 정보노출 ▶수정 내역 1. 외부 입력값에서 개행문자(￦r￦n)를
		 * 제거한 후 쿠키의 값으로 설정 2. 쿠키의 만료시간은 해당 기능에 맞춰 최소로 사용
		 ****************************************/

		Cookie cookie = new Cookie(name, value);

		// 보안 프로토콜(https)에서만 사용 가능하도록 설정
		cookie.setSecure(true);

		if (maxAge > MAX_AGE) {
			maxAge = MAX_AGE;
		}

		// 최대 24시간을 넘지 않는 범위에서 사용자가 지정한 유효 기간을 설정
		cookie.setMaxAge(maxAge);

		return cookie;
	}

	public static void printStackTrace(Exception e) {

		logger.error(Utility.getErrorMessage(e));
	}

	public static String getErrorMessage(Exception e) {

		/****************************************
		 * ■전자정부 SW 개발·운영자를 위한 개발보안 가이드(2019.11)에 따라 아래와 같이 대응 ▶취약점 6-03. 시스템 데이터 정보노출 ▶수정 내역 1. 오류와 관련된 최소한의 정보만을 제공
		 ****************************************/

		logger.error(e.getMessage());

//		return "에러가 발생했습니다.";

		return e.getMessage();
	}

	public static Object getClone(Object srcObj) {

		Object tgtObj = new Object();

		BeanUtils.copyProperties(srcObj, tgtObj);

		return tgtObj;
	}

	public static void responseWrite(HttpServletResponse response, String str) throws IOException {
		PrintWriter printWriter = response.getWriter();
		printWriter.write(str);
	}

	public static void mkdir(File dir) {
		dir.setExecutable(false, true);
		dir.setReadable(true);
		dir.setWritable(false, true);
		dir.mkdir();
	}

	public static void mkdirs(File dir) {
		dir.setExecutable(false, true);
		dir.setReadable(true);
		dir.setWritable(false, true);
		dir.mkdirs();
	}

	public static void write(String path, String charSet, String str) throws BaseException {

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream(path);
			osw = new OutputStreamWriter(fos, charSet);
			bw = new BufferedWriter(osw);
			bw.write(str);
		} catch (IOException e) {
			throw new BaseException(e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
		}
	}

	public static String read(String path, String charSet) throws BaseException {

		StringBuilder sb = new StringBuilder();

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader reader = null;

		try {
			fis = new FileInputStream(path);
			isr = new InputStreamReader(fis, charSet);
			reader = new BufferedReader(isr);

			String line = new String();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			throw new BaseException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new BaseException(e);
				}
			}
		}

		return sb.toString();
	}
}
