package com.example.learning.commons.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
public class Users implements UserDetails {

	private static final long serialVersionUID = 1L;

	private int user_mst_id;
	private String user_id;

	private String username;
	private String password;
	private int loginfail_cnt;
	//private String userAuth = "ADMIN";

	private LoginHistory loginHistory;

    private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities () {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired () { return true; }
	@Override
	public boolean isAccountNonLocked () { return true; }
	@Override
	public boolean isCredentialsNonExpired () { return true; }
	@Override
	public boolean isEnabled () { return true; }

	public void setUsername (String username) {
		this.username = username;
	}
}
