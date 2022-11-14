package com.cfg.bm.data.api.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder encoder;

    public User findByUsername(String username) {
	return Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow();
    }

    public Page<User> findAll(Pageable pageable) {
	return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
	return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User save(User user) {
	user.setPassword(encoder.encode(user.getPassword()));
	return Optional.ofNullable(userRepository.save(user)).orElseThrow();
    }

    @Transactional
    public void delete(Long id) { // logical
	var toDelete = findById(id);
	toDelete.setEnabled(false);
	userRepository.save(toDelete);
    }

    @Transactional
    public User updatePassword(User user, String newPassword) {
	user.setPassword(encoder.encode(newPassword));
	return userRepository.save(user);
    }

//    public static User extractUser(Principal principal) throws NoSuchFieldException, SecurityException {
//	UserService userService = new UserService(ApplicationContextUtils.getBean(UserRepository.class),
//		ApplicationContextUtils.getBean(PasswordEncoder.class));
//
//	return userService.findByUsername(Objects.requireNonNull(principal).getName());
//    }
}
