package com.example.learning.commons.security.utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtTokenUtil {
	private static final String SECRET_KEY = "your_secret_key";
	private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

	public static String generateToken(Authentication authentication) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

		return Jwts.builder()
				.setSubject(authentication.getName())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
}
