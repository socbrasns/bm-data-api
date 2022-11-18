package com.cfg.bm.data.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cfg.bm.data.api.model.Event;
import com.cfg.bm.data.api.repository.EventRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@Data
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class EventService {

    EventRepository eventRepository;

    public Page<Event> findAll(Pageable pageable) {
	return eventRepository.findAll(pageable);
    }

    public Event findById(Long id) {
	return eventRepository.findById(id).orElseThrow();
    }

    public Event save(Event event) {
	return eventRepository.save(event);
    }

    public void delete(Long id) {// logical
	eventRepository.save(findById(id).enabled(false));
    }

}
