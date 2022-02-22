package com.cfg.bm.data.api.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.subhability.SubHability;
import com.cfg.bm.data.api.repository.HabilityRepository;
import com.cfg.bm.data.api.repository.SubHabilityRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subHabilities")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SubHabilityController {

	private final SubHabilityRepository subHabilityRepository;

	@GetMapping
	public Flux<SubHability> findAll() {
		return Flux.fromIterable(subHabilityRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<SubHability> findById(@PathVariable Long id) {
		return Mono.just(subHabilityRepository.findById(id).orElseThrow());
	}

	@PostMapping
	public Mono<SubHability> save(@Valid @RequestBody SubHability subHability,
			@Autowired HabilityRepository habilityRepository) {
		subHability.setHability(habilityRepository.findById(subHability.getHability().getId())
				.orElseThrow(EntityNotFoundException::new));
		return Mono.just(subHabilityRepository.save(subHability));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.empty().and((p) -> {
			subHabilityRepository.deleteById(id);
		});
	}

}
