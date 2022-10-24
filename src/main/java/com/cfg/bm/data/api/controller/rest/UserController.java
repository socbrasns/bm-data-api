package com.cfg.bm.data.api.controller.rest;

import java.net.URI;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cfg.bm.data.api.model.security.User;
import com.cfg.bm.data.api.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class UserController {

	UserService service;

	@GetMapping
	public ResponseEntity<Page<User>> findAll(PageRequest request) {
		return ResponseEntity.ok(service.findAll(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return ResponseEntity.of(service.findById(id));
	}

//	@GetMapping("/by-ids")
//	public ResponseEntity<Iterable<User>> findAllById(@Valid @RequestBody List<Long> ids) {
//		return ResponseEntity.ok(service.findAllById(ids));
//	}

	@PostMapping
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User saved = service.save(user);
		URI location = UriComponentsBuilder.fromUriString("/users/{id}").buildAndExpand(Map.of("id", saved.getId()))
				.toUri();
		return ResponseEntity.created(location).body(saved);
	}

//	@PostMapping("/all")
//	public ResponseEntity<Iterable<User>> saveAll(@Valid @RequestBody List<User> users) {
//		return ResponseEntity.ok(service.saveAll(users));
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Valid @PathVariable(name = "id", required = true) Long id) {
		service.deleteById(id);
		return ResponseEntity.accepted().build();
	}

}