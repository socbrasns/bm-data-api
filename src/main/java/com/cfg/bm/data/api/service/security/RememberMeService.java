package com.cfg.bm.data.api.service.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.RememberMeServices;

import com.cfg.bm.data.api.service.UserService;

import io.swagger.v3.oas.annotations.servers.Server;

@Server
public class RememberMeService implements RememberMeServices {

    @Autowired
    UserService userService;

    @Value("${rememberme.expiration}")
    private String expiration;

    @Value("${rememberme.secret}")
    private String secret;

    @Value("${rememberme.key}")
    private String key;

    @Override
    public Authentication autoLogin(HttpServletRequest request, HttpServletResponse response) {
//	Jwts.builder().
//	if(request.authenticate(response));
	return null;
    }

    @Override
    public void loginFail(HttpServletRequest request, HttpServletResponse response) {
	response.getTrailerFields().get().remove(key);

    }

    @Override
    public void loginSuccess(HttpServletRequest request, HttpServletResponse response,
	    Authentication successfulAuthentication) {
//	RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(key,
//		Principal.class.cast(successfulAuthentication.getPrincipal()),
//		successfulAuthentication.getAuthorities());
//
//	Date now = new Date();
//	Date exp = new Date(now.getTime() + Long.parseLong(expiration));
//
//	var token = Jwts.builder().setIssuer("cfgsolucoes.com").setSubject(rememberMeAuthenticationToken.getName())
//		.setIssuedAt(new Date()).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret)
//		.setPayload("Hello, nice to see you again!").compact();
//	response.getTrailerFields().get().put(key, token);
    }

}
