package com.cfg.bm.data.api.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.cfg.bm.data.api.controller.api.EventController;
import com.cfg.bm.data.api.model.Event;
import com.cfg.bm.data.api.service.EventService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class EventControllerImpl implements EventController {

    private final EventService eventService;

    @Override
    public Page<Event> findAll(Pageable pageable) {
	return eventService.findAll(pageable);
    }

    @Override
    public Event findById(@Valid Long id) {
	return eventService.findById(id);
    }

    @Override
    public Event save(@Valid Event event) {
	return eventService.save(event);
    }

    @Override
    public Void deleteById(@Valid Long id) {
	eventService.delete(id);
	return null;
    }

}
