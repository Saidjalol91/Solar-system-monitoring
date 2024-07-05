package com.example.learning.services;

import com.example.learning.BaseException;
import com.example.learning.Dao.SunDataRCVDao;
import com.example.learning.Dao.UserRegDao;
import com.example.learning.models.SunDataRCVModel;
import com.example.learning.models.UserRegModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	private UserRegDao userRegDao;
	@Override
	public List<UserRegModel> getUsersList (UserRegModel param) throws BaseException {
		try {
			return userRegDao.selectUsers(param);
		} catch (Exception e) {
			throw new BaseException(e);
		}
	}
}
