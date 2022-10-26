package com.cfg.bm.data.api.controller.rest;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;
import com.cfg.bm.data.api.service.LevelComparationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sub-habilities/level-comparations")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SHLevelComparationController {

	private final LevelComparationService service;

	@GetMapping
	public ResponseEntity<Page<LevelComparation>> findAll(PageRequest pageRequest) {
		return ResponseEntity.ok(service.findAll(pageRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<LevelComparation> findById(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.of(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<LevelComparation> save(@Valid @RequestBody LevelComparation levelComparation) {
		LevelComparation saved = service.save(levelComparation);
		if (Objects.isNull(levelComparation.getId())) {
			URI location = UriComponentsBuilder.fromUriString("/users/{id}").buildAndExpand(Map.of("id", saved.getId()))
					.toUri();
			return ResponseEntity.created(location).body(saved);
		} else {
			return ResponseEntity.accepted().body(saved);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.accepted().build();
	}

}