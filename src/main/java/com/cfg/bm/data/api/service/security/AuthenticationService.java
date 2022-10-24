package com.cfg.bm.data.api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.service.UserService;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return service.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

	}

}
