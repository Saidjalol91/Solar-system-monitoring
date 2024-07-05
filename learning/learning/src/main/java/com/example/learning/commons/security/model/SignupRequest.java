package com.example.learning.commons.security.model;

import lombok.Data;

import java.sql.SQLOutput;
import java.util.Date;

@Data
public class SignupRequest {
	private String username;
	private String email;
	private String password;
	private String confirm_passwd;
	private Date reg_date;
	private Date pass_update;
}
