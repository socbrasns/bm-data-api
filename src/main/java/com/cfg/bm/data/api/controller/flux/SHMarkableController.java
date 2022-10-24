package com.cfg.bm.data.api.controller.flux;

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

import com.cfg.bm.data.api.model.subhability.extrainfo.Markable;
import com.cfg.bm.data.api.repository.HabilityRepository;
import com.cfg.bm.data.api.repository.MarkableRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sub-habilities/markables")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SHMarkableController {

	private final MarkableRepository markableRepository;
	
	private final HabilityRepository habilityRepository;

	@GetMapping
	public Flux<Markable> findAll() {
		return Flux.fromIterable(markableRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<Markable> findById(@PathVariable Long id) throws NotFoundException {
		return Mono.just(markableRepository.findById(id).orElseThrow(NotFoundException::new));
	}

	@PostMapping
	public Mono<Markable> save(@Valid @RequestBody Markable markable) {
		markable.setHability(habilityRepository.findById(markable.getHability().getId())
				.orElseThrow(EntityNotFoundException::new));
		return Mono.just(markableRepository.save(markable));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@PathVariable Long id) {
		return Mono.just(id).doOnNext(markableRepository::deleteById).then();
	}

}
