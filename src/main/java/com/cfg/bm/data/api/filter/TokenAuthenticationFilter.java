package com.cfg.bm.data.api.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cfg.bm.data.api.service.UserService;
import com.cfg.bm.data.api.service.security.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	TokenService tokenService;

	@Autowired
	UserService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		Optional.ofNullable(request.getHeader("Authorization")).filter(tokenService::isTokenBearer)
				.map(tokenService::sanitizeBearer).filter(tokenService::isTokenValid).map(tokenService::getTokenId)
				.map(t -> service.findById(t).orElseThrow())
				.map(dbu -> new UsernamePasswordAuthenticationToken(dbu, null, dbu.getAuthorities()))
				.ifPresent(auth -> {
					SecurityContextHolder.getContext().setAuthentication(auth);
				});
		filterChain.doFilter(request, response);

	}

}
