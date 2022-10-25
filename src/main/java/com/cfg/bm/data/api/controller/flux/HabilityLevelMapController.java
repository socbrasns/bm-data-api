package com.cfg.bm.data.api.controller.flux;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.model.HabilityLevelMap;
import com.cfg.bm.data.api.repository.HabilityLevelMapRepository;
import com.cfg.bm.data.api.repository.HabilityRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hability-level-map")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class HabilityLevelMapController {

	private HabilityLevelMapRepository habilityLevelMapRepository;

	private final HabilityRepository habilityRepository;

	@GetMapping
	public Flux<HabilityLevelMap> findAll() {
		return Flux.fromIterable(habilityLevelMapRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<HabilityLevelMap> findById(@PathVariable Long id) {
		return Mono.just(habilityLevelMapRepository.findById(id).orElseThrow());
	}

	@PostMapping
	public Mono<HabilityLevelMap> save(@Valid @RequestBody HabilityLevelMap habilityLevelMap) {
		return Mono.just(habilityLevelMapRepository.save(habilityLevelMap));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.just(id).doOnNext(habilityLevelMapRepository::deleteById).then();
	}

	@GetMapping("hability/{id}")
	public Flux<HabilityLevelMap> findLevelsByHabilityId(@PathVariable Long id) throws NotFoundException {
		return Flux.fromStream(habilityLevelMapRepository
				.findByHability(habilityRepository.findById(id).orElseThrow(NotFoundException::new)).stream());
	}
}
