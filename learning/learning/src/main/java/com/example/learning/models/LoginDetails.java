package com.example.learning.models;

import com.example.learning.commons.security.model.Users;

public class LoginDetails extends org.springframework.security.core.userdetails.User{

	private static final long serialVersionUID = 1L;
	private Users user;

	public LoginDetails(Users user){
		super
				(
						user.getUsername (),
						user.getPassword (),
						user.getAuthorities ()
				);
		this.user = user;
	}
	public Users getUser(){
		return user;
	}
	public void setUser(Users user){
		this.user  = user;
	}
}
