package com.cfg.bm.data.api.service.security;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Login;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private static final String BEARER = "Bearer ";

    @Value("${jwt.expiration}")
    private Integer expiration;

    @Value("${jwt.secret}")
    private String secret;

    private String issuer = "cfgsolucoes.com";

    public String generateTokenRememberme(Authentication rememberMeAuthenticationToken) {

	return "token_rememberme";
    }

    public String generateToken(Authentication authentication, Login login) {

	login.setTokenIssuedAt(Calendar.getInstance());
	login.setTokenExpirationDate(login.getTokenIssuedAt());
	login.getTokenExpirationDate().roll(Calendar.MINUTE, expiration);

	Claims claims = getClaims(login);

	return Jwts.builder().setIssuer(claims.getIssuer()).setSubject(claims.getSubject())
		.setIssuedAt(claims.getIssuedAt()).setExpiration(claims.getExpiration()).setId(claims.getId())
		.signWith(SignatureAlgorithm.HS256, secret).setClaims(claims).compact();
    }

    public Claims getClaims(Login login) {
	Claims claims = Jwts.claims();
	claims.put(Claims.ISSUER, issuer);
	claims.put(Claims.ID, login.getLogedUser().getUsername());
	claims.put(Claims.SUBJECT, String.valueOf(login.getLogedUser().getUuid().getMostSignificantBits()));
	claims.put(Claims.ISSUED_AT, login.getTokenIssuedAt().getTime());
	claims.put(Claims.EXPIRATION, login.getTokenExpirationDate().getTime());
	return claims;
    }

    public boolean isTokenBearerType(String token) {
	return token.startsWith(BEARER);
    }

    public String sanitizeBearer(String bearer) {
	return bearer.substring(BEARER.length());
    }

    public String getTokenId(String token) {
	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getId();
    }

    public boolean isTokenValid(String token) {
	try {
	    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//	    Jwts.parser().setSigningKey(secret).requireIssuer(claims.getIssuer()).requireId(claims.getId())
//		    .requireSubject(claims.getSubject()).requireIssuedAt(claims.getIssuedAt())
//		    .requireExpiration(claims.getExpiration());
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

}
