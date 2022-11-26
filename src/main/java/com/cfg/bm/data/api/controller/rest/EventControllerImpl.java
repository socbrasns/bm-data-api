package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.EventController;
import com.cfg.bm.data.api.model.Event;
import com.cfg.bm.data.api.request.SearchRequest;
import com.cfg.bm.data.api.service.EventService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class EventControllerImpl implements EventController {

    private final EventService service;

    @Override
    public Page<Event> findAll(Pageable pageable) {
	return service.findAll(pageable);
    }

    @Override
    public Event findById(@Valid Long id) {
	return service.findById(id);
    }

    @Override
    public Event save(@Valid Event event) {
	return service.save(event);
    }

    @Override
    public Void deleteById(@Valid Long id) {
	service.delete(id);
	return null;
    }

    @Override
    public Page<Event> search(SearchRequest searchRequest) {
	return service.search(searchRequest);
    }

}
