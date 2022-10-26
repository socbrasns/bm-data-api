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

import com.cfg.bm.data.api.model.subhability.extrainfo.Markable;
import com.cfg.bm.data.api.service.MarkableService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/sub-habilities/markables")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SHMarkableController {

	private final MarkableService service;

	@GetMapping
	public ResponseEntity<Page<Markable>> findAll(PageRequest pageRequest) {
		return ResponseEntity.ok(service.findAll(pageRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Markable> findById(@PathVariable Long id) throws NotFoundException {
		return ResponseEntity.of(service.findById(id));
	}

	@PostMapping
	public ResponseEntity<Markable> save(@Valid @RequestBody Markable markable) {
		Markable saved = service.save(markable);
		if (Objects.isNull(markable.getId())) {
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
