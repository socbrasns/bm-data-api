package com.cfg.bm.data.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.security.User;
import com.cfg.bm.data.api.service.BmUserDetailsService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class UserController {

	private final BmUserDetailsService bmUserDetailsService;

	@GetMapping
	public Flux<User> findAll() {
		return bmUserDetailsService.readAll();
	}

	@GetMapping("/{id}")
	public Mono<User> findById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return bmUserDetailsService.readById(id);
	}

	@PostMapping
	public Mono<User> save(@Valid @RequestBody User user) {
		return bmUserDetailsService.save(user);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return bmUserDetailsService.delete(id);
	}

}
