package com.cfg.bm.data.api.service.security;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Login;
import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.service.LoginService;

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

    @Value("${jwt.issuer}")
    private String issuer;

    @Autowired
    LoginService loginService;

    @Transactional
    public String generateToken(User user) {

	var cal = Calendar.getInstance();
	var exp = cal;
	exp.roll(Calendar.MINUTE, expiration);

	user.setLogin(loginService.save(Login.builder().tokenIssuedAt(cal).tokenExpirationDate(exp).build()));

	Claims claims = getClaims(user);
	return Jwts.builder().setIssuer(claims.getIssuer()).setSubject(claims.getSubject())
		.setIssuedAt(claims.getIssuedAt()).setExpiration(claims.getExpiration()).setId(claims.getId())
		.signWith(SignatureAlgorithm.HS256, secret).setClaims(claims).compact();
    }

    public Claims getClaims(User user) {
	Claims claims = Jwts.claims();
	claims.put(Claims.ISSUER, issuer);
	claims.put(Claims.ID, user.getUsername());
	claims.put(Claims.SUBJECT, String.valueOf(user.getUuid().getMostSignificantBits()));
	claims.put(Claims.ISSUED_AT, user.getLogin().getTokenIssuedAt().getTime());
	claims.put(Claims.EXPIRATION, user.getLogin().getTokenExpirationDate().getTime());
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
