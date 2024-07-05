package com.example.learning.Dao;

import com.example.learning.commons.AbstractDao;
import com.example.learning.commons.security.model.SignupRequest;
import com.example.learning.models.UserRegModel;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("UserRegDao")
public class UserRegDao extends AbstractDao {

	public Integer insertUserReg(UserRegModel param){return (int) insert ("insertUserReg", param);}

	public Integer signUpUser(SignupRequest param){return (int) insert ("registerUser", param);}

	public List selectUsers(UserRegModel param) {return  selectList ("getUsersList", param);}

}
