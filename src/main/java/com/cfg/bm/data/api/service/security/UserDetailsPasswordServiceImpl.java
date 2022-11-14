package com.cfg.bm.data.api.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.service.UserService;

@Service
public class UserDetailsPasswordServiceImpl implements UserDetailsPasswordService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
	return userService.updatePassword(userService.findByUsername(user.getUsername()), newPassword);
    }

}
