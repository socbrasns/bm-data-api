package com.cfg.bm.data.api.filter;

import java.io.IOException;
import java.util.Optional;

import javax.naming.AuthenticationNotSupportedException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cfg.bm.data.api.service.UserService;
import com.cfg.bm.data.api.service.security.TokenService;
import com.cfg.bm.data.api.utils.ApplicationContextUtils;

@Service
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    TokenService tokenService;

    UserService service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	tokenService = ApplicationContextUtils.getBean(TokenService.class);

	service = ApplicationContextUtils.getBean(UserService.class);

	if (request.getHeader("Authorization") != null) {
	    Optional.ofNullable(request.getHeader("Authorization")).filter(tokenService::isTokenBearerType)
		    .ifPresentOrElse(tokenBearer -> {
			Optional.of(tokenBearer).map(tokenService::sanitizeBearer)
				.filter(tokenService::isTokenValid)
				.map(tokenService::getTokenId).map(service::findByUsername)
				.ifPresentOrElse(credential -> {
				    Optional.of(credential).map(dbu -> new UsernamePasswordAuthenticationToken(dbu,
					    null, dbu.getAuthorities())).ifPresent(auth -> {
						SecurityContextHolder.getContext().setAuthentication(auth);
					    });
				}, () -> new AuthenticationCredentialsNotFoundException("Credentials not found"));
		    }, () -> new AuthenticationNotSupportedException("is not a Bearer Token"));

	}
	filterChain.doFilter(request, response);

    }

}
