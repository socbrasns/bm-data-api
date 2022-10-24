package com.cfg.bm.data.api.service.security;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.security.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private static final String BEARER = "Bearer ";

	@Value("${jwt.expiration}")
	private String expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {

		User usuario = (User) authentication.getPrincipal();

		Date now = new Date();
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));

		return Jwts.builder().setIssuer("cfgsolucoes.com").setSubject(String.valueOf(usuario.getId()))
				.setIssuedAt(new Date()).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret)
				.setClaims(usuario.getAuthorities().stream()
						.collect(Collectors.toMap(a -> String.valueOf(a.getId()), a -> a.getAuthority())))
				.compact();
	}

	public boolean isTokenBearer(String token) {
		return token.startsWith(BEARER);
	}

	public String sanitizeBearer(String bearer) {
		return bearer.substring(BEARER.length());
	}

	public Long getTokenId(String token) {
		Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.valueOf(body.getSubject());
	}

	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getSignature().equals(secret);
			return true;
//					.getBody().equals(dbu.getAuthorities()
//					.stream().collect(Collectors.toMap(a -> String.valueOf(a.getId()), a -> a.getAuthority())));
		} catch (Exception e) {
			return false;
		}
	}

//	public boolean isTokenExpired(String token) {
//		try {
//			return Jwts.parser().requireNotBefore(new Date()).isSigned(secret);
//		} catch (Exception e) {
//			return false;
//		}
//	}

}
