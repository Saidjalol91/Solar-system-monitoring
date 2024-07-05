package com.example.learning.commons.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenGenerator;

	@Override
	protected void doFilterInternal (HttpServletRequest request,
									 HttpServletResponse response,
									 FilterChain filterChain) throws ServletException, IOException {

		String token = getJWTFromRequest(request);
		if(StringUtils.hasText (token) && tokenGenerator.validateToken (token)){
//			String username = tokenGenerator.getUsernameFromJwt (token);

		}
		filterChain.doFilter (request, response);
	}

	private String getJWTFromRequest(HttpServletRequest request){
		String bearerToken = request.getHeader ("Authorization");
		if(StringUtils.hasText (bearerToken) && bearerToken.startsWith ("Bearer ")){
			return bearerToken.substring (7, bearerToken.length ());
		}
		return null;
	}
}
