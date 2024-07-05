package com.example.learning.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;

@Data
public class Authority  implements GrantedAuthority {
	private static final long serialVersionUID = 1000000002;

	private int user_mst_id;
	private String user_id;
	private String auth;
	private String assign_by;
	private Date assign_date;
	@Override
	public String getAuthority () {
		return "ROLE_" + auth;
	}
}
