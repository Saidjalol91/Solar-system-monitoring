package com.example.learning.commons.security.service;

import com.example.learning.commons.security.model.LoginHistory;
import com.example.learning.commons.security.model.LoginRequest;
import com.example.learning.commons.security.model.Users;

import java.util.List;

public interface SecurityService {
	Users getUserByUsername (Users users);

	List<Users> getUserList (Users users);

	void resetLoginFailCount (Users users);
	void addLoginFailCount (Users users);

	void insertLoginHistory (LoginHistory history);
}
