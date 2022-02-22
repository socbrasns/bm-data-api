package com.cfg.bm.data.api.controller;

import java.util.function.UnaryOperator;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.apache.http.impl.execchain.RequestAbortedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.mapper.FormMapper;
import com.cfg.bm.data.api.model.Form;
import com.cfg.bm.data.api.repository.FormRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/forms")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class FormController {

	private final FormRepository formRepository;
	private final FormMapper formMapper;

	@GetMapping
	public Flux<Form> findAll() {
		return Flux.fromIterable(formRepository.findAll());
	}

	@GetMapping("/{id}")
	public Mono<Form> findById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return Mono.just(formRepository.findById(id).orElseThrow());
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Mono<Form> save(@Valid @RequestBody Form form) throws RequestAbortedException {
		return Mono.just(formRepository.save(form));
	}

	@PutMapping("/{id}")
	public Mono<Form> update(@Valid @RequestBody final Form form, @Valid @PathVariable(name = "id", required = true) Long id) {
		UnaryOperator<Form> update = (fromDb) -> {
			formMapper.mapIgnoreNullsAndId(form, fromDb);
			return fromDb;
		};
		return Mono.just(formRepository
				.save(formRepository.findById(id).map(update).orElseThrow(EntityNotFoundException::new)));
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteById(@Valid @PathVariable(name = "id", required = true) Long id) {
		return Mono.empty().and((p) -> {
			formRepository.deleteById(id);
		});
	}

}
