package com.example.learning.commons.security.service.impl;

import com.example.learning.commons.security.dao.SecurityDao;
import com.example.learning.commons.security.model.LoginHistory;
import com.example.learning.commons.security.model.LoginRequest;
import com.example.learning.commons.security.model.Users;
import com.example.learning.commons.security.service.SecurityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("securityService")
public class SecurityServiceImpl implements SecurityService {
	//@Resource(name = "securityDao")
	private SecurityDao securityDao;
	@Override
	public Users getUserByUsername (Users users) {return securityDao.getUsersByUsername (users);}

	@Override
	public List<Users> getUserList (Users users) {
		return null;
	}

	@Override
	public void resetLoginFailCount (Users users) {

	}

	@Override
	public void addLoginFailCount (Users users) {

	}

	@Override
	public void insertLoginHistory (LoginHistory history) {

	}


//	@Override
//	public void insertLoginHistory (LoginHistory history) {
//
//	}
//	@Override
//	public List<LoginRequest> getUserList (LoginRequest users) {return securityDao.getUserList (users);}
//
//	/**
//	 * @param users
//	 */
//	@Override
//	public void resetLoginFailCount (Users users) {securityDao.resetLoginFailCount (users);}
//	@Override
//	public void addLoginFailCount (Users users) {securityDao.addLoginFailCount (users);}
//	@Override
//	public void insertLoginHistory (LoginHistory history) {securityDao.insertLoginHistory (history);}
}
