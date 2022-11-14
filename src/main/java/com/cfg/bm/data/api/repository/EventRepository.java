package com.cfg.bm.data.api.repository;

import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cfg.bm.data.api.model.Event;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Component
    @Profile("dev")
    public class EventCreator {
	@Autowired
	private EventRepository eventRepository;

	@PostConstruct
	public void init() {
	    Event e = new Event();
	    e.setName("Local Sessions");
	    e.setDate(Calendar.getInstance());
	    eventRepository.save(e);

	}
    }
}
