package com.cfg.bm.data.api.controller.flux;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.security.User;
import com.cfg.bm.data.api.repository.UserRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController(value = "reactiveUserController")
@RequestMapping("/flux/users")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class UserController {

	UserRepository repository;

	@GetMapping
	public Flux<User> findAll() {
		return Flux.fromIterable(repository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<User> findById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return Mono.just(repository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found.")));
	}

	@PostMapping
	public Mono<User> save(@Valid @RequestBody User user) {
		return Mono.just(repository.save(user));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return Mono.fromRunnable(() -> repository.deleteById(id));
	}

}