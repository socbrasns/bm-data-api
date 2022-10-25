package com.cfg.bm.data.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.security.Login;
import com.cfg.bm.data.api.model.security.Token;
import com.cfg.bm.data.api.service.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public ResponseEntity<Token> auth(@RequestBody @Validated Login login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				login.getUsername(), login.getPassword());

		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		String token = tokenService.generateToken(authentication);

		return ResponseEntity.ok(Token.builder().type("Bearer").token(token).build());

	}

}
