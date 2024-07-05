package com.example.learning.services;
import com.example.learning.BaseException;
import com.example.learning.models.SunDataRCVModel;
import com.example.learning.models.UserRegModel;
import java.util.Collection;
import java.util.List;

public interface UserService {

	List<UserRegModel> getUsersList (UserRegModel param) throws BaseException;

}
