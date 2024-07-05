package com.example.learning.libraries;


import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class HttpCall {
	protected String charSet = "UTF-8";
	protected boolean debug = false;
	protected int timeout = -1;
	Logger log = Logger.getLogger(String.valueOf (getClass()));

	public HttpCall() {
	}

	public HttpCall(String charSet) {
		this.charSet = charSet;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Map<String, Object> getUrlCall(String url, Object data, String method) throws Exception {
		return this.getUrlCall(url, data, method, null);
	}

	private SSLSocketFactory getSSLSocketFactory() throws Exception {
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0] = tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new SecureRandom ());
		return sc.getSocketFactory();
	}

	public HttpURLConnection getHttpUrlConnection(String url, Object data, String method, Map<String, String> headers) throws Exception {
		StringBuffer paramSB = new StringBuffer();
		String name;
		if (data instanceof Map) {
			Map<String, Object> param = (Map)data;
			Iterator<String> iter = param.keySet().iterator();

			for(int i = 0; iter.hasNext(); ++i) {
				name = iter.next();
				if (i > 0) {
					paramSB.append("&");
				}

				Object v = param.get(name);
				paramSB.append(name + "=" + (v instanceof Integer ? Integer.toString((Integer)v) : (v instanceof String ? URLEncoder.encode((String)v, this.charSet) : (v instanceof Double ? Double.toString((Double)v) : v))));
			}
		} else if (!(data instanceof InputStream) && data != null) {
			paramSB.append((String)data);
		}

		if ("GET".equalsIgnoreCase (method)) {
			url = url + (!"".contentEquals (paramSB) ? "?" : "") + paramSB;
		}

		if (this.debug) {
			this.log.info("[URL CALL] START getUrlCall ================================================");
		}

		if (this.debug) {
			this.log.info("[URL CALL] URL : " + url);
		}

		if (!"GET".equalsIgnoreCase (method) && !(data instanceof InputStream) && this.debug) {
			this.log.info("[URL CALL] PARAMETER : " + (paramSB));
		}

		URL URLObj = new URL(url);
		HttpURLConnection connection = (HttpURLConnection)URLObj.openConnection();
		connection.setRequestMethod(method);
		connection.setUseCaches(false);
		connection.setDefaultUseCaches(false);
		if (this.timeout > 0) {
			connection.setConnectTimeout(this.timeout);
		}

		if (this.timeout > 0) {
			connection.setReadTimeout(this.timeout);
		}

		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5");
		connection.setRequestProperty("Accept-Language", "ko_KR,ko;q=0.8");
		if (headers != null && headers instanceof Map) {
			Iterator<String> iter = headers.keySet().iterator();

			while(iter.hasNext()) {
				name = iter.next();
				connection.setRequestProperty(name, headers.get(name));
			}
		}

		connection.setDoOutput(false);
		if (!"GET".equalsIgnoreCase (method)) {
			OutputStream wr = connection.getOutputStream();
			if (data instanceof InputStream is) {
				byte[] b = new byte[1024];
				//int len = true;

				int len;
				while((len = is.read(b)) >= 0) {
					wr.write(b, 0, len);
					wr.flush();
				}
			} else {
				wr.write(paramSB.toString().getBytes());
			}

			wr.flush();
			wr.close();
		}

		connection.connect();
		return connection;
	}

	public HttpsURLConnection getHttpsUrlConnection(String url, Object data, String method, Map<String, String> headers) throws Exception {
		StringBuffer paramSB = new StringBuffer();
		String name;
		if (data instanceof Map) {
			Map<String, Object> param = (Map)data;
			Iterator<String> iter = param.keySet().iterator();

			for(int i = 0; iter.hasNext(); ++i) {
				name = iter.next();
				if (i > 0) {
					paramSB.append("&");
				}

				Object v = param.get(name);
				paramSB.append(name + "=" + (v instanceof Integer ? Integer.toString((Integer)v) : (v instanceof String ? URLEncoder.encode((String)v, this.charSet) : (v instanceof Double ? Double.toString((Double)v) : v))));
			}
		} else if (!(data instanceof InputStream) && data != null) {
			paramSB.append((String)data);
		}

		if ("GET".equalsIgnoreCase (method)) {
			url = url + (!"".contentEquals (paramSB) ? "?" : "") + paramSB;
		}

		if (this.debug) {
			this.log.info("[URL CALL] START getUrlCall ================================================");
		}

		if (this.debug) {
			this.log.info("[URL CALL] URL : " + url);
		}

		if (!"GET".equalsIgnoreCase (method) && !(data instanceof InputStream) && this.debug) {
			this.log.info("[URL CALL] PARAMETER : " + (paramSB));
		}

		URL URLObj = new URL(url);
		HttpsURLConnection connection = (HttpsURLConnection)URLObj.openConnection();
		System.out.println("Method : " + method);
		connection.setSSLSocketFactory(this.getSSLSocketFactory());
		connection.setHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String urlHostName, SSLSession session) {
				return true;
			}
		});
		connection.setDoOutput(!"GET".equalsIgnoreCase (method));
		connection.setRequestMethod(method);
		connection.setUseCaches(false);
		connection.setDefaultUseCaches(false);
		if (this.timeout > 0) {
			connection.setConnectTimeout(this.timeout);
		}

		if (this.timeout > 0) {
			connection.setReadTimeout(this.timeout);
		}

		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.0) AppleWebKit/536.5 (KHTML, like Gecko) Chrome/19.0.1084.46 Safari/536.5");
		connection.setRequestProperty("Accept-Language", "ko_KR,ko;q=0.8");
		if (headers != null && headers instanceof Map) {
			Iterator<String> iter = headers.keySet().iterator();

			while(iter.hasNext()) {
				name = iter.next();
				connection.setRequestProperty(name, headers.get(name));
			}
		}

		if (connection.getDoOutput()) {
			OutputStream wr = connection.getOutputStream();
			if (data instanceof InputStream is) {
				byte[] b = new byte[1024];
				//int len = true;

				int len;
				while((len = is.read(b)) >= 0) {
					wr.write(b, 0, len);
					wr.flush();
				}
			} else {
				wr.write(paramSB.toString().getBytes());
			}

			wr.flush();
			wr.close();
		}

		connection.connect();
		return connection;
	}

	public HttpURLConnection getHttpUrlConnection(String url) throws Exception {
		return this.getHttpUrlConnection(url, null, "GET", null);
	}

	public Map<String, Object> getUrlCall(String url, Object data, String method, Map<String, String> headers) throws Exception {
		Map<String, Object> result = null;
		if (url.startsWith("https://")) {
			result = this.getHttpsUrlCall(url, data, method, headers);
		} else {
			result = this.getHttpUrlCall(url, data, method, headers);
		}

		return result;
	}

	public Map<String, Object> getHttpUrlCall(String url, Object data, String method, Map<String, String> headers) throws Exception {
		Map<String, Object> returnMap = new HashMap();
		HttpURLConnection connection = this.getHttpUrlConnection(url, data, method, headers);
		String response = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;

		try {
			is = connection.getInputStream();
			byte[] b = new byte[1024];
			//int len = true;

			int len;
			while((len = is.read(b)) > 0) {
				baos.write(b, 0, len);
			}

			response = baos.toString(this.charSet);
			is.close();
		} catch (IOException var12) {
			if (this.debug) {
				System.err.println("[URL CALL] EXCETPTION : " + var12.getMessage());
			}
		}

		int responseCode = connection.getResponseCode();
		String responseCodeMessage = connection.getResponseMessage();
		returnMap.put("responseCodeMessage", responseCodeMessage);
		returnMap.put("responseCode", responseCode);
		returnMap.put("response", response);
		if (this.debug) {
			this.log.info("[URL CALL] RESULT : " + (returnMap == null ? null : returnMap.toString()));
		}

		if (this.debug) {
			this.log.info("[URL CALL] END ================================================");
		}

		return returnMap;
	}

	public Map<String, Object> getHttpsUrlCall(String url, Object data, String method, Map<String, String> headers) throws Exception {
		Map<String, Object> returnMap = new HashMap();
		HttpsURLConnection connection = this.getHttpsUrlConnection(url, data, method, headers);
		String response = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;

		try {
			is = connection.getInputStream();
			byte[] b = new byte[1024];
			//int len = true;

			int len;
			while((len = is.read(b)) > 0) {
				baos.write(b, 0, len);
			}

			response = baos.toString(this.charSet);
			is.close();
		} catch (IOException var12) {
			if (this.debug) {
				System.err.println("[URL CALL] EXCETPTION : " + var12.getMessage());
			}
		}

		int responseCode = connection.getResponseCode();
		String responseCodeMessage = connection.getResponseMessage();
		returnMap.put("responseCodeMessage", responseCodeMessage);
		returnMap.put("responseCode", responseCode);
		returnMap.put("response", response);
		if (this.debug) {
			this.log.info("[URL CALL] RESULT : " + (returnMap == null ? null : returnMap.toString()));
		}

		if (this.debug) {
			this.log.info("[URL CALL] END ================================================");
		}

		return returnMap;
	}

	public InputStream getHttpUrlInputStream(HttpURLConnection connection) throws Exception {
		InputStream is = null;
		if (connection != null) {
			is = connection.getInputStream();
		}

		return is;
	}

	public static Map<String, List<String>> getHttpUrlHeaders(HttpURLConnection connection) {
		Map<String, List<String>> headers = null;
		if (connection != null) {
			headers = connection.getHeaderFields();
		}

		return headers;
	}

	public static List<String> getHttpUrlHeader(HttpURLConnection connection, String key) {
		Map<String, List<String>> headers = getHttpUrlHeaders(connection);
		return headers != null ? headers.get(key) : null;
	}

	public static void main(String[] args) throws Exception {
		Map<String, String> headerMap = new HashMap ();
		headerMap.put("CID", "CID12345");
		headerMap.put("Authorization", "AUTH_KEY_12345");
		String url = "https://119.205.235.142:10443/v1/api/auth";
		HttpCall http = new HttpCall();
		http.setDebug(true);
		Map<String, Object> tokenResponse = http.getUrlCall(url, "", "GET", headerMap);
		System.out.println(tokenResponse);
	}

	class miTM implements TrustManager, X509TrustManager {
		miTM() {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean isServerTrusted(X509Certificate[] certs) {
			return true;
		}

		public boolean isClientTrusted(X509Certificate[] certs) {
			return true;
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
		}
	}
}
