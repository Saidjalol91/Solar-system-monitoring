package com.example.learning.models;

import lombok.Data;

import java.util.Date;

@Data
public class LoginHistory {
	private int user_mst_id;
	private String ip_address;
	private Date logintime;
	private String success;

	public LoginHistory(String success, int user_mst_id, String remoteAddress){
		this.success = success;
		this.ip_address = remoteAddress;
		this.user_mst_id = user_mst_id;
	}

}
