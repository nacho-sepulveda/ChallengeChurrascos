package com.ProductAPI.app.Segurity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ProductAPI.app.Errors.ProductAppException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	@Value("${app.jwt-secret}")
	private String jwtSecret;
	
	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;
	
	public String tokenGeneration(Authentication authentication) {
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + jwtExpirationInMs);
		String token = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expirationDate)
						.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return token;
	}
	public String getUsernameFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	public boolean tokenValidate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new ProductAppException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new ProductAppException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new ProductAppException(HttpStatus.BAD_REQUEST, "Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new ProductAppException(HttpStatus.BAD_REQUEST, "Token JWT not supported");
		} catch (IllegalArgumentException ex) {
			throw new ProductAppException(HttpStatus.BAD_REQUEST, "The claims JWT string is empty");
		}
	}
}
