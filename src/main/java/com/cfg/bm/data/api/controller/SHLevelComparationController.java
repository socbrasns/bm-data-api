package com.cfg.bm.data.api.controller;

import javax.persistence.EntityNotFoundException;
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

import com.cfg.bm.data.api.model.subhability.SubHability;
import com.cfg.bm.data.api.model.subhability.extrainfo.LevelComparation;
import com.cfg.bm.data.api.repository.HabilityRepository;
import com.cfg.bm.data.api.repository.LevelComparationRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sub-habilities/level-comparations")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SHLevelComparationController {

	private final LevelComparationRepository levelComparationRepository;
	
	private final HabilityRepository habilityRepository;

	@GetMapping
	public Flux<SubHability> findAll() {
		return Flux.fromIterable(levelComparationRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<SubHability> findById(@PathVariable Long id) throws NotFoundException {
		return Mono.just(levelComparationRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@PostMapping
	public Mono<SubHability> save(@Valid @RequestBody LevelComparation levelComparation) {
		levelComparation.setHability(habilityRepository.findById(levelComparation.getHability().getId())
				.orElseThrow(EntityNotFoundException::new));
		return Mono.just(levelComparationRepository.save(levelComparation));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.just(id).doOnNext(levelComparationRepository::deleteById).then();
	}

}
