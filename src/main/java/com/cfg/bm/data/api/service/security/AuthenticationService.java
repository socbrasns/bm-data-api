package com.cfg.bm.data.api.service.security;

import java.security.Principal;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.model.security.Token;
import com.cfg.bm.data.api.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class AuthenticationService implements UserDetailsService {

    private UserService userService;

    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	return Optional.ofNullable(userService.findByUsername(username))
		.orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    @Transactional
    public Token login(Authentication auth) {

	var u = userService.findByUsername(Principal.class.cast(auth.getPrincipal()).getName());
	var t = verifyInvalidUserAccount(u)
		.orElse(Token.builder().type("Bearer").token(tokenService.generateToken(u)).build());
	userService.save(u);
	return t;
    }

    private Optional<Token> verifyInvalidUserAccount(User user) {

	if (user.isAccountNonExpired()) { // expired
	    return Optional.of(Token.builder().type("Error").token("Please contact Administratos.").build());
	} else if (user.isAccountNonLocked()) { // locked to use before email confirmation
	    return Optional.of(
		    Token.builder().type("Error").token("You need to confirm your e-mail to use this API.").build());
	} else if (user.isCredentialsNonExpired()) { // password expired to password reset use
	    return Optional.of(Token.builder().type("Error")
		    .token("Reset your password, follow the link in your e-mail.").build());
	} else if (user.isEnabled()) { // inactive deleted
	    return Optional.of(Token.builder().type("Error").token("Account inactive.").build());
	} else {
	    return Optional.empty();
	}
    }
}
