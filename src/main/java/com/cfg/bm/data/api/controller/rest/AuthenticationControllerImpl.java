package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.AuthenticationController;
import com.cfg.bm.data.api.model.Login;
import com.cfg.bm.data.api.model.security.Token;
import com.cfg.bm.data.api.service.security.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class AuthenticationControllerImpl implements AuthenticationController {

    private AuthenticationService authenticationService;

    private AuthenticationManager authenticationManager;

    @Override
    public Token auth(@Valid Login login) {
	return authenticationService.login(authenticationManager
		.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())));

    }

}
