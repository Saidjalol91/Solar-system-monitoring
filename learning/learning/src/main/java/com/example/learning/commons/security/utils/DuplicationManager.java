package com.example.learning.commons.security.utils;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class DuplicationManager {
	private final long SESSION_TIME = 6;

	private final Map<String, AccessVO> session = new HashMap<String, AccessVO> ();

	public boolean isDuplicate(String id) {

		if(this.session.containsKey(id)) {
			if(!expireTime(this.session.get(id).getAccessTime()) && this.session.get(id).isVerify()) {
				return true;
			}
			this.session.remove(id);
		}
		return false;
	}

	public void addSession(String id, String sessionID) {

		cleanSession();

		AccessVO vo = new AccessVO();
		vo.setVerify(true);
		vo.setAccessTime(new Date ());
		vo.setSessionId(sessionID);
		this.session.put(id, vo);
	}

	public void updateSession(String id, String sessionID) {

		if(this.session.containsKey(id)) {
			if(this.session.get(id).getSessionId().equals(sessionID)) {
				AccessVO vo = new AccessVO();
				vo.setVerify(true);
				vo.setAccessTime(new Date());
				vo.setSessionId(sessionID);
				this.session.put(id, vo);
			}
		}
	}

	public Map<String, AccessVO> getAllSession() {

		Iterator<String> keys = this.session.keySet().iterator();
		while(keys.hasNext()) {

			String key = keys.next();

			if(expireTime(this.session.get(key).getAccessTime())) {
				AccessVO vo = this.session.get(key);
				vo.setVerify(false);
				this.session.put(key, vo);
			}
		}

		return this.session;
	}

	public void removeSession(String id, String sessionID) {
		if(this.session.containsKey(id)) {
			if(this.session.get(id).getSessionId().equals(sessionID)) {
				this.session.remove(id);
			}
		}
	}

	public void cleanSession() {
		Iterator<String> keys = this.session.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if(expireTime(this.session.get(key).getAccessTime()) || !this.session.get(key).isVerify()) {
				this.session.remove(key);
			}
		}
	}

	private boolean expireTime(Date date) {

		long diff = new Date().getTime() - date.getTime();
		long min = diff / (1000 * 60);

		// 세션 타임이 지났으면
		return min >= SESSION_TIME;
	}

	public class AccessVO {

		private boolean verify = false;
		private String sessionId;
		private Date accessTime;

		public boolean isVerify() {
			return verify;
		}
		public void setVerify(boolean verify) {
			this.verify = verify;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public Date getAccessTime() {
			return accessTime;
		}
		public void setAccessTime(Date accessTime) {
			this.accessTime = accessTime;
		}
	}
}
