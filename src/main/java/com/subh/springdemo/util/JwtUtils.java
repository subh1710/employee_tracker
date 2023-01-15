package com.subh.springdemo.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.subh.springdemo.common.AccessDeniedException;
import com.subh.springdemo.entity.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private static String secret = "This_is_secret";
	private static long expiryDuration = 60 * 60;

	public String generateJwt(Employee employee) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expiryDuration * 1000;

		Date issuedAt = new Date(milliTime);
		Date expiryAt = new Date(expiryTime);

		// claims
		Claims claims = Jwts.claims().setIssuer(String.valueOf(employee.getId())).setIssuedAt(issuedAt)
				.setExpiration(expiryAt);

		// optional claims
		claims.put("firstName", employee.getFirstName());
		claims.put("lastName", employee.getLastName());

		// generate jwt using claims
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Claims verify(String authorization) throws Exception {

		try {
			Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			return claims;
		} catch (Exception e) {
			throw new AccessDeniedException("Access Denied");
		}

	}
}
