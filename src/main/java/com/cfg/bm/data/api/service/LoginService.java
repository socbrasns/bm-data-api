package com.cfg.bm.data.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Login;
import com.cfg.bm.data.api.repository.LoginRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class LoginService {

    LoginRepository repository;

    public Login save(Login login) {
	return repository.save(login);
    }

//    public void doRememberme(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken, Login login,
//	    HttpServletResponse httpServletResponse) {
//	HttpHeaders headers = new HttpHeaders();
//	if (login.isRememberme()) {
//	    RememberMeAuthenticationToken rememberMeAuthenticationToken = new RememberMeAuthenticationToken(
//		    "rememberme", usernamePasswordAuthenticationToken,
//		    usernamePasswordAuthenticationToken.getAuthorities());
//	    httpServletResponse.addCookie(createRememberCookie(Token.builder().type("rememberme")
//		    .token(tokenService.generateTokenRememberme(rememberMeAuthenticationToken)).build()));
//	} else {
//	    httpServletResponse.addCookie(removeRememberCookie());
//	}
//    }
//
//    private Cookie removeRememberCookie() {
//	Cookie cookie = new Cookie("rememberme", null);
//	cookie.setMaxAge(0); // expires in 7 days
//	cookie.setSecure(true);
//	cookie.setHttpOnly(true);
//	cookie.setPath("/"); // global cookie accessible every where
//	return cookie;
//    }
//
//    private Cookie createRememberCookie(Token token) {
//	Cookie cookie = new Cookie(token.getType(), token.getToken());
//	cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//	cookie.setSecure(true);
//	cookie.setHttpOnly(true);
//	cookie.setPath("/"); // global cookie accessible every where
//	return cookie;
//    }

//    @Transactional
//    public Token login(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
//	var auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//	User user;
//	try {
//	    user = UserService.extractUser(Principal.class.cast(auth.getPrincipal()));
//	} catch (NoSuchFieldException | SecurityException e) {
//	    return Token.builder().type("Error").token(
//		    "Internal server error: ".concat(e.getLocalizedMessage()).concat(" -> ").concat(e.getMessage()))
//		    .build();
//	}
//	var login = Login.builder() // include new login
//		.logedUser(user).build();
//
//	if (!login.getLogedUser().isAccountNonExpired()) { // expired
//	    return Token.builder().type("Error").token("Please contact Administratos.").build();
//	} else if (!login.getLogedUser().isAccountNonLocked()) { // locked to use before email confirmation
//	    return Token.builder().type("Error").token("You need to confirm your e-mail to use this API.").build();
//	} else if (!login.getLogedUser().isCredentialsNonExpired()) { // password expired to password reset use
//	    return Token.builder().type("Error").token("Reset your password, follow the link in your e-mail.").build();
//	} else if (!login.getLogedUser().isEnabled()) { // inactive deleted
//	    return Token.builder().type("Error").token("Account inactive.").build();
//	} else {
//
//	    var token = Token.builder().type("Bearer").token(tokenService.generateToken(auth, login)).build();
//	    repository.save(login); // after token generation
//	    return token;
//	}
//    }

}
