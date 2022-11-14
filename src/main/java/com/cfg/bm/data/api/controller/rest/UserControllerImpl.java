package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.UserController;
import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class UserControllerImpl implements UserController {

    UserService service;

    @Override
    public Page<User> findAll(Pageable pageable) {
	return service.findAll(pageable);
    }

    @Override
    public User findById(@Valid Long id) {
	return service.findById(id);
    }

    @Override
    public User save(@Valid User user) {
//	var saved = service.createAccount(user);
	return service.save(user);
//		ResponseEntity.created(
//		UriComponentsBuilder.fromUriString("/user/{id}").buildAndExpand(Map.of("id", saved.getId())).toUri())
//		.body(saved);
    }

    @Override
    public Void deleteById(@Valid Long id) {
	service.delete(id);
	return null;
    }

}