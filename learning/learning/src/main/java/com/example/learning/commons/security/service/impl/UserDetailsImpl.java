package com.example.learning.commons.security.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Data
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String email;

	@JsonIgnore
	private String password;
	@JsonIgnore
	private String passwd_confirm;

	public UserDetailsImpl (Long id, String username, String email, String password, String passwd_confirm) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.passwd_confirm = passwd_confirm;
	}

//	public static UserDetailsImpl build(User user) {
//
//		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
//	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
