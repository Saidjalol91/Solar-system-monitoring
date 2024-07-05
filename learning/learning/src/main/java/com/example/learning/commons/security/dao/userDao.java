package com.example.learning.commons.security.dao;

import com.example.learning.commons.AbstractDao;
import com.example.learning.commons.security.model.Users;

public class userDao extends AbstractDao {
	public Users findByUsername(Users users){return (Users) selectOne ("findByUsername", users);}


}
