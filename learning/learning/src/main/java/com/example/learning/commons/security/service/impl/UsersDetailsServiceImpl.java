package com.example.learning.commons.security.service.impl;

import com.example.learning.BaseException;
import com.example.learning.commons.security.dao.SecurityDao;
import com.example.learning.commons.security.model.LoginRequest;
import com.example.learning.commons.security.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service("userDetailsService")
public class UsersDetailsServiceImpl implements UserDetailsService {

	@Resource(name ="securityDao")
	SecurityDao securityDao;

	@Override
	@Transactional
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		Users users = new Users ();
		users.setUsername (username);

		try {
			users = (Users) securityDao.selectOne ("getUserByUsername", users);

			if (users == null ) {
				throw new UsernameNotFoundException (username);
			}
			return users;
		} catch (UsernameNotFoundException e) {
			users = new Users ();
			users.setUsername (username);
			return (UserDetails) users;
		} catch (DataAccessException e) {
			//throw new BaseException (e);
		}
		return null;
	}

}
