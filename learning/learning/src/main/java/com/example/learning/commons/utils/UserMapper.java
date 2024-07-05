package com.example.learning.commons.utils;

import com.example.learning.commons.security.model.Users;

public interface UserMapper {
	//Login

	Users getUserAccount (String userId);

	// signup

	void saveUser(Users users);
}
