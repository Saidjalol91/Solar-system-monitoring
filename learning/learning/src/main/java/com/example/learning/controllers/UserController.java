package com.example.learning.controllers;


import com.example.learning.BaseException;
import com.example.learning.Dao.UserRegDao;
import com.example.learning.models.UserRegModel;
import com.example.learning.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserRegDao userRegDao;

	@PostMapping(value = {"/insertUser"})
	public @ResponseBody ResponseEntity<Integer> createUser(HttpServletResponse response, HttpServletRequest request, UserRegModel param ){

		System.out.println ("what is response" + response);
		System.out.println ("what is request" + request);
		System.out.println ("param values" + param.getAddress ());
		System.out.println ("data");
        userRegDao.insertUserReg (param);
      return ResponseEntity.ok(200);
	}
	@RequestMapping(value ={"/getUsers"}, method = RequestMethod.GET)
	public ResponseEntity<Object> getUsers(Model model, HttpServletRequest req, HttpServletResponse res) throws BaseException {
		Map<String, Object> result = new HashMap<String, Object> ();

		UserRegModel param = new UserRegModel ();
		List<UserRegModel> re = userService.getUsersList (param);
		result.put("record",re);
		return new ResponseEntity<> (result, HttpStatus.OK);
	}
//	@PostMapping(value = {"/passchange"})
//	public @ResponseBody ResponseEntity<Integer>updatePassword(HttpServletResponse response, HttpServletRequest request) throws BaseException{
//		return ResponseEntity.ok (200);
//	}
}
