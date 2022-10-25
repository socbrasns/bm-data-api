package com.cfg.bm.data.api.controller.flux;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.repository.HabilityRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/habilities")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class HabilityController {

	private final HabilityRepository habilityRepository;

	@GetMapping
	public Flux<Hability> findAll() {
		return Flux.fromIterable(habilityRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<Hability> findById(@PathVariable Long id) {
		return Mono.just(habilityRepository.findById(id).orElseThrow());
	}

	@PostMapping
	public Mono<Hability> save(@Valid @RequestBody Hability hability) {
		return Mono.just(habilityRepository.save(hability));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.just(id).doOnNext(habilityRepository::deleteById).then();
	}
}
