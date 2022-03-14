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

import com.cfg.bm.data.api.model.subhability.extrainfo.Percentage;
import com.cfg.bm.data.api.repository.HabilityRepository;
import com.cfg.bm.data.api.repository.PercentageRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sub-habilities/percentages")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SHPercentageController {

	private final PercentageRepository percentageRepository;
	
	private final HabilityRepository habilityRepository;

	@GetMapping
	public Flux<Percentage> findAll() {
		return Flux.fromIterable(percentageRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<Percentage> findById(@PathVariable Long id) throws NotFoundException {
		return Mono.just(percentageRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@PostMapping
	public Mono<Percentage> save(@Valid @RequestBody Percentage percentage) {
		percentage.setHability(habilityRepository.findById(percentage.getHability().getId())
				.orElseThrow(EntityNotFoundException::new));
		return Mono.just(percentageRepository.save(percentage));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.just(id).doOnNext(percentageRepository::deleteById).then();
	}

}
