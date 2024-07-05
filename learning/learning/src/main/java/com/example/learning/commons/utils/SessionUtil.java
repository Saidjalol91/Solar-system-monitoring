package com.example.learning.commons.utils;

import com.example.learning.commons.security.model.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SessionUtil {
	public static Users getUsers() {

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			Users users = new Users();
			users.setUsername ("");
			return users;
		}

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal == null) {
			Users users = new Users();
			users.setUsername("");
			return users;
		}
		if (principal.equals("anonymousUser")) {
			Users users = new Users();
			users.setUsername("");
			return users;
		}

		return (Users) principal;
	}

	public static Locale getLocale(HttpServletRequest req) {
		Locale locale = (Locale) WebUtils.getSessionAttribute(req, SessionLocaleResolver.class.getName() + ".LOCALE");
		if (locale == null)
			locale = new Locale("ko");
		return locale;
	}

	public static void setLocale(HttpServletRequest req, String lang) {
		Locale locale = new Locale(lang);
		WebUtils.setSessionAttribute(req, SessionLocaleResolver.class.getName() + ".LOCALE", locale);
	}

	public static Users getSessionUser(HttpServletRequest req) {
		Users sessionUser = new Users();

		HttpSession httpSession = req.getSession(false);
		if (httpSession != null) {
			sessionUser = (Users) httpSession.getAttribute("sessionUser");
			if (sessionUser == null) {
				sessionUser = SessionUtil.getUsers();
				httpSession.setAttribute("sessionUser", sessionUser);
			}
			if (sessionUser.getUser_id() == null) {
				sessionUser = SessionUtil.getUsers();
				httpSession.setAttribute("sessionUser", sessionUser);
			}
		}
		return sessionUser;
	}
}
