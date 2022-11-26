package com.cfg.bm.data.api.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.UserController;
import com.cfg.bm.data.api.model.User;
import com.cfg.bm.data.api.request.SearchRequest;
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
    public User findById(Long id) {
	return service.findById(id);
    }

    @Override
    public User save(User user) {
	return service.save(user);

    }

    @Override
    public Void deleteById(Long id) {
	service.delete(id);
	return null;
    }

    @Override
    public Page<User> search(SearchRequest searchRequest) {
	return service.search(searchRequest);
    }

}