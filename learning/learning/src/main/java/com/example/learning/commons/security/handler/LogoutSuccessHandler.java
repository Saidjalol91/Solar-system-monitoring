package com.example.learning.commons.security.handler;

import com.example.learning.commons.security.model.LoginDetails;
import com.example.learning.commons.security.utils.DuplicationManager;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private DuplicationManager duplicationManager;

	@Getter
	private String defaultSuccessUrl = "/login";

	public void setDefaultSuccessUrl(String defaultSuccessUrl){
		this.defaultSuccessUrl = defaultSuccessUrl;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse res, Authentication ae) throws IOException, ServletException{
		String loginid  = req.getParameter ("username");
		req.setAttribute ("username", loginid);

		if(ae != null){
			LoginDetails ld = (LoginDetails)  ae.getPrincipal ();
			String logged_id = ld.getUsername ();

			HttpSession session = req.getSession (false);
			duplicationManager.removeSession (logged_id, session.getId ());
			session.invalidate ();
		}
		
		req.getRequestDispatcher (defaultSuccessUrl).forward (req,res);
	}
}
