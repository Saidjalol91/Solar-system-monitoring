package com.example.learning.commons.security.model;

import java.util.Date;

public class LoginHistory {
	private final int user_mst_id;
	private final String ip_address;
	private Date logintime;
	private final String success;

	public LoginHistory(String success, int user_mst_id, String remoteAddress){
		this.success = success;
		this.ip_address = remoteAddress;
		this.user_mst_id = user_mst_id;
	}
}
