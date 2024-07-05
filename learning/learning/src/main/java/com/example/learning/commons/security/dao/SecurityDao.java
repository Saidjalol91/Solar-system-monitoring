package com.example.learning.commons.security.dao;

import com.example.learning.commons.AbstractDao;
import com.example.learning.commons.security.model.LoginRequest;
import com.example.learning.commons.security.model.Users;
import org.springframework.stereotype.Repository;

@Repository("securityDao")
public class SecurityDao extends AbstractDao {

	public Users getUsersByUsername(Users users){ return (Users) selectOne ("getUserByUsername",users); }

//	@SuppressWarnings ("unchecked")
//	public List<GrantedAuthority> getAuthorities(Users users){
//		return selectList("getAuthorities", users);
//	}
//	@SuppressWarnings ("unchecked")
//	public List<Users>getUserList(Users users){return selectList("getUserList", users);}
//
//	public void resetLoginFailCount(Users users){ update ("resetLoginFailCount", users);}
//
//	public void addLoginFailCount(Users users) {update ("addLoginFailCount", users);}
//
//	public void insertLoginHistory(LoginHistory history) {insert ("insertLoginHistory", history);}

}
