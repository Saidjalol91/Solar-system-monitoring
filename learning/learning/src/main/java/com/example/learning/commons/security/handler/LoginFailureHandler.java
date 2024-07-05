package com.example.learning.commons.security.handler;


import com.example.learning.commons.security.model.LoginHistory;
import com.example.learning.commons.security.model.Users;
import com.example.learning.commons.security.service.SecurityService;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Resource(name = "securityService")
	private SecurityService securityService;

	private String defaultFailureUrl = "/login";

	public String getDefaultFailureUrl () {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl (String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

	@Override
	public void onAuthenticationFailure (HttpServletRequest req, HttpServletResponse res, AuthenticationException ae) throws IOException, ServletException{
		String loginid = req.getParameter ("username");
		String password = req.getParameter ("password");

		Users user = new Users ();
		user.setUser_id (req.getParameter ("username"));
		user = securityService.getUserByUsername (user);
		ObjectMapper m = new ObjectMapper ();
		String json_data = m.writeValueAsString (user);

		if (user != null) {
			if (loginid.equals (password) && user.getPassword ().equals ("password")) {
				req.setAttribute ("errorcode", "9002");
				req.setAttribute ("errormsg", "Please set your password");
				req.setAttribute ("session", json_data);
				req.setAttribute ("loginfail_cnt", 0);
			} else if (!user.isAccountNonExpired ()) {
				req.setAttribute ("errorCode", "9003");
				req.setAttribute ("errormsg", "you changed password over 90 days, Please reset your password");
				req.setAttribute ("session", json_data);
				req.setAttribute ("loginfail_cnt", 0);
			} else if (!user.isAccountNonLocked ()) {
				req.setAttribute ("errorCode", "9006");
				req.setAttribute ("errormsg", "You do not loggined long period, therefor you could not login, <br> Please ask to Administrator");
				req.setAttribute ("session", json_data);
				req.setAttribute ("error", true);
				req.setAttribute ("loginfail_cnt", 0);
			} else if (user.getLoginfail_cnt () >= 5) {
				req.setAttribute ("errorCode", "9004");
				req.setAttribute ("errormsg", "You typed more than 5 times wrong password , thus the account is locked. <br> Please ask to Administrator");
				req.setAttribute ("session", "");
				req.setAttribute ("loginfail_cnt", 0);
			} else {
				securityService.addLoginFailCount (user);

				String client_ip = req.getHeader ("X-FORWARDED_FOR");
				if (client_ip == null || client_ip.isEmpty ()) client_ip = req.getRemoteUser ();
				securityService.insertLoginHistory (new LoginHistory ("N", user.getUser_mst_id (), client_ip));

				req.setAttribute ("errorCode", "9000");
				req.setAttribute ("errormsg", "Your id or password is not matched.<br> If you type more than 5 times wrong id or password, your account will be locked");
				req.setAttribute ("session", "");
				req.setAttribute ("loginfail_cnt", (user.getLoginfail_cnt () + 1));
			}
			req.setAttribute ("username", loginid);
		} else {
			req.setAttribute ("errorcode", "9000");
			req.setAttribute ("errormsg", "Your id or password is not matched.<br> If you type more than 5 times wrong id or password, your account will be locked");
			req.setAttribute ("loginfail_cnt", 1);
			req.setAttribute ("session", "");
			req.setAttribute ("username", "");
		}

		req.setAttribute ("error", true);

		req.getRequestDispatcher (defaultFailureUrl).forward (req, res);
	}
}
