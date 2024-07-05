package com.example.learning.commons.security.handler;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.learning.BaseException;
import com.example.learning.commons.security.model.LoginHistory;
import com.example.learning.commons.security.model.Users;
import com.example.learning.commons.security.service.SecurityService;
import com.example.learning.commons.security.utils.DuplicationManager;
import com.example.learning.commons.utils.SessionUtil;
import com.example.learning.commons.utils.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.io.IOException;
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource(name = "securityService")
	private SecurityService securityService;

	@Autowired
	private DuplicationManager duplicationManager;

	public final int SESSION_TIMEOUT_IN_SECONDS = 60 * 60 * 8;

	static  final int COOKIES_MAX_AGE = 60 *60 * 24 * 7;
	private RequestCache  requestCache  =new HttpSessionRequestCache ();

	@Override
	public void setRequestCache(RequestCache requestCache){
		this.requestCache = requestCache;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) throws ServletException, IOException {
		try{
			//session time controlling
			req.getSession ().setMaxInactiveInterval (SESSION_TIMEOUT_IN_SECONDS);

			// ID saving

			String remember = req.getParameter ("remember");
			if(remember != null){
				String username = ((UserDetails) authentication.getPrincipal ()).getUsername ();
				Cookie cookie = Utility.getCookieInstance ("remember_username", username, COOKIES_MAX_AGE);
				res.addCookie (cookie);
			}
			else {
				Cookie cookie =  Utility.getCookieInstance ("remember_username","",0);
				res.addCookie (cookie);
			}
			String targetUrl = new String ();

			ObjectMapper m = new ObjectMapper ();
			Users sessionUser = SessionUtil.getUsers ();
			String json_data = m.writeValueAsString (sessionUser);

			if(sessionUser.getLoginfail_cnt () >= 5){
				req.setAttribute ("errorCode", "9004");
				req.setAttribute ("errormsg", "You tried wrong password more than 5 times and your account is blocked. <br> Please contact to administrator");
				req.setAttribute ("error", true);
				req.setAttribute ("loginfail_cnt", sessionUser.getLoginfail_cnt ());

				SessionUtil.getSessionUser (req).setUser_id (null);

				req.getRequestDispatcher ("/login").forward (req,res);
			}
			else if(!sessionUser.isAccountNonLocked ()){
				req.setAttribute ("errorCode", "9006");
				req.setAttribute ("errormsg", "According to not to login long period, you could nor login. <br> Please contact to Administrator");
				req.setAttribute ("session", json_data);
				req.setAttribute ("error", true);
				req.setAttribute ("loginfail_cnt", 0);

				SessionUtil.getSessionUser (req).setUser_id (null);
				req.getRequestDispatcher ("/login").forward (req,res);
			}
			else if(!sessionUser.isAccountNonExpired ()){
				req.setAttribute ("errorCode", "9003");
				req.setAttribute ("errormsg", "Your password 90 days passed, Please reset your password");
				req.setAttribute ("session", json_data);
				req.setAttribute ("error", true);
				req.setAttribute ("loginfail_cnt", 0);

				SessionUtil.getSessionUser (req).setUser_id (null);

				req.getRequestDispatcher ("/login").forward (req,res);
			}
			else{
				//login session list registartion
				HttpSession session = req.getSession (false);
				duplicationManager.addSession (sessionUser.getUser_id (), session.getId ());

				//login fail reset

				Users users =  new Users ();
				users.setUser_mst_id (sessionUser.getUser_mst_id ());
				securityService.resetLoginFailCount (users);

				// login history save logic

				String client_ip = req.getHeader ("X-FORWARDED-FOR");
				if(client_ip == null || client_ip.length () == 0){
					client_ip = req.getRemoteAddr ();
				}
				securityService.insertLoginHistory (new LoginHistory ("Y", sessionUser.getUser_mst_id (), client_ip));

				SavedRequest savedRequest = requestCache.getRequest (req, res);
				if(savedRequest == null){
					Utility.redirect (req, res,req.getContextPath () + "/index");
					return;
				}
				targetUrl = savedRequest.getRedirectUrl ();

				if(targetUrl.contains ("stomp")){
					Utility.redirect (req, res,req.getContextPath () + "/index");
					return;
				}
				Utility.redirect (req, res,targetUrl);

			}
		} catch (BaseException e) {
			throw new ServletException ("There is something wrong with network, please check your network");
		}
	}
}
