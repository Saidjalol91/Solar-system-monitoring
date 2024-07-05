package com.example.learning.commons.security.controllers;

import com.example.learning.Dao.UserRegDao;
import com.example.learning.commons.security.dao.SecurityDao;
import com.example.learning.commons.security.model.LoginRequest;
import com.example.learning.commons.security.model.SignupRequest;
import com.example.learning.commons.security.model.Users;
import com.example.learning.commons.security.utils.JwtTokenProvider;
import com.example.learning.commons.utils.SessionUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private UserRegDao userRegDao;

	@Autowired
	private SecurityDao securityDao;
	@Autowired
	private JwtTokenProvider jwtGenerator;

//


	@PostMapping("/register")
	public ResponseEntity<Object> register (@RequestBody SignupRequest signupRequest) {

		Map<String, Object> result = new HashMap<String, Object> ();
		HttpStatus statusCode = HttpStatus.OK;

		SignupRequest user = new SignupRequest ();
		user.setUsername (signupRequest.getUsername ());
		user.setEmail (signupRequest.getEmail ());
		user.setPassword (encoder.encode (signupRequest.getPassword ()));
		user.setConfirm_passwd (encoder.encode (signupRequest.getConfirm_passwd ()));
		userRegDao.signUpUser (user);

		return new ResponseEntity<Object> (result, statusCode);
	}

	// connect with databases this part it is not working properly just chekc from tommorrow
	@PostMapping("/login")
	public String authonticateUser (@Valid @RequestBody LoginRequest loginRequest, Model model) {

		List<String > roles = List.of ("Admin");
		Authentication authentication = authenticationManager.authenticate (
				new UsernamePasswordAuthenticationToken (
						loginRequest.getUsername (),
						loginRequest.getPassword ()
				)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return "success";
	}

	// here just popup password change controller logic write here
	@PostMapping("/passchange")
	public String changeUserPassword (@RequestBody LoginRequest loginRequest) {
		return "change passowrd";
	}

	@GetMapping(value = "/currentUser")
	public static Users getUser(HttpServletRequest req){
		Users sessionUser  =new Users ();
		HttpSession httpSession = req.getSession ();
		if(httpSession != null){
			sessionUser = (Users) httpSession.getAttribute ("sessionUser");
			if(sessionUser == null){
				sessionUser = SessionUtil.getUsers ();
				httpSession.setAttribute ("sessionUser", sessionUser);
			}
			if(sessionUser.getUser_id () == null){
				sessionUser = SessionUtil.getUsers ();
				httpSession.setAttribute ("sessionUser", sessionUser);
			}
		}

		System.out.println ("WHAT IS SESSION USER" + sessionUser);
		return sessionUser;

	}
}
