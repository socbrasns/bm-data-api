package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.HabilityController;
import com.cfg.bm.data.api.model.Hability;
import com.cfg.bm.data.api.request.SearchRequest;
import com.cfg.bm.data.api.service.HabilityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class HabilityControllerImpl implements HabilityController {

    private final HabilityService service;

    @Override
    public Page<Hability> findAll(Pageable pageable) {
	return service.findAll(pageable);
    }

    @Override
    public Hability findById(@Valid Long id) {
	return service.findById(id);
    }

    @Override
    public Hability save(@Valid Hability Hability) {
	return service.save(Hability);
    }

    @Override
    public Void deleteById(@Valid Long id) {
	service.delete(id);
	return null;
    }

    @Override
    public Page<Hability> search(SearchRequest searchRequest) {
	return service.search(searchRequest);
    }

}
